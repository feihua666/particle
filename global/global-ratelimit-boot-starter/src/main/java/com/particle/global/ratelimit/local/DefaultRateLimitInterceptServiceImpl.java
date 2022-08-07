package com.particle.global.ratelimit.local;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.particle.global.ratelimit.RateLimitInterceptService;
import com.particle.global.ratelimit.RateLimiterNamedWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.particle.global.tool.collection.CollectionTool.newArrayList;

/**
 * <p>
 * 默认可配置限流器服务，这里应该能满足绝大部分需求
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 20:21
 */
@Slf4j
@Order
@Component
@ConfigurationProperties(prefix = "particle.rate-limit.local")
public class DefaultRateLimitInterceptServiceImpl implements RateLimitInterceptService {


	private final static String MODEL_SHARED = "shared";
	private final static String MODEL_HALFSHARED = "halfShared";
	private final static String MODEL_NONESHARED = "noneShared";


	/**
	 * 是否启用
	 * 添加 Setter 主要是为了 configs 属性可以配置，否则配置不进来
	 */
	@Setter
	private boolean enabled = true;
	/**
	 * 配置规则
	 * 添加Setter主要是为了 configs 属性可以配置，否则配置不进来
	 */
	@Setter
	private List<Config> configs;

	/**
	 * 限流器们
	 */
	private Map<String, RateLimiterNamedWrapper> sharedRateLimiterNamedWrapperMap = new ConcurrentHashMap<>();
	private Map<String,RateLimiterNamedWrapper> noneSharedRateLimiterNamedWrapperMap = new ConcurrentHashMap<>();
	private Map<String,RateLimiterNamedWrapper> halfSharedRateLimiterNamedWrapperMap = new ConcurrentHashMap<>();


	private Map<String, Pattern> patternCacheMap = new ConcurrentHashMap<>();
	@Override
	public List<RateLimiterNamedWrapper> getRateLimiter(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// 如果没有配置不处理
		if (!enabled) {
			return null;
		}
		if (CollectionUtil.isEmpty(configs)) {
			return null;
		}

		// 要返回的限流器集合
		List<RateLimiterNamedWrapper> result = new ArrayList<>();
		for (Config config : configs) {
			if (isAllConfigEmpty(config)) {
				log.warn("限流默认不生效，没有可用配置, name={}",config.getName());
				continue;
			}

			MatchResultConfig matchResultConfig = new MatchResultConfig();
			matchResultConfig.setUrlReg(urlMatch(request, config.getUrlReg()));
			matchResultConfig.setQueryRegs(allQueryMatch(request, config.getQueryRegs()));
			matchResultConfig.setHeaderRegs(allHeadersMatch(request, config.getHeaderRegs()));
			matchResultConfig.setBodyRegs(allBodyRegMatch(request, config.getBodyRegs()));
			matchResultConfig.setTemplate(templateParse(request,config.getTemplate()));

			List<RateLimiterNamedWrapper> rateLimiterNamedWrappers = null;
			switch (config.getModel()){
				case MODEL_SHARED: {
					// 共享
					rateLimiterNamedWrappers = getShared(request, config,matchResultConfig);
					break;
				}
				case MODEL_HALFSHARED: {
					// 独享
					rateLimiterNamedWrappers = getHalfShared(request, config,matchResultConfig);
					break;
				}
				case MODEL_NONESHARED: {
					// 独享
					rateLimiterNamedWrappers = getNoneShared(request, config,matchResultConfig);
					break;
				}
				default: {
					log.error("不支持的模式：model={},name={}",config.getModel(),config.getName());
				}
			}
			if (!CollectionUtil.isEmpty(rateLimiterNamedWrappers)) {
				result.addAll(rateLimiterNamedWrappers);
			}
		}
		return result;
	}

	/**
	 * 是否一个配置中所有项都为空
	 * @param config
	 * @return
	 */
	private boolean isAllConfigEmpty(Config config){
		boolean b = StrUtil.isEmpty(config.getUrlReg())
				&& CollectionUtil.isEmpty(config.getQueryRegs())
				&& CollectionUtil.isEmpty(config.getHeaderRegs())
				&& CollectionUtil.isEmpty(config.getBodyRegs())
				&& StrUtil.isEmpty(config.getTemplate());
		return b;
	}

