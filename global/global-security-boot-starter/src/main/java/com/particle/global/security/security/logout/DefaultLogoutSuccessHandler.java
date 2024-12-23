package com.particle.global.security.security.logout;

import cn.hutool.core.io.IoUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.json.JsonTool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 退出登录回调
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 19:32
 */
public class DefaultLogoutSuccessHandler extends DefaultAbstractLogoutSuccessHandler implements LogoutSuccessHandler {

	private SecurityContextLogoutHandler securityContextLogoutHandler;

	public DefaultLogoutSuccessHandler(SecurityContextLogoutHandler securityContextLogoutHandler) {
		this.securityContextLogoutHandler = securityContextLogoutHandler;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter out = response.getWriter();
		Response responseResult = Response.buildSuccess();

		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
		String toJsonStrForHttp = JsonTool.toJsonStrForHttp(responseResult, jackson2HttpMessageConverter.getObjectMapper());
		out.write(toJsonStrForHttp);
		out.flush();
		IoUtil.close(out);
		super.tryNotifyILogoutSuccessResultServiceOnSuccess(request,response,authentication,responseResult);


		if (securityContextLogoutHandler != null) {
			securityContextLogoutHandler.logout(request,response,authentication);
		}
		LoginUserTool.clear();
		LoginUserTool.removeFromSession(request);
	}
}
