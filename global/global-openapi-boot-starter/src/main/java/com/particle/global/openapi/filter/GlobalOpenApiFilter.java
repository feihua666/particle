package com.particle.global.openapi.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.BaseException;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.openapi.GlobalOpenapiProperties;
import com.particle.global.openapi.api.OpenApi;
import com.particle.global.openapi.api.OpenapiHelper;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.security.security.PermissionService;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.servlet.RequestTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>
 * 全局开放接口入口过滤器，实现接口参数的验签
 * 如果能捕获到异常，会走两遍该filter，第一遍和第二遍分区处理不同的逻辑，以完成整个响应记录
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 14:51:20
 */
public class GlobalOpenApiFilter extends OncePerRequestFilter {

	private static final String openapiContextRequestKey = "openapiContextRequestKey";

	@Autowired
	private List<OpenApi> openApiList;
	@Autowired
	private OpenapiHelper openapiHelper;
	@Autowired
	private GlobalOpenapiProperties globalOpenapiProperties;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		OpenApi openApi = null;
		Throwable throwable = null;
		try {
			// 如果本filter抛出异常，会走两遍，因为 tomcat 可能会将异常重写向到 /error 处理，这里处理的结果才是最终的返回结果
			Object attributeOpenapiContext = request.getAttribute(openapiContextRequestKey);
			if (request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE) == null && attributeOpenapiContext == null) {
				for (OpenApi op : openApiList) {
					if (op.support(request)) {
						openapiHelper.requestStart(request, op);
						break;
					}
				}
			}else {
				if (attributeOpenapiContext != null) {
					openapiHelper.openApiStart(((OpenapiContext) attributeOpenapiContext));
				}
			}

			filterChain.doFilter(request, response);
		} catch (Throwable e) {
			// 注意：该逻辑必须写在 throwable = e 上面，否则在finally块中处理矛盾
			if (OpenapiCollectTool.isStartOpenApi() && e instanceof BaseException) {
				request.setAttribute(openapiContextRequestKey,OpenapiCollectTool.getContext());
				OpenapiContext context = OpenapiCollectTool.getContext();
				if (context != null) {
					context.setThrowable(throwable);
				}
				IErrorCode errorCode = ((BaseException) e).getError();
				Response responseData = Response.buildFailure(errorCode);
				boolean ajaxRequest = RequestTool.isAjaxRequest(request);
				String accept = request.getHeader(HttpHeaders.ACCEPT);
				Boolean forceOutputJsonOnBaseException = globalOpenapiProperties.getForceOutputJsonOnBaseException();
				if ((forceOutputJsonOnBaseException != null || forceOutputJsonOnBaseException) || ajaxRequest || StrUtil.contains(accept, MediaType.APPLICATION_JSON_VALUE)) {
					if (context != null) {
						context.setResponseResult(responseData);
					}
					outJson(response,responseData,errorCode);
					return;
				}

			}
			throwable = e;
			// 这里直接抛出异常，会走两遍 该filter
			throw e;
		} finally {
			if (OpenapiCollectTool.isStartOpenApi()) {
				if( throwable == null){
					// 如果没有异常，正常返回
					openapiHelper.requestFinished(request, response);
				}else {
					OpenapiContext context = OpenapiCollectTool.getContext();
					if (context != null) {
						context.setThrowable(throwable);
					}
				}

				PermissionService.clear();
				OpenapiCollectTool.clear();
			}

		}

	}

	/**
	 * 在 /error 请求时是否不过滤，这里过滤
	 * @return
	 */
	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return false;
	}

	/**
	 * 输出json
	 * @param httpServletResponse
	 * @param responseResult
	 * @throws IOException
	 */
	protected void outJson(HttpServletResponse httpServletResponse, Response responseResult, IErrorCode errorCode) throws IOException{
		httpServletResponse.setContentType("application/json;charset=utf-8");
		httpServletResponse.setStatus(errorCode.getHttpStatus());
		PrintWriter out = httpServletResponse.getWriter();
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
		String toJsonStrForHttp = JsonTool.toJsonStrForHttp(responseResult, jackson2HttpMessageConverter.getObjectMapper());

		out.write(toJsonStrForHttp);

		out.flush();
		IoUtil.close(out);
	}
}
