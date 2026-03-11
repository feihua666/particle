package com.particle.component.autoconfigure.global.security.tenant.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.login.GrantedTenant;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.security.tenant.SecurityUserTenantService;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.client.exception.ErrorCodeTenantEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class SecurityUserTenantServiceImpl implements SecurityUserTenantService {

	@Autowired
	private TenantUserRpcFeignClient tenantUserRpcFeignClient;
	@Autowired
	private TenantRpcFeignClient tenantRpcFeignClient;

	@Override
	public List<GrantedTenant> retrieveUserTenantByUserId(Long userId) {
        CommonIdCommand userCommonIdCommand = CommonIdCommand.create(userId);
        MultiResponse<TenantUserVO> tenantUserVOMultiResponse = tenantUserRpcFeignClient.queryListByUserIdIgnoreTenantLimit(userCommonIdCommand);
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
		// 如果为空，给一个合理的提示信息
		if (tenantIds.isEmpty()) {
			throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_not_available_tenant);
		}

		// 这里不需要租户过滤，因为在租户插件配置中已过滤了表
        TenantQueryAllCommand tenantQueryAllCommand = new TenantQueryAllCommand();
        tenantQueryAllCommand.setFilterTenantIds(new ArrayList<>(tenantIds));
        MultiResponse<TenantRpcVO> tenantVOMultiResponse = tenantRpcFeignClient.getAllTenant(tenantQueryAllCommand);
        List<TenantRpcVO> tenantVOS = tenantVOMultiResponse.getData();

		tenantVOS = filterAvailableTenantDOs(tenantVOS,now,false);
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
	 * @param tenantVOS
	 * @param now
	 * @return
	 */
	public static List<TenantRpcVO> filterAvailableTenantDOs(List<TenantRpcVO> tenantVOS,
														  LocalDateTime now,
														  boolean quietly) {
		if (CollectionUtil.isEmpty(tenantVOS)) {
			return Collections.emptyList();
		}

		List<TenantRpcVO> tenantDOList = tenantVOS.stream()
				// 没有被禁用
				.filter(item -> item.getIsDisabled() == null || !item.getIsDisabled())
				// 已生效
				.filter(item -> item.getEffectiveAt() == null || item.getEffectiveAt().isBefore(now))
				// 没到截止日期
				.filter(item -> item.getExpireAt() == null || item.getExpireAt().isAfter(now))
				.collect(Collectors.toList());

		// 如果为空，给一个合理的提示信息
		if (tenantDOList.isEmpty() && !quietly) {

			throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_not_available_tenant);
		}
		return tenantDOList;
	}
}
