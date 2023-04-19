package com.particle.tenant.app.executor;

import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.dto.command.TenantUserUpdateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
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
 * 租户用户 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantUserUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserGateway tenantUserGateway;

	/**
	 * 执行 租户用户 更新指令
	 * @param tenantUserUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> execute(@Valid TenantUserUpdateCommand tenantUserUpdateCommand) {
		TenantUser tenantUser = createByTenantUserUpdateCommand(tenantUserUpdateCommand);
		tenantUser.changeLeaveAtToCurrentIfNullAndHasLeft();

		tenantUser.setUpdateControl(tenantUserUpdateCommand);
		boolean save = tenantUserGateway.save(tenantUser);
		if (save) {
			return SingleResponse.of(TenantUserAppStructMapping.instance.toTenantUserVO(tenantUser));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantUserUpdateCommand
	 * @return
	 */
	private TenantUser createByTenantUserUpdateCommand(TenantUserUpdateCommand tenantUserUpdateCommand){
		TenantUser tenantUser = TenantUser.create();
		TenantUserUpdateCommandToTenantUserMapping.instance.fillTenantUserByTenantUserUpdateCommand(tenantUser, tenantUserUpdateCommand);
		return tenantUser;
	}

	@Mapper
	interface TenantUserUpdateCommandToTenantUserMapping{
		TenantUserUpdateCommandToTenantUserMapping instance = Mappers.getMapper(TenantUserUpdateCommandToTenantUserMapping.class );

		default TenantUserId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantUserId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUser
		 * @param tenantUserUpdateCommand
		 */
		void fillTenantUserByTenantUserUpdateCommand(@MappingTarget TenantUser tenantUser, TenantUserUpdateCommand tenantUserUpdateCommand);
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
