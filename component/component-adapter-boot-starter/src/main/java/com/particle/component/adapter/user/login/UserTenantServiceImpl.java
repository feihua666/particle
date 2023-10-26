package com.particle.component.adapter.user.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.tenant.client.exception.ErrorCodeTenantEnum;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
	public List<GrantedTenant> retrieveUserTenantByUserId(Long userId,Long limitedTenantId) {
		List<TenantUserDO> tenantUserDOS = iTenantUserService.getByUserIdIgnoreTenantLimit(userId);
		if (tenantUserDOS == null) {
			return Collections.emptyList();
		}

		LocalDateTime now = LocalDateTime.now();

		Set<Long> tenantIds = tenantUserDOS.stream()
				// 没有被禁用
				.filter(item -> item.getIsExpired()==null || !item.getIsExpired())
				// 已生效
				.filter(item -> item.getEffectiveAt() == null || item.getEffectiveAt().isBefore(now))
				// 没到截止日期
				.filter(item -> item.getExpireAt() == null || item.getExpireAt().isAfter(now))
				// 没有被禁用
				.filter(item -> item.getIsLeave()==null || !item.getIsLeave())
				.map(TenantUserDO::getTenantId).collect(Collectors.toSet());

		if (tenantIds.isEmpty()) {
			// 如果为空，给一个合理的提示信息
			TenantUserDO tenantUserDO = null;
			if (limitedTenantId != null) {
				tenantUserDO = tenantUserDOS.stream().filter(item -> Objects.equals(limitedTenantId, item.getTenantId())).findFirst().orElse(null);
			}else {
				tenantUserDO = tenantUserDOS.iterator().next();
			}
			if (tenantUserDO == null) {
				return Collections.emptyList();
			}
			if(BooleanUtil.isTrue(tenantUserDO.getIsExpired())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_expired);
			}
			if(tenantUserDO.getEffectiveAt() != null && tenantUserDO.getEffectiveAt().isAfter(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_not_effective);

			}
			if(tenantUserDO.getExpireAt() != null && tenantUserDO.getExpireAt().isBefore(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_expired_limit);
			}
			if(BooleanUtil.isTrue(tenantUserDO.getIsLeave())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_leave);
			}
		}

		// 这里不需要租户过滤，因为在租户插件配置中已过滤了表
		List<TenantDO> tenantDOS = iTenantService.getByIdsIgnoreTenantLimit(tenantIds);

		tenantDOS = filterAvailableTenantDOs(tenantDOS,now,limitedTenantId,false);
		return tenantDOS.stream()

				.map(item -> GrantedTenant.create(item.getId(), item.getCode(), item.getName(),
						item.getTenantThemeJson(),
						item.getTenantDefaultRouteJson(),
						item.getTenantLogoJson(),
						item.getConfigJson(),
						item.getIsFormal()))
				.collect(Collectors.toList());

	}


	/**
	 * 提供一个工具过滤可用的
	 * @param tenantDOS
	 * @param now
	 * @return
	 */
	public static List<TenantDO> filterAvailableTenantDOs(List<TenantDO> tenantDOS,LocalDateTime now,Long limitedTenantId,boolean quietly) {
		if (CollectionUtil.isEmpty(tenantDOS)) {
			return Collections.emptyList();
		}

		List<TenantDO> tenantDOList = tenantDOS.stream()
				// 没有被禁用
				.filter(item -> item.getIsDisabled() == null || !item.getIsDisabled())
				// 已生效
				.filter(item -> item.getEffectiveAt() == null || item.getEffectiveAt().isBefore(now))
				// 没到截止日期
				.filter(item -> item.getExpireAt() == null || item.getExpireAt().isAfter(now))
				.collect(Collectors.toList());


		if (tenantDOList.isEmpty() && !quietly) {
			// 如果为空，给一个合理的提示信息
			TenantDO tenantDO = null;
			if (limitedTenantId != null) {
				tenantDO = tenantDOList.stream().filter(item -> Objects.equals(limitedTenantId, item.getId())).findFirst().orElse(null);
			}else {
				tenantDO = tenantDOList.iterator().next();
			}
			if (tenantDO == null) {
				return Collections.emptyList();
			}
			if(BooleanUtil.isTrue(tenantDO.getIsDisabled())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_disabled);
			}
			if(tenantDO.getEffectiveAt() != null && tenantDO.getEffectiveAt().isAfter(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_not_effective);

			}
			if(tenantDO.getExpireAt() != null && tenantDO.getExpireAt().isBefore(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_not_expired_limit);
			}

		}
		return tenantDOList;
	}
}
