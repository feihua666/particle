package com.particle.global.security.tenant;

import javax.servlet.ServletRequest;

/**
 * <p>
 * 用户租户处理
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 17:37
 */
public interface ITenantResolveService {

	/**
	 * 处理用户租户
	 * @param request
	 * @return
	 */
	public GrantedTenant resolveGrantedTenant(ServletRequest request);
}
