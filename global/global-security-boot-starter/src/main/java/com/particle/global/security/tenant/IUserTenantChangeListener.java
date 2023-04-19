package com.particle.global.security.tenant;

/**
 * <p>
 * 当前用户租户改变临时
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 15:53
 */
public interface IUserTenantChangeListener {


	/**
	 * 租户变更通知
	 * @param newGrantedTenant 为null时表示清空
	 * @param oldGrantedTenant 有可能为null这是用户租户从无到有的过程
	 */
	public void onTenantChanged(GrantedTenant newGrantedTenant, GrantedTenant oldGrantedTenant);
}
