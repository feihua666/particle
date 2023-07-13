package com.particle.tenant.app.userinvite.executor;

import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUpdateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
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
 * 租户用户邀请 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantUserInviteUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteGateway tenantUserInviteGateway;

	/**
	 * 执行 租户用户邀请 更新指令
	 * @param tenantUserInviteUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteVO> execute(@Valid TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand) {
		TenantUserInvite tenantUserInvite = createByTenantUserInviteUpdateCommand(tenantUserInviteUpdateCommand);
		tenantUserInvite.setUpdateControl(tenantUserInviteUpdateCommand);
		boolean save = tenantUserInviteGateway.save(tenantUserInvite);
		if (save) {
			return SingleResponse.of(TenantUserInviteAppStructMapping.instance.toTenantUserInviteVO(tenantUserInvite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户邀请更新指令创建租户用户邀请模型
	 * @param tenantUserInviteUpdateCommand
	 * @return
	 */
	private TenantUserInvite createByTenantUserInviteUpdateCommand(TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand){
		TenantUserInvite tenantUserInvite = TenantUserInvite.create();
		TenantUserInviteUpdateCommandToTenantUserInviteMapping.instance.fillTenantUserInviteByTenantUserInviteUpdateCommand(tenantUserInvite, tenantUserInviteUpdateCommand);
		return tenantUserInvite;
	}

	@Mapper
	interface TenantUserInviteUpdateCommandToTenantUserInviteMapping{
		TenantUserInviteUpdateCommandToTenantUserInviteMapping instance = Mappers.getMapper(TenantUserInviteUpdateCommandToTenantUserInviteMapping.class );

		default TenantUserInviteId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantUserInviteId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUserInvite
		 * @param tenantUserInviteUpdateCommand
		 */
		void fillTenantUserInviteByTenantUserInviteUpdateCommand(@MappingTarget TenantUserInvite tenantUserInvite, TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand);
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
