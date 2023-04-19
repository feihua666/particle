package com.particle.tenant.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
import com.particle.tenant.domain.gateway.TenantUserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
@Validated
public class TenantUserDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserGateway tenantUserGateway;

	/**
	 * 执行 租户用户 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> execute(@Valid IdCommand deleteCommand) {
		TenantUserId tenantUserId = TenantUserId.of(deleteCommand.getId());
		TenantUser byId = tenantUserGateway.getById(tenantUserId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantUserGateway.delete(tenantUserId);
		if (delete) {
			return SingleResponse.of(TenantUserAppStructMapping.instance.toTenantUserVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserGateway
	 */
	@Autowired
	public void setTenantUserGateway(TenantUserGateway tenantUserGateway) {
		this.tenantUserGateway = tenantUserGateway;
	}
}
