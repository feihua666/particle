package com.particle.global.security.security.logout;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * <p>
 * 自定义登录处理器，主要是处理登出成功后逻辑
 * 主要是为了方便以后扩展，避免直接实现  {@link LogoutSuccessHandler}
 * 直接实现该接口即可
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 19:34
 */
public interface ILogoutSuccessResultService {

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
