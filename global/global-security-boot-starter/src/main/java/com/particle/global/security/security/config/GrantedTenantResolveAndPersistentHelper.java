package com.particle.global.security.security.config;

import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.security.tenant.IUserTenantChangeListener;
import com.particle.global.security.tenant.TenantTool;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 租户相关处理帮助类
 * </p>
 *
 * @author yangwei
 * @since 2023-07-13 14:24
 */
public class GrantedTenantResolveAndPersistentHelper {

	@Autowired
	@Setter
	private List<IUserTenantChangeListener> iUserTenantChangeListeners;
	@Autowired
	@Setter
	private ITenantResolveService iTenantResolveService;


	/**
	 * 如果不存在尝试解析并持久化
	 * @param request
	 */
	public void resolveAndPersistentIfNotExist(ServletRequest request) {
		Long tenantId = TenantTool.getTenantId();
		if (tenantId == null) {
			resolveAndPersistent(request);
		}
	}

	/**
	 * 解析并持久到线程变量
	 * @param request
	 */
	public void resolveAndPersistent(ServletRequest request) {
		// 处理租户并设置到线程变量
		if (iUserTenantChangeListeners != null && iTenantResolveService != null) {
			GrantedTenant grantedTenant = iTenantResolveService.resolveGrantedTenant(request,true);
			for (IUserTenantChangeListener iUserTenantChangeListener : iUserTenantChangeListeners) {
				iUserTenantChangeListener.onTenantChanged(grantedTenant, null);
			}
		}
	}

	/**
	 * 删除持久化
	 */
	public void removeFromPersistent() {
		if (iUserTenantChangeListeners != null) {
			for (IUserTenantChangeListener iUserTenantChangeListener : iUserTenantChangeListeners) {
				iUserTenantChangeListener.onTenantChanged(null, null);
			}
		}
	}
}
