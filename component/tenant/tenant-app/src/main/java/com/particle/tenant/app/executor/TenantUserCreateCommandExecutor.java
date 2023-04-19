package com.particle.tenant.app.executor;

import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.gateway.TenantUserGateway;
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
 * 租户用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
@Validated
public class TenantUserCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserGateway tenantUserGateway;

	/**
	 * 执行租户用户添加指令
	 * @param tenantUserCreateCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> execute(@Valid TenantUserCreateCommand tenantUserCreateCommand) {
		TenantUser tenantUser = createByTenantUserCreateCommand(tenantUserCreateCommand);
		tenantUser.changeJoinAtToCurrent();
		tenantUser.changeIsLeaveToFalseIfNull();

		tenantUser.setAddControl(tenantUserCreateCommand);
		boolean save = tenantUserGateway.save(tenantUser);
		if (save) {
			return SingleResponse.of(TenantUserAppStructMapping.instance.toTenantUserVO(tenantUser));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户创建指令创建租户用户模型
	 * @param tenantUserCreateCommand
	 * @return
	 */
	private TenantUser createByTenantUserCreateCommand(TenantUserCreateCommand tenantUserCreateCommand){
		TenantUser tenantUser = TenantUser.create();
		TenantUserCreateCommandToTenantUserMapping.instance.fillTenantUserByTenantUserCreateCommand(tenantUser, tenantUserCreateCommand);
		return tenantUser;
	}

	@Mapper
	interface  TenantUserCreateCommandToTenantUserMapping{
		TenantUserCreateCommandToTenantUserMapping instance = Mappers.getMapper( TenantUserCreateCommandToTenantUserMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUser
		 * @param tenantUserCreateCommand
		 */
		void fillTenantUserByTenantUserCreateCommand(@MappingTarget TenantUser tenantUser, TenantUserCreateCommand tenantUserCreateCommand);
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
