package com.particle.tenant.adapter.login;

import cn.hutool.core.collection.CollectionUtil;
import com.github.xiaoymin.knife4j.core.util.Assert;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.tenant.TenantTool;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.data.TenantCurrentVO;
import com.particle.tenant.client.dto.data.TenantLoginVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能，登录用户相关
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 16:13
 */
@RestController
@RequestMapping("/tenant/login")
@Tag(name = "租户功能，登录用户相关")
public class TenantLoginController {

	@Autowired
	private ITenantUserService tenantUserService;
	@Autowired
	private ITenantService tenantService;

	@Operation(summary = "获取当前登录用户的租户信息")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/tenantInfo")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<TenantLoginVO> tenantInfo(@Parameter(description = "租户id，默认当前正在使用的租户") Long tenantId, @Parameter(description = "是否过期，true=过期，false=不过期，不传=全部") Boolean isExpired, @Parameter(hidden = true) LoginUser loginUser) {
		if (CollectionUtil.isEmpty(loginUser.getTenants())) {
			return SingleResponse.buildSuccess();
		}
		if (tenantId == null) {
			tenantId = loginUser.getCurrentTenant().getId();
		}else {
			Long finalTenantId = tenantId;
			Assert.isTrue(loginUser.getTenants().stream().anyMatch(item -> finalTenantId.equals(item.getId())),"tenantId 不合法");
		}
		TenantDO tenantDO = tenantService.getById(tenantId);
		TenantVO tenantVO = TenantAppStructMapping.instance.tenantDOToTenantVO(tenantDO);
		TenantLoginVO tenantLoginVO = TenantAppStructMapping.instance.TenantVOToTenantLoginVO(tenantVO);
		int userCount = (int)tenantUserService.countByTenantIdIgnoreTenantLimit(tenantId,false, isExpired);
		tenantLoginVO.setUserCount(userCount);

		return SingleResponse.of(tenantLoginVO);
	}

	@Operation(summary = "获取当前租户信息")
	@GetMapping("/currentTenant")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<TenantCurrentVO> currentTenant() {
		Long tenantId = TenantTool.getTenantId();
		if (tenantId == null) {
			return SingleResponse.buildSuccess();
		}
		TenantDO tenantDO = tenantService.getById(tenantId);
		TenantCurrentVO tenantVO = TenantAppStructMapping.instance.tenantDOToTenantCurrentVO(tenantDO);
		return SingleResponse.of(tenantVO);


	}
}