	/**
	 * 是否存在匹配的
	 * @param config
	 * @param matchResultConfig
	 * @return
	 */
	private boolean isHasMacthed(Config config,MatchResultConfig matchResultConfig){
		// 检测是否匹配
		boolean b = (StrUtil.isEmpty(config.getUrlReg()) || matchResultConfig.getUrlReg() != null)
				&& (CollectionUtil.isEmpty(config.getQueryRegs()) || matchResultConfig.getQueryRegs() != null)
				&& (CollectionUtil.isEmpty(config.getHeaderRegs()) || matchResultConfig.getHeaderRegs() != null)
				&& (CollectionUtil.isEmpty(config.getBodyRegs()) || matchResultConfig.getBodyRegs() != null)
				&& (StrUtil.isEmpty(config.getTemplate()) || !StrUtil.isEmpty(matchResultConfig.getTemplate()))
				;
		return b;
	}
	/**
	 * 获取共享的限流器
	 * @param request
	 * @param config
	 * @return
	 */
	private List<RateLimiterNamedWrapper> getShared(HttpServletRequest request,Config config,MatchResultConfig matchResultConfig){
		// 检测是否匹配
		boolean b = isHasMacthed(config,matchResultConfig);

		if(b){
			RateLimiterNamedWrapper rateLimiterNamedWrapper = sharedRateLimiterNamedWrapperMap.computeIfAbsent(config.toString(),(key)->
				new RateLimiterNamedWrapper(config.getName(),config.getCode(), RateLimiter.create(config.getRate()))
			);
			return newArrayList(rateLimiterNamedWrapper);
		}
		return null;
	}


	/**
	 * 获取独享限流器
	 * @param request
	 * @param config
	 * @param matchResultConfig
	 * @return
	 */
	private List<RateLimiterNamedWrapper> getNoneShared(HttpServletRequest request,Config config,MatchResultConfig matchResultConfig){
		// 检测是否匹配
		boolean b = isHasMacthed(config,matchResultConfig);

		// 没有匹配的直接返回空
		if (!b) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		if(!StrUtil.isEmpty(config.getUrlReg()) && matchResultConfig.getUrlReg() != null){
			sb.append(matchResultConfig.getUrlReg().getMatchedExtract());
		}
		if(!CollectionUtil.isEmpty(config.getQueryRegs()) && matchResultConfig.getQueryRegs() != null){
			sb.append(matchResultConfig.getQueryRegs().getMatchedExtract());
		}
		if(!CollectionUtil.isEmpty(config.getHeaderRegs()) && matchResultConfig.getHeaderRegs() != null){
			sb.append(matchResultConfig.getHeaderRegs().getMatchedExtract());
		}
		if(!CollectionUtil.isEmpty(config.getBodyRegs()) && matchResultConfig.getBodyRegs() != null){
			sb.append(matchResultConfig.getBodyRegs().getMatchedExtract());
		}
		if(!StrUtil.isEmpty(config.getTemplate()) && !StrUtil.isEmpty(matchResultConfig.getTemplate())){
			sb.append(matchResultConfig.getTemplate());
		}
		if (sb.length() == 0) {
			return null;
		}
		RateLimiterNamedWrapper rateLimiterNamedWrapper = noneSharedRateLimiterNamedWrapperMap.computeIfAbsent(sb.toString(), (k) -> new RateLimiterNamedWrapper(config.getName(), config.getCode(), RateLimiter.create(config.getRate())));
		return newArrayList(rateLimiterNamedWrapper);

	}

