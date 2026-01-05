package com.particle.component.autoconfigure.tenant.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.client.exception.ErrorCodeTenantEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
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
	private TenantUserRpcFeignClient tenantUserRpcFeignClient;
	@Autowired
	private TenantRpcFeignClient tenantRpcFeignClient;

	@Override
	public List<GrantedTenant> retrieveUserTenantByUserId(Long userId,Long limitedTenantId) {
        IdCommand userIdCommand = IdCommand.create(userId);
        MultiResponse<TenantUserVO> tenantUserVOMultiResponse = tenantUserRpcFeignClient.queryListByUserIdIgnoreTenantLimit(userIdCommand);
        List<TenantUserVO> tenantUserVOS = tenantUserVOMultiResponse.getData();
		if (tenantUserVOS == null) {
			return Collections.emptyList();
		}

		LocalDateTime now = LocalDateTime.now();

		Set<Long> tenantIds = tenantUserVOS.stream()
				// 没有被禁用
				.filter(item -> item.getIsExpired()==null || !item.getIsExpired())
				// 已生效
				.filter(item -> item.getEffectiveAt() == null || item.getEffectiveAt().isBefore(now))
				// 没到截止日期
				.filter(item -> item.getExpireAt() == null || item.getExpireAt().isAfter(now))
				// 没有被禁用
				.filter(item -> item.getIsLeave()==null || !item.getIsLeave())
				.map(TenantUserVO::getTenantId).collect(Collectors.toSet());

		if (tenantIds.isEmpty()) {
			// 如果为空，给一个合理的提示信息
            TenantUserVO tenantUserVO = null;
			if (limitedTenantId != null) {
				tenantUserVO = tenantUserVOS.stream().filter(item -> Objects.equals(limitedTenantId, item.getTenantId())).findFirst().orElse(null);
			}else {
				tenantUserVO = tenantUserVOS.iterator().next();
			}
			if (tenantUserVO == null) {
				return Collections.emptyList();
			}
			if(BooleanUtil.isTrue(tenantUserVO.getIsExpired())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_expired);
			}
			if(tenantUserVO.getEffectiveAt() != null && tenantUserVO.getEffectiveAt().isAfter(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_not_effective);

			}
			if(tenantUserVO.getExpireAt() != null && tenantUserVO.getExpireAt().isBefore(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_expired_limit);
			}
			if(BooleanUtil.isTrue(tenantUserVO.getIsLeave())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_leave);
			}
		}

		// 这里不需要租户过滤，因为在租户插件配置中已过滤了表
        TenantQueryAllCommand tenantQueryAllCommand = new TenantQueryAllCommand();
        tenantQueryAllCommand.setFilterTenantIds(new ArrayList<>(tenantIds));
        MultiResponse<TenantVO> tenantVOMultiResponse = tenantRpcFeignClient.getAllTenant(tenantQueryAllCommand);
        List<TenantVO> tenantVOS = tenantVOMultiResponse.getData();

		tenantVOS = filterAvailableTenantDOs(tenantVOS,now,limitedTenantId,false);
		return tenantVOS.stream()

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
	public static List<TenantVO> filterAvailableTenantDOs(List<TenantVO> tenantDOS,LocalDateTime now,Long limitedTenantId,boolean quietly) {
		if (CollectionUtil.isEmpty(tenantDOS)) {
			return Collections.emptyList();
		}

		List<TenantVO> tenantDOList = tenantDOS.stream()
				// 没有被禁用
				.filter(item -> item.getIsDisabled() == null || !item.getIsDisabled())
				// 已生效
				.filter(item -> item.getEffectiveAt() == null || item.getEffectiveAt().isBefore(now))
				// 没到截止日期
				.filter(item -> item.getExpireAt() == null || item.getExpireAt().isAfter(now))
				.collect(Collectors.toList());


		if (tenantDOList.isEmpty() && !quietly) {
			// 如果为空，给一个合理的提示信息
			TenantVO tenantVO = null;
			if (limitedTenantId != null) {
				tenantVO = tenantDOS.stream().filter(item -> Objects.equals(limitedTenantId, item.getId())).findFirst().orElse(null);
			}else {
				tenantVO = tenantDOS.iterator().next();
			}
			if (tenantVO == null) {
				return Collections.emptyList();
			}
			if(BooleanUtil.isTrue(tenantVO.getIsDisabled())){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_disabled);
			}
			if(tenantVO.getEffectiveAt() != null && tenantVO.getEffectiveAt().isAfter(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_not_effective);

			}
			if(tenantVO.getExpireAt() != null && tenantVO.getExpireAt().isBefore(now)){
				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_not_expired_limit);
			}

		}
		return tenantDOList;
	}
}
