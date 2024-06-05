package com.particle.global.web.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.http.HttpClientTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>
 * 处理 webTitle 图标,一般用于使用webTitle的地方
 * </p>
 *
 * @author yangwei
 * @since 2024-06-04 13:04:11
 */
@Slf4j
public class WebTitleFilter extends OncePerRequestFilter {

	private List<WebTitleResolver> webTitleResolverList;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (CollectionUtil.isNotEmpty(webTitleResolverList)) {
			WebTitleResolveResult resolve = null;
			for (WebTitleResolver webTitleResolver : webTitleResolverList) {
				resolve = webTitleResolver.resolve(request);
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

	protected void outJson(HttpServletResponse httpServletResponse, WebTitleFilter.WebTitleResolveResult resolve)  throws IOException{
		SingleResponse<WebTitleFilter.WebTitleResolveResult> webTitleResolveResultSingleResponse = SingleResponse.of(resolve);
		String toJsonStrForHttp = JSONUtil.toJsonStr(webTitleResolveResultSingleResponse);
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
	public static WebTitleFilter.WebTitleResolveResult resolveByAmbiguousString(String ambiguousString, String logPrefix) {
		// base64
		if (Base64.isBase64(ambiguousString)) {
			return WebTitleFilter.WebTitleResolveResult.create(ambiguousString);
		}
		// http网络地址
		if (StrUtil.startWith(ambiguousString, "http")) {
			byte[] download = null;
			try {
				download = HttpClientTool.download(ambiguousString, null);
			} catch (IOException e) {
				log.error("{} webTitle download error by url={}",logPrefix,ambiguousString, e);
				return null;
			}
			return WebTitleFilter.WebTitleResolveResult.create(Base64.encode(download));
		}

		// 其它处理，如：classpath路径，文件绝对路径等
		byte[] bytes = null;
		try {
			bytes = ResourceUtil.readBytes(ambiguousString);
		} catch (Exception e) {
			log.error("{} webTitle readBytes error by resource={}",logPrefix,ambiguousString, e);
			return null;
		}
		return WebTitleFilter.WebTitleResolveResult.create(Base64.encode(bytes));
	}


	/**
	 * webTitle 解析器
	 */
	public static interface WebTitleResolver {
		/**
		 * 定义一个基础顺序，默认100，越小越靠前
		 */
		public static final int componentBaseOrder = 100;

		public WebTitleResolveResult resolve(HttpServletRequest request);
	}
	/**
	 * webTitle 解析结果
	 */
	@Data
	public static class WebTitleResolveResult{

		private String webTitle;

		public static WebTitleResolveResult create(String webTitle) {
			WebTitleResolveResult webTitleResolveResult = new WebTitleResolveResult();
			webTitleResolveResult.webTitle = webTitle;
			return webTitleResolveResult;
		}
	}

	@Autowired
	public void setWebTitleResolverList(List<WebTitleResolver> webTitleResolverList) {
		this.webTitleResolverList = webTitleResolverList;
	}



}
