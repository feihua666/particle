package com.particle.global.security.security.login;

import javax.servlet.ServletRequest;

/**
 * <p>
 * 在用户初始化成功后调用
 * 可用来统计用户在同一个登录会话下的行为等监听
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 18:08
 */
public interface SecurityFilterPersistentLoginUserReadyListener {

	/**
	 * 可以通过 {@link LoginUserTool} 获取登录用户
	 * @param request
	 */
	void onLoginUserReady(ServletRequest request);
}
