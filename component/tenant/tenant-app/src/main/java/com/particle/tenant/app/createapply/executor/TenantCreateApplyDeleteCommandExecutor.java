package com.particle.tenant.app.createapply.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户创建申请 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Component
@Validated
public class TenantCreateApplyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;

	/**
	 * 执行 租户创建申请 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid IdCommand deleteCommand) {
		TenantCreateApplyId tenantCreateApplyId = TenantCreateApplyId.of(deleteCommand.getId());
		TenantCreateApply byId = tenantCreateApplyGateway.getById(tenantCreateApplyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantCreateApplyGateway.delete(tenantCreateApplyId);
		if (delete) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}
}
