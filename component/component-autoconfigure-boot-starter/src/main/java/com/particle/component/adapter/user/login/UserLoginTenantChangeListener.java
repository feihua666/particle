package com.particle.component.adapter.user.login;

import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.IUserTenantChangeListener;
import com.particle.global.security.tenant.TenantTool;

/**
 * <p>
 * 用户登录时租户改变处理
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 15:57
 */
public class UserLoginTenantChangeListener implements IUserTenantChangeListener {
	@Override
	public void onTenantChanged(GrantedTenant newGrantedTenant, GrantedTenant oldGrantedTenant) {
		if (newGrantedTenant != null) {
			TenantTool.setTenantId(newGrantedTenant.getId());
		}else {
			TenantTool.clear();
		}
	}
}
