package com.particle.component.adapter.user.login;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.mapper.TenantMapper;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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

	private static String header_c_host = "c-host";

	/**
	 * 缓存，参数为0表示不限制缓存时间
	 */
	TimedCache<String, GrantedTenant> timedCache = CacheUtil.newTimedCache(0);

	private List<TenantDO> cachedTenantDOS;

	@Autowired
	private TenantMapper tenantMapper;

	@Override
	public GrantedTenant resolveGrantedTenant(ServletRequest request,boolean useLoginUser) {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null && useLoginUser) {
			return loginUser.getCurrentTenant();
		}
		String domainWithPort = ServletUtil.getHeaderIgnoreCase(((HttpServletRequest) request),header_c_host);
		if (Strings.isEmpty(domainWithPort)) {
			domainWithPort = RequestTool.getDomain(((HttpServletRequest) request), false, true);
		}


		return resolveGrantedTenant(domainWithPort);
	}

	@Override
	public GrantedTenant resolveGrantedTenant(String domainWithPort) {

		String domainOnly = domainWithPort.split(":")[0];

		if (cachedTenantDOS == null) {

			List<TenantDO> tenantDOS = getAllSimpleIgnoreTenantLimit();
			cachedTenantDOS = UserTenantServiceImpl.filterAvailableTenantDOs(tenantDOS, LocalDateTime.now(),null,true);
			if (cachedTenantDOS == null) {
				return null;
			}
		}

		return timedCache.get(domainWithPort, () -> {
			for (TenantDO cachedTenantDO : cachedTenantDOS) {
				String tenantDomain = cachedTenantDO.getTenantDomain();
				if (Strings.isEmpty(tenantDomain)) {
					continue;
				}
				for (String domain : tenantDomain.split(",")) {
					if (StrUtil.equalsAny(domain,domainOnly,domainWithPort)) {
						return GrantedTenant.create(cachedTenantDO.getId(),
								cachedTenantDO.getCode(),
								cachedTenantDO.getName(),
								cachedTenantDO.getTenantThemeJson(),
								cachedTenantDO.getTenantDefaultRouteJson(),
								cachedTenantDO.getTenantLogoJson(),
								cachedTenantDO.getConfigJson(),
								cachedTenantDO.getIsFormal()
						);

					}
				}
			}
			return null;
		});	}

	@Override
	public void removeCache() {
		cachedTenantDOS = null;
		timedCache.clear();
	}


	private List<TenantDO> getAllSimpleIgnoreTenantLimit() {
		try {
			// 设置忽略租户插件
			InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
			return tenantMapper.selectList(Wrappers.<TenantDO>lambdaQuery()
					.select(TenantDO::getId,TenantDO::getCode,TenantDO::getName,TenantDO::getTenantDomain,TenantDO::getIsDisabled));
		} finally {
			InterceptorIgnoreHelper.clearIgnoreStrategy();
		}
	}
}
