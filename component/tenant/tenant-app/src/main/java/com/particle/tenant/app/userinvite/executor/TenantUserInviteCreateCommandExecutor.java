package com.particle.tenant.app.userinvite.executor;

import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteCreateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户用户邀请 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Component
@Validated
public class TenantUserInviteCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteGateway tenantUserInviteGateway;

	/**
	 * 执行租户用户邀请添加指令
	 * @param tenantUserInviteCreateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteVO> execute(@Valid TenantUserInviteCreateCommand tenantUserInviteCreateCommand) {
		TenantUserInvite tenantUserInvite = createByTenantUserInviteCreateCommand(tenantUserInviteCreateCommand);
		tenantUserInvite.setAddControl(tenantUserInviteCreateCommand);
		boolean save = tenantUserInviteGateway.save(tenantUserInvite);
		if (save) {
			return SingleResponse.of(TenantUserInviteAppStructMapping.instance.toTenantUserInviteVO(tenantUserInvite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户邀请创建指令创建租户用户邀请模型
	 * @param tenantUserInviteCreateCommand
	 * @return
	 */
	private TenantUserInvite createByTenantUserInviteCreateCommand(TenantUserInviteCreateCommand tenantUserInviteCreateCommand){
		TenantUserInvite tenantUserInvite = TenantUserInvite.create();
		TenantUserInviteCreateCommandToTenantUserInviteMapping.instance.fillTenantUserInviteByTenantUserInviteCreateCommand(tenantUserInvite, tenantUserInviteCreateCommand);
		return tenantUserInvite;
	}

	@Mapper
	interface  TenantUserInviteCreateCommandToTenantUserInviteMapping{
		TenantUserInviteCreateCommandToTenantUserInviteMapping instance = Mappers.getMapper( TenantUserInviteCreateCommandToTenantUserInviteMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUserInvite
		 * @param tenantUserInviteCreateCommand
		 */
		void fillTenantUserInviteByTenantUserInviteCreateCommand(@MappingTarget TenantUserInvite tenantUserInvite, TenantUserInviteCreateCommand tenantUserInviteCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserInviteGateway
	 */
	@Autowired
	public void setTenantUserInviteGateway(TenantUserInviteGateway tenantUserInviteGateway) {
		this.tenantUserInviteGateway = tenantUserInviteGateway;
	}
}
