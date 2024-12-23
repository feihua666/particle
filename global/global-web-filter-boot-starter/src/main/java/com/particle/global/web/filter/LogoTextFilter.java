package com.particle.global.web.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.SingleResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>
 * 处理 logoText ,一般用于使用logoText的地方,如：xxxx管理系统
 * </p>
 *
 * @author yangwei
 * @since 2024-06-04 13:04:11
 */
@Slf4j
public class LogoTextFilter extends OncePerRequestFilter {

	private List<LogoTextResolver> logoTextResolverList;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (CollectionUtil.isNotEmpty(logoTextResolverList)) {
			LogoTextResolveResult resolve = null;
			for (LogoTextResolver logoTextResolver : logoTextResolverList) {
				resolve = logoTextResolver.resolve(request);
				if (resolve != null) {
					break;
				}
			}
			if (resolve == null) {
				filterChain.doFilter(request, response);
			} else {
				outJson(response,resolve);
			}
		}else {
			filterChain.doFilter(request,response);
		}
	}

	protected void outJson(HttpServletResponse httpServletResponse,LogoTextResolveResult resolve)  throws IOException{
		SingleResponse<LogoTextResolveResult> logoTextResolveResultSingleResponse = SingleResponse.of(resolve);
		String toJsonStrForHttp = JSONUtil.toJsonStr(logoTextResolveResultSingleResponse);
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter out = httpServletResponse.getWriter();
		out.write(toJsonStrForHttp);
		out.flush();
		IoUtil.close(out);
	}
	/**
	 * 根据字符串解析
	 * @param ambiguousString
	 * @return
	 */
	public static LogoTextFilter.LogoTextResolveResult resolveByAmbiguousString(String ambiguousString, String logPrefix) {
		return LogoTextFilter.LogoTextResolveResult.create(ambiguousString);
	}


	/**
	 * logoText 解析器
	 */
	public static interface LogoTextResolver {
		/**
		 * 定义一个基础顺序，默认100，越小越靠前
		 */
		public static final int componentBaseOrder = 100;

		public LogoTextResolveResult resolve(HttpServletRequest request);
	}
	/**
	 * logoText 解析结果
	 */
	@Data
	public static class LogoTextResolveResult{

		private String logoText;

		public static LogoTextResolveResult create(String logoText) {
			LogoTextResolveResult logoTextResolveResult = new LogoTextResolveResult();
			logoTextResolveResult.logoText = logoText;
			return logoTextResolveResult;
		}
	}

	@Autowired
	public void setLogoTextResolverList(List<LogoTextResolver> logoTextResolverList) {
		this.logoTextResolverList = logoTextResolverList;
	}



}
