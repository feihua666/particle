package com.particle.tenant.app.userinvite.executor;

import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteUserRecordAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordUpdateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteUserRecordGateway;
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
 * 租户用户邀请记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantUserInviteUserRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteUserRecordGateway tenantUserInviteUserRecordGateway;

	/**
	 * 执行 租户用户邀请记录 更新指令
	 * @param tenantUserInviteUserRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteUserRecordVO> execute(@Valid TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand) {
		TenantUserInviteUserRecord tenantUserInviteUserRecord = createByTenantUserInviteUserRecordUpdateCommand(tenantUserInviteUserRecordUpdateCommand);
		tenantUserInviteUserRecord.setUpdateControl(tenantUserInviteUserRecordUpdateCommand);
		boolean save = tenantUserInviteUserRecordGateway.save(tenantUserInviteUserRecord);
		if (save) {
			return SingleResponse.of(TenantUserInviteUserRecordAppStructMapping.instance.toTenantUserInviteUserRecordVO(tenantUserInviteUserRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户邀请记录更新指令创建租户用户邀请记录模型
	 * @param tenantUserInviteUserRecordUpdateCommand
	 * @return
	 */
	private TenantUserInviteUserRecord createByTenantUserInviteUserRecordUpdateCommand(TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand){
		TenantUserInviteUserRecord tenantUserInviteUserRecord = TenantUserInviteUserRecord.create();
		TenantUserInviteUserRecordUpdateCommandToTenantUserInviteUserRecordMapping.instance.fillTenantUserInviteUserRecordByTenantUserInviteUserRecordUpdateCommand(tenantUserInviteUserRecord, tenantUserInviteUserRecordUpdateCommand);
		return tenantUserInviteUserRecord;
	}

	@Mapper
	interface TenantUserInviteUserRecordUpdateCommandToTenantUserInviteUserRecordMapping{
		TenantUserInviteUserRecordUpdateCommandToTenantUserInviteUserRecordMapping instance = Mappers.getMapper(TenantUserInviteUserRecordUpdateCommandToTenantUserInviteUserRecordMapping.class );

		default TenantUserInviteUserRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantUserInviteUserRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUserInviteUserRecord
		 * @param tenantUserInviteUserRecordUpdateCommand
		 */
		void fillTenantUserInviteUserRecordByTenantUserInviteUserRecordUpdateCommand(@MappingTarget TenantUserInviteUserRecord tenantUserInviteUserRecord, TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserInviteUserRecordGateway
	 */
	@Autowired
	public void setTenantUserInviteUserRecordGateway(TenantUserInviteUserRecordGateway tenantUserInviteUserRecordGateway) {
		this.tenantUserInviteUserRecordGateway = tenantUserInviteUserRecordGateway;
	}
}