	/**
	 * 半共享
	 * @param request
	 * @param config
	 * @param matchResultConfig
	 * @return
	 */
	private List<RateLimiterNamedWrapper> getHalfShared(HttpServletRequest request,Config config,MatchResultConfig matchResultConfig){
		// 检测是否匹配
		boolean b = isHasMacthed(config,matchResultConfig);

		// 没有匹配的直接返回空
		if (!b) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		if(!StrUtil.isEmpty(config.getUrlReg()) && matchResultConfig.getUrlReg() != null){
			sb.append(matchResultConfig.getUrlReg().getMatchedReg());
		}
		if(!CollectionUtil.isEmpty(config.getQueryRegs()) && matchResultConfig.getQueryRegs() != null){
			sb.append(matchResultConfig.getQueryRegs().getMatchedReg());
		}
		if(!CollectionUtil.isEmpty(config.getHeaderRegs()) && matchResultConfig.getHeaderRegs() != null){
			sb.append(matchResultConfig.getHeaderRegs().getMatchedReg());
		}
		if(!CollectionUtil.isEmpty(config.getBodyRegs()) && matchResultConfig.getBodyRegs() != null){
			sb.append(matchResultConfig.getBodyRegs().getMatchedReg());
		}
		if(!StrUtil.isEmpty(config.getTemplate()) && !StrUtil.isEmpty(matchResultConfig.getTemplate())){
			sb.append(matchResultConfig.getTemplate());
		}
		if (sb.length() == 0) {
			return null;
		}
		RateLimiterNamedWrapper rateLimiterNamedWrapper = halfSharedRateLimiterNamedWrapperMap.computeIfAbsent(sb.toString(), (k) -> new RateLimiterNamedWrapper(config.getName(), config.getCode(), RateLimiter.create(config.getRate())));
		return newArrayList(rateLimiterNamedWrapper);
	}
	/**
	 * 缓存pattern
	 * @param pattern
	 * @return
	 */
	private Pattern getPattern(String pattern){
		if (StrUtil.isEmpty(pattern)) {
			return null;
		}
		Pattern patternCache = patternCacheMap.computeIfAbsent(pattern,(k)-> Pattern.compile(pattern));
		return patternCache;
	}

	/**
	 * 匹配
	 * @param reg
	 * @param content
	 * @return
	 */
	private String match(String reg,String content){

		List<String> allGroups = ReUtil.getAllGroups(getPattern(reg), content);
		return Optional.ofNullable(allGroups).map(g -> g.stream().collect(Collectors.joining(","))).orElse(null);
	}

	/**
	 * 全部匹配
	 * @param allMatchRegs
	 * @param content
	 * @return
	 */
	private String match(List<String> allMatchRegs,String content){
		if (CollectionUtil.isEmpty(allMatchRegs)) {
			return null;
		}
		List<String> r = new ArrayList<>();
		String itemMatch = null;
		for (String allMatchReg : allMatchRegs) {
			itemMatch = match(allMatchReg, content);
			if(StrUtil.isEmpty(itemMatch)){
				return null;
			}
			r.add(itemMatch);
		}
		return r.stream().collect(Collectors.joining(","));
	}

		/**
		 * 是否url匹配
		 * @param request
		 * @param urlReg
		 * @return
		 */
	private MatchResult urlMatch(HttpServletRequest request, String urlReg){

		String matchExtract = match(urlReg,request.getRequestURI());
		if (StrUtil.isEmpty(matchExtract)) {
			return null;
		}
		return new MatchResult(urlReg,matchExtract);
	}

	/**
	 * 是否匹配查询参数
	 * @param request
	 * @param queryRegs
	 * @return
	 */
	private MatchResult allQueryMatch(HttpServletRequest request,List<String> queryRegs){

		String matchExtract = match(queryRegs,request.getQueryString());
		if (StrUtil.isEmpty(matchExtract)) {
			return null;
		}
		return new MatchResult(queryRegs.stream().collect(Collectors.joining(MatchResult.SEPERATOR)), matchExtract);

	}
	/**
	 * 是否全部匹配请求头
	 * @param request
	 * @param headers
	 * @return
	 */
	private MatchResult allHeadersMatch(HttpServletRequest request,List<Header> headers){
		if (CollectionUtil.isEmpty(headers)){
			return null;
		}
		MatchResult matchResult = null;
		List<MatchResult> result = new ArrayList<>(headers.size());
		for (Header headerReg : headers) {
			matchResult = matchHeader(request, headerReg);
			if (matchResult == null) {
				return null;
			}
			result.add(matchResult);
		}
		String matchRegs = result.stream().map(item -> item.getMatchedReg()).collect(Collectors.joining(MatchResult.SEPERATOR));
		String matchExtract = result.stream().map(item -> item.getMatchedExtract()).collect(Collectors.joining(MatchResult.SEPERATOR));
		return new MatchResult(matchRegs,matchExtract);
	}

