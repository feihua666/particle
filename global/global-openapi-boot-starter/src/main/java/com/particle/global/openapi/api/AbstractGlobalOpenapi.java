package com.particle.global.openapi.api;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.openapi.data.*;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 全局抽象开放接口实现
 * 注意：缓存时间尽量使用质数错开，以防止缓存同时失效导致库压力
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:05
 */
@Slf4j
public abstract class AbstractGlobalOpenapi implements OpenApi{

	/**
	 * 请求时间戳，时间差不能超过 2 分钟
	 */
	private static Long timestampExpire = 2L * 60L *1000L;

	/**
	 * apiInfo缓存key
	 */
	private static final String globalOpenapiApiInfo_key = "globalOpenapiApiInfo_key";
	/**
	 * apiInfo缓存时间
	 */
	private static Long globalOpenapiApiInfoExpire = 3L * 60L *1000L;

	/**
	 * 客户端缓存key
	 */
	private static final String globalOpenapiClient_key = "globalOpenapiClient_key";
	/**
	 * 客户端缓存时间 5 分钟
	 */
	private static Long globalOpenapiClientExpire = 5L * 60L *1000L;

	/**
	 * 用来匹配请求
	 */
	protected List<AntPathRequestMatcher> antPathRequestMatchers;
	/**
	 * 用于存储匹配的url
	 */
	protected Set<String> urlPatterns = new LinkedHashSet<>();

	@Autowired
	protected GlobalOpenapiClientProvider globalOpenapiClientProvider;
	@Autowired
	protected GlobalOpenapiApiInfoProvider globalOpenapiApiInfoProvider;

	@Autowired(required = false)
	protected GlobalOpenapiCache globalOpenapiCache;

	@Autowired(required = false)
	protected List<GlobalOpenapiUrlPatternConfigure> globalOpenapiUrlPatternConfigures;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void initAntPathRequestMatchers() {
		if (antPathRequestMatchers == null) {
			antPathRequestMatchers = new ArrayList<>(urlPatterns.size());
			for (String urlPattern : urlPatterns) {
				antPathRequestMatchers.add(new AntPathRequestMatcher(urlPattern));
			}
			if (CollectionUtil.isNotEmpty(globalOpenapiUrlPatternConfigures)) {
				for (GlobalOpenapiUrlPatternConfigure globalOpenapiUrlPatternConfigure : globalOpenapiUrlPatternConfigures) {
					Set<String> patterns = globalOpenapiUrlPatternConfigure.urlPatterns();
					if (CollectionUtil.isNotEmpty(patterns)) {
						for (String urlPattern : patterns) {
							antPathRequestMatchers.add(new AntPathRequestMatcher(urlPattern));
						}
					}
				}
			}
		}
	}

	@Override
	public BasicHeaderDTO obtainBasicHeaderDTO(HttpServletRequest request) {
		return BasicHeaderDTO.createDefaultByHttpServletRequest(request);
	}

	@Override
	public RequestParameterDTO obtainRequestParameterDTO(HttpServletRequest request) {
		return RequestParameterDTO.create(request);
	}

	@Override
	public OpenapiClient getOpenapiClient(String clientId,boolean includeSecret,boolean includeAuthorities) {

		if (globalOpenapiCache !=null) {
			OpenapiClient openapiClient = (OpenapiClient)globalOpenapiCache.get(globalOpenapiClient_key);
			if (openapiClient != null) {
				return openapiClient;
			}
		}

		OpenapiClient openapiClient = globalOpenapiClientProvider.getOpenapiClientByClientId(clientId,includeSecret,includeAuthorities);
		if (openapiClient != null && globalOpenapiCache != null) {
			globalOpenapiCache.put(globalOpenapiClient_key,openapiClient,globalOpenapiClientExpire);
		}
		return openapiClient;
	}

	@Override
	public ApiInfo getApiInfo(String apiUrl,String appId) {
		if (globalOpenapiApiInfoProvider == null) {
			return null;
		}
		if (globalOpenapiCache !=null) {
			ApiInfo globalOpenapiApiInfo = (ApiInfo)globalOpenapiCache.get(globalOpenapiApiInfo_key);
			if (globalOpenapiApiInfo != null) {
				return globalOpenapiApiInfo;
			}
		}
		ApiInfo apiInfo = globalOpenapiApiInfoProvider.getApiInfo(apiUrl, appId);
		if (apiInfo != null && globalOpenapiCache != null) {
			globalOpenapiCache.put(globalOpenapiApiInfo_key,apiInfo,globalOpenapiApiInfoExpire);
		}

		return apiInfo;
	}

