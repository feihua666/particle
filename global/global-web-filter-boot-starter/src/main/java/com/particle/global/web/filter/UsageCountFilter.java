package com.particle.global.web.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.dto.response.Response;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.login.LoginUserTool;
import com.particle.global.tool.spring.SpringContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 使用次数统计过滤器
 * </p>
 *
 * @author yangwei
 * @since 2023-10-25 14:44:31
 */
@Slf4j
public class UsageCountFilter extends OncePerRequestFilter {

	@Autowired(required = false)
	private UsageCountMarker usageCountMarker;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		if (usageCountMarker != null) {
			LoginUser loginUser = LoginUserTool.getLoginUser();
			if (loginUser != null) {
				// 请求接口地址
				String requestURI = request.getRequestURI();
				String contextPath = request.getServletContext().getContextPath();
				if (StrUtil.isNotEmpty(contextPath)) {
					requestURI = requestURI.substring(contextPath.length());
				}

				UsageCountMarkResult usageCountMarkResult = usageCountMarker.mark(requestURI, loginUser.getId(), loginUser.getCurrentTenant() != null ? loginUser.getCurrentTenant().getId() : null);

				if (usageCountMarkResult != null) {
					if (usageCountMarkResult.getIsExceed()) {
						ErrorCodeGlobalEnum usageCountLimitError = ErrorCodeGlobalEnum.USAGE_COUNT_LIMIT_ERROR;
						String userTip = usageCountMarkResult.getExceedTip();
						Response failureResponse = Response.buildFailure(usageCountLimitError,userTip);
						outJson(response,failureResponse,usageCountLimitError.getHttpStatus());

						return;
					}
				}
			}
		}


		filterChain.doFilter(request,response);
	}

	protected void outJson(HttpServletResponse httpServletResponse,Response response,int httpStatus)  throws IOException{
		String toJsonStrForHttp = JsonTool.toJsonStrForHttp(response, JsonTool.getObjectMapper());
		httpServletResponse.setStatus(httpStatus);
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter out = httpServletResponse.getWriter();
		out.write(toJsonStrForHttp);
		out.flush();
		IoUtil.close(out);
	}

	/**
	 * usage count 标记器
	 */
	public static interface UsageCountMarker {
		/**
		 * 定义一个基础顺序，默认100，越小越靠前
		 */
		public static final int componentBaseOrder = 100;

		public UsageCountMarkResult mark(String requestURI, Long loginUserId, Long currentTenantId);
	}
	/**
	 * usage count 解析结果
	 */
	@Data
	public static class UsageCountMarkResult {

		/**
		 * 是否超出
		 */
		private Boolean isExceed;
		/**
		 * 超出提示
		 */
		private String exceedTip;


		public static UsageCountMarkResult create(Boolean isExceed, String exceedTip) {
			UsageCountMarkResult logoTextResolveResult = new UsageCountMarkResult();
			logoTextResolveResult.isExceed = isExceed;
			logoTextResolveResult.exceedTip = exceedTip;
			return logoTextResolveResult;
		}
	}
}