	/**
	 * 单个请求头匹配
	 * 需要请求头的 key和value同时匹配
	 * @param request
	 * @param headerReg
	 * @return
	 */
	private MatchResult matchHeader(HttpServletRequest request,Header headerReg){
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerName = null;
		String headerValue = null;

		List<String> matchRegs = new ArrayList<>();
		List<String> matchExtract = new ArrayList<>();
		while (headerNames.hasMoreElements()){
			headerName = headerNames.nextElement();
			headerValue = request.getHeader(headerName);
			String matchHeaderName = match(headerReg.getNameReg(), headerName);
			String matchHeaderValue = match(headerReg.getValueReg(), headerValue);
			if (StrUtil.hasEmpty(matchHeaderName,matchHeaderValue)) {
				return null;
			}
			matchRegs.add(headerReg.getNameReg() + "=" + headerReg.getValueReg());
			matchExtract.add(matchHeaderName +"=" + matchHeaderValue);
		}
		return new MatchResult(matchRegs.stream().collect(Collectors.joining(MatchResult.SEPERATOR)), matchExtract.stream().collect(Collectors.joining(MatchResult.SEPERATOR)));
	}


	/**
	 * 是否匹配请求体
	 * @param request
	 * @param bodyRegs
	 * @return
	 */
	private MatchResult allBodyRegMatch(HttpServletRequest request,List<String> bodyRegs){
		if (CollectionUtil.isEmpty(bodyRegs)) {
			return null;
		}
		String bodyString = null;
		try {
			bodyString = IoUtil.readUtf8(request.getInputStream());
		} catch (IOException e) {
			log.error("获取请求体", e);

		}
		if (StrUtil.isEmpty(bodyString)) {
			return null;
		}
		String match = match(bodyRegs, bodyString);
		if(StrUtil.isEmpty(match)){
			return null;
		}
		return new MatchResult(bodyRegs.stream().collect(Collectors.joining(MatchResult.SEPERATOR)),match );
	}

	/**
	 * 返回不为空代表匹配
	 * @param request
	 * @param template
	 * @return
	 */
	private String templateParse(HttpServletRequest request,String template){
		String r = null;
		return r;
	}

	/**
	 * 匹配的结果
	 */
	@Getter
	@AllArgsConstructor
	private static class MatchResult{
		public static String SEPERATOR = "|";
		/**
		 * 匹配的正则
		 */
		private String matchedReg;

		private String matchedExtract;
	}
	/**
	 * 请求头匹配
	 */
	@Data
	private static class Header{
		// 请求头名称匹配
		private String nameReg;
		// 请求头名称对应的值匹配
		private String valueReg;
	}

	/**
	 * 匹配规则配置
	 */
	@Data
	private static class Config{
		// 限流器的名称
		private String name;
		// 限流器的编码
		private String code;
		// 限流器的速率
		private double rate = 50d;
		// 匹配 url 正则
		private String urlReg;
		// 匹配请求头
		private List<Header> headerRegs;
		// 匹配查询参数
		private List<String> queryRegs;
		// 匹配 请求体参数
		private List<String> bodyRegs;
		/**
		 * 利用模板技术自定义其它
		 * 只要输出有值就代表匹配，否则不匹配
		 */
		private String template;
		/**
		 * 限流器共享
		 * 场景：本质上来说一个配置算一个限流器，所有匹配的规则用这一个限流器，ok没问题
		 * 但想想另一个场景，所有匹配的url单独用一个限流器，对，不共享
		 * 用这个标识来控制是否共享
		 */
		private String model = MODEL_NONESHARED;
	}

	@Getter
	@Setter
	private static class MatchResultConfig{

		private MatchResult urlReg;

		private MatchResult headerRegs;

		private MatchResult queryRegs;

		private MatchResult bodyRegs;

		private String template;
	}
}
