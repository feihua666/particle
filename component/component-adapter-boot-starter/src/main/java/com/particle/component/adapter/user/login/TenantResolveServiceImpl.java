package com.particle.component.adapter.user.login;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 租户处理实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 17:41
 */
public class TenantResolveServiceImpl implements ITenantResolveService {

	/**
	 * 缓存两小时
	 */
	TimedCache<String, GrantedTenant> timedCache = CacheUtil.newTimedCache(2 * 60 * 60 * 1000);

	@Autowired
	private ITenantService iTenantService;

	@Override
	public GrantedTenant resolveGrantedTenant(ServletRequest request) {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null) {
			return loginUser.getCurrentTenant();
		}
		String domain = RequestTool.getDomain(((HttpServletRequest) request), false, false);

		return timedCache.get(domain, () -> {

			TenantDO byTenantDomain = iTenantService.getByTenantDomain(domain);
			if (byTenantDomain == null) {
				return null;
			}
			return GrantedTenant.create(byTenantDomain.getId(), byTenantDomain.getCode(), byTenantDomain.getName());
		});

	}
}