	/**
	 * 将三部分拼接起来一起参与摘要计算
	 * @param basicHeaderDTO
	 * @param responseDataDTO
	 * @param openapiClient
	 * @return
	 */
	@Override
	public String signature(BasicHeaderDTO basicHeaderDTO, ResponseDataDTO responseDataDTO, OpenapiClient openapiClient) {
		String digestHex = digestData(basicHeaderDTO, responseDataDTO, openapiClient);
		OpenapiAlgorithmSecret responseAlgorithmSecret = openapiClient.getResponseAlgorithmSecret();
		if (responseAlgorithmSecret.getIsSign()) {
			String sign = responseAlgorithmSecret.getSignatureAlgorithm().sign(digestHex, responseAlgorithmSecret.getPrivateSignSecret());
			return sign;
		}
		return digestHex;
	}

	@Override
	public boolean verifyTimestamp(BasicHeaderDTO basicHeaderDTO) {
		Long now = System.currentTimeMillis();
		Long requestTimestamp = basicHeaderDTO.getTimestamp();
		if (requestTimestamp == null) {
			return false;
		}

		return (now - requestTimestamp) <= timestampExpire;
	}

	@Override
	public boolean verifyNonce(BasicHeaderDTO basicHeaderDTO) {
		if (globalOpenapiCache == null) {
			log.warn("globalOpenapiCache is null. verifyNonce ignored.");
			return true;
		}

		String key = basicHeaderDTO.getClientId() + basicHeaderDTO.getNonce();
		String flag = (String) globalOpenapiCache.get(key);
		if (StrUtil.isEmpty(flag)) {
			// 这里value存储一个1即可，代表有值
			globalOpenapiCache.put(key,"1",timestampExpire);
			return true;
		}

		// 到这里说明存在
		return false;
	}

	@Override
	public boolean verifySignature(BasicHeaderDTO basicHeaderDTO, RequestParameterDTO requestParameterDTO, OpenapiClient openapiClient) {
		String digestHex = digestData(basicHeaderDTO, requestParameterDTO, openapiClient);

		OpenapiAlgorithmSecret requestAlgorithmSecret = openapiClient.getRequestAlgorithmSecret();
		if (requestAlgorithmSecret.getIsSign()) {
			Boolean verify = requestAlgorithmSecret.getSignatureAlgorithm().verify(digestHex, requestAlgorithmSecret.getPrivateSignSecret(), basicHeaderDTO.getSignature());
			return verify;
		}
		// 直接对比摘要
		return Objects.equals(digestHex,basicHeaderDTO.getSignature());
	}

	/**
	 * 对请求数据做摘要处理
	 * @param basicHeaderDTO
	 * @param requestParameterDTO
	 * @param openapiClient
	 * @return
	 */
	protected String digestData(BasicHeaderDTO basicHeaderDTO, RequestParameterDTO requestParameterDTO, OpenapiClient openapiClient) {
		Assert.notNull(openapiClient, ErrorCodeOpenapiEnum.OPENAPI_CLIENT_ID_NOT_EXIST);

		String finalStringForSignature = prepareSignatureData(basicHeaderDTO,requestParameterDTO,openapiClient.getSecret());

		OpenapiAlgorithmSecret requestAlgorithmSecret = openapiClient.getRequestAlgorithmSecret();
		String digestHex = requestAlgorithmSecret.getDigestAlgorithm().digestHex(finalStringForSignature);

		return digestHex;
	}

	/**
	 * 准备做签名的数据
	 * @param basicHeaderDTO
	 * @param requestParameterDTO
	 * @param secret
	 * @return
	 */
	public static String prepareSignatureData(BasicHeaderDTO basicHeaderDTO, RequestParameterDTO requestParameterDTO,String secret) {
		StringBuffer sb = new StringBuffer();
		sb.append(basicHeaderDTO.buildStringForSignature());
		sb.append(requestParameterDTO.buildStringForSignature());
		sb.append(secret);
		return sb.toString();
	}

	/**
	 * 对响应数据做摘要处理
	 * @param responseDataDTO
	 * @param openapiClient
	 * @return
	 */
	protected String digestData(BasicHeaderDTO basicHeaderDTO,ResponseDataDTO responseDataDTO, OpenapiClient openapiClient) {
		StringBuffer sb = new StringBuffer();
		sb.append(basicHeaderDTO.buildStringForSignature());
		sb.append(responseDataDTO.buildStringForSignature());
		sb.append(openapiClient.getSecret());

		String finalStringForSignature = sb.toString();

		OpenapiAlgorithmSecret responseAlgorithmSecret = openapiClient.getResponseAlgorithmSecret();
		String digestHex = responseAlgorithmSecret.getDigestAlgorithm().digestHex(finalStringForSignature);

		return digestHex;
	}
}
