package com.particle.usagecount.adapter.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.security.security.login.DefaultAuthenticationSuccessHandler;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

	@Autowired
	private IUsageCountRecordApplicationService iUsageCountRecordApplicationService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 请求接口地址
		String requestURI = request.getRequestURI();
		String contextPath = request.getServletContext().getContextPath();
		if (StrUtil.isNotEmpty(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
		}
		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null) {

			UsageCountRecordMarkCommand usageCountRecordMarkCommand = new UsageCountRecordMarkCommand();
			usageCountRecordMarkCommand.setUrlPattern(requestURI);
			usageCountRecordMarkCommand.setCurrentUserId(loginUser.getId());
			if (loginUser.getCurrentTenant() != null) {
				usageCountRecordMarkCommand.setCurrentTenantId(loginUser.getCurrentTenant().getId());
			}
			SingleResponse<UsageCountRecordMarkVO> mark = iUsageCountRecordApplicationService.mark(usageCountRecordMarkCommand);
			log.debug("usage count filter mark result = {}",JsonTool.toJsonStr(mark));
			if (mark.getData() != null) {
				if (mark.getData().getIsExceed()) {
					ErrorCodeGlobalEnum usageCountLimitError = ErrorCodeGlobalEnum.USAGE_COUNT_LIMIT_ERROR;
					String userTip = mark.getData().getExceedTip();
					Response failureResponse = Response.buildFailure(usageCountLimitError,userTip);
					outJson(response,failureResponse,usageCountLimitError.getHttpStatus());

					return;
				}
			}
		}

		filterChain.doFilter(request,response);
	}

	protected void outJson(HttpServletResponse httpServletResponse,Response response,int httpStatus)  throws IOException{
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
		String toJsonStrForHttp = JsonTool.toJsonStrForHttp(response, jackson2HttpMessageConverter.getObjectMapper(), DefaultAuthenticationSuccessHandler.class);
		httpServletResponse.setStatus(httpStatus);
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter out = httpServletResponse.getWriter();
		out.write(toJsonStrForHttp);
		out.flush();
		IoUtil.close(out);
	}
}
