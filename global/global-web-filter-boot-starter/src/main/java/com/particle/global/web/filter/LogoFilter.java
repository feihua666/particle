package com.particle.global.web.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.http.HttpClientTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 处理 logo 图标,一般用于使用logo的地方
 * </p>
 *
 * @author yangwei
 * @since 2024-06-04 13:04:11
 */
@Slf4j
public class LogoFilter extends OncePerRequestFilter {

	private List<LogoResolver> logoResolverList;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (CollectionUtil.isNotEmpty(logoResolverList)) {
			LogoResolveResult resolve = null;
			for (LogoResolver logoResolver : logoResolverList) {
				resolve = logoResolver.resolve(request);
				if (resolve != null) {
					break;
				}
			}
			if (resolve == null) {
				filterChain.doFilter(request, response);
			} else {
				handleResolvedResult(resolve, response);
			}
		}else {
			filterChain.doFilter(request,response);
		}
	}

	/**
	 * 处理解析后的结果并写到响应
	 * @param resolve
	 * @param response
	 * @throws IOException
	 */
	private void handleResolvedResult(LogoResolveResult resolve,HttpServletResponse response) throws IOException {
		response.setContentType("image/*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control", "public, max-age=2592000");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", System.currentTimeMillis() + 2592000000L);

		Base64.decodeToStream(resolve.getBase64(),response.getOutputStream(),true);

	}

	/**
	 * 根据字符串解析
	 * @param ambiguousString
	 * @return
	 */
	public static LogoFilter.LogoResolveResult resolveByAmbiguousString(String ambiguousString, String logPrefix) {
		// base64
		if (Base64.isBase64(ambiguousString)) {
			return LogoFilter.LogoResolveResult.create(ambiguousString);
		}
		// http网络地址
		if (StrUtil.startWith(ambiguousString, "http")) {
			byte[] download = null;
			try {
				download = HttpClientTool.download(ambiguousString, null);
			} catch (IOException e) {
				log.error("{} logo download error by url={}",logPrefix,ambiguousString, e);
				return null;
			}
			return LogoFilter.LogoResolveResult.create(Base64.encode(download));
		}

		// 其它处理，如：classpath路径，文件绝对路径等
		byte[] bytes = null;
		try {
			bytes = ResourceUtil.readBytes(ambiguousString);
		} catch (Exception e) {
			log.error("{} logo readBytes error by resource={}",logPrefix,ambiguousString, e);
			return null;
		}
		return LogoFilter.LogoResolveResult.create(Base64.encode(bytes));
	}


	/**
	 * logo 解析器
	 */
	public static interface LogoResolver {
		/**
		 * 定义一个基础顺序，默认100，越小越靠前
		 */
		public static final int componentBaseOrder = 100;

		public LogoResolveResult resolve(HttpServletRequest request);
	}
	/**
	 * logo 解析结果
	 */
	@Data
	public static class LogoResolveResult{

		private String base64;

		public static LogoResolveResult create(String base64) {
			LogoResolveResult logoResolveResult = new LogoResolveResult();
			logoResolveResult.base64 = base64;
			return logoResolveResult;
		}
	}

	@Autowired
	public void setLogoResolverList(List<LogoResolver> logoResolverList) {
		this.logoResolverList = logoResolverList;
	}



}
