package com.particle.tenant.app.tenantfunc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.tenantfunc.structmapping.TenantFuncAppStructMapping;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户功能菜单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Component
@Validated
public class TenantFuncDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncGateway tenantFuncGateway;

	/**
	 * 执行 租户功能菜单 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantFuncVO> execute(@Valid IdCommand deleteCommand) {
		TenantFuncId tenantFuncId = TenantFuncId.of(deleteCommand.getId());
		TenantFunc byId = tenantFuncGateway.getById(tenantFuncId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantFuncGateway.delete(tenantFuncId);
		if (delete) {
			return SingleResponse.of(TenantFuncAppStructMapping.instance.toTenantFuncVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantFuncGateway
	 */
	@Autowired
	public void setTenantFuncGateway(TenantFuncGateway tenantFuncGateway) {
		this.tenantFuncGateway = tenantFuncGateway;
	}
}
