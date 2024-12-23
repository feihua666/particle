package com.particle.tenant.app.tenantfuncapplication.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.tenantfuncapplication.structmapping.TenantFuncApplicationAppStructMapping;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 租户功能应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Component
@Validated
public class TenantFuncApplicationDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncApplicationGateway tenantFuncApplicationGateway;

	/**
	 * 执行 租户功能应用 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantFuncApplicationVO> execute(@Valid IdCommand deleteCommand) {
		TenantFuncApplicationId tenantFuncApplicationId = TenantFuncApplicationId.of(deleteCommand.getId());
		TenantFuncApplication byId = tenantFuncApplicationGateway.getById(tenantFuncApplicationId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantFuncApplicationGateway.delete(tenantFuncApplicationId,deleteCommand);
		if (delete) {
			return SingleResponse.of(TenantFuncApplicationAppStructMapping.instance.toTenantFuncApplicationVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantFuncApplicationGateway
	 */
	@Autowired
	public void setTenantFuncApplicationGateway(TenantFuncApplicationGateway tenantFuncApplicationGateway) {
		this.tenantFuncApplicationGateway = tenantFuncApplicationGateway;
	}
}
