package com.particle.component.adapter.user.login;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

	private List<TenantDO> cachedTenantDOS;

	@Autowired
	private ITenantService iTenantService;

	@Override
	public GrantedTenant resolveGrantedTenant(ServletRequest request) {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null) {
			return loginUser.getCurrentTenant();
		}
		String domainWidthPort = RequestTool.getDomain(((HttpServletRequest) request), false, true);
		String domainOnly = domainWidthPort.split(":")[0];

		if (cachedTenantDOS == null) {
			cachedTenantDOS = iTenantService.getAllSimpleIgnoreTenantLimit();
			if (cachedTenantDOS == null) {
				return null;
			}
		}

		return timedCache.get(domainWidthPort, () -> {
			for (TenantDO cachedTenantDO : cachedTenantDOS) {
				String tenantDomain = cachedTenantDO.getTenantDomain();
				if (Strings.isEmpty(tenantDomain)) {
					return null;
				}

				if (StrUtil.equalsAny(tenantDomain,domainOnly,domainWidthPort)) {
					return GrantedTenant.create(cachedTenantDO.getId(), cachedTenantDO.getCode(), cachedTenantDO.getName());

				}

			}
			return null;
		});

	}

	@Override
	public void removeCache() {
		cachedTenantDOS = null;
		timedCache.clear();
	}
}
