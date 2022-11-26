package com.particle.global.security.security.logout;

import cn.hutool.core.io.IoUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.json.JsonTool;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		Response response1 = Response.buildSuccess();
		out.write(JsonTool.toJsonStr(response1));
		out.flush();
		IoUtil.close(out);
		super.tryNotifyILogoutSuccessResultServiceOnSuccess(request,response,authentication,response1);


		if (securityContextLogoutHandler != null) {
			securityContextLogoutHandler.logout(request,response,authentication);
		}
	}
}
