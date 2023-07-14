package com.particle.global.security.tenant;

import org.apache.logging.log4j.util.Strings;

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
	 * 清除缓存
	 */
	default public void removeCache(){}

	/**
	 * 处理用户租户
	 * @param request
	 * @return
	 */
	public GrantedTenant resolveGrantedTenant(ServletRequest request,boolean useLoginUser);
	/**
	 * 处理用户租户
	 * @param domainWithPort 如：localhost:8080、api.bds.com
	 * @return
	 */
	public GrantedTenant resolveGrantedTenant(String domainWithPort);

	/**
	 * 兼容两种
	 * @param fallbackRequest
	 * @param domainWithPort
	 * @return
	 */
	default public GrantedTenant resolveGrantedTenant(ServletRequest fallbackRequest,boolean useLoginUser,String domainWithPort) {
		if (Strings.isEmpty(domainWithPort)) {
			return resolveGrantedTenant(fallbackRequest,useLoginUser);
		}
		return resolveGrantedTenant(domainWithPort);
	}
}
