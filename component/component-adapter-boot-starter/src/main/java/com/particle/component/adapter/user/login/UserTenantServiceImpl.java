package com.particle.component.adapter.user.login;

import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 获取用户租户信息实现服务
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 11:39
 */
public class UserTenantServiceImpl implements UserTenantService {

	@Autowired
	private ITenantUserService iTenantUserService;
	@Autowired
	private ITenantService iTenantService;

	@Override
	public List<GrantedTenant> retrieveUserTenantByUserId(Long userId) {
		List<TenantUserDO> tenantUserDOS = iTenantUserService.getByUserIdIgnoreTenantId(userId);
		if (tenantUserDOS == null) {
			return Collections.emptyList();
		}
		Set<Long> tenantIds = tenantUserDOS.stream().filter(item -> !item.getIsExpired()).map(TenantUserDO::getTenantId).collect(Collectors.toSet());

		if (tenantIds.isEmpty()) {
			return Collections.emptyList();
		}

		// 这里不需要租户过滤，因为在租户插件配置中已过滤了表
		List<TenantDO> tenantDOS = iTenantService.getByIdsIgnoreTenantLimit(tenantIds);

		return tenantDOS.stream().filter(item -> !item.getIsDisabled()).map(item -> GrantedTenant.create(item.getId(), item.getCode(), item.getName())).collect(Collectors.toList());

	}
}