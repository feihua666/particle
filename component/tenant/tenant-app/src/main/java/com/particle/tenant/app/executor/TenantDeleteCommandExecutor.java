package com.particle.tenant.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
import com.particle.tenant.domain.gateway.TenantGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Component
@Validated
public class TenantDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantGateway tenantGateway;

	/**
	 * 执行 租户 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantVO> execute(@Valid IdCommand deleteCommand) {
		TenantId tenantId = TenantId.of(deleteCommand.getId());
		Tenant byId = tenantGateway.getById(tenantId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantGateway.delete(tenantId);
		if (delete) {
			return SingleResponse.of(TenantAppStructMapping.instance.toTenantVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantGateway
	 */
	@Autowired
	public void setTenantGateway(TenantGateway tenantGateway) {
		this.tenantGateway = tenantGateway;
	}
}
