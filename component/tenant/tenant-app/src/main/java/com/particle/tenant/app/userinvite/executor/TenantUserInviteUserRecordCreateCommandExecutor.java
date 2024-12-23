package com.particle.tenant.app.userinvite.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteUserRecordAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordCreateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteUserRecordGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 租户用户邀请记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Component
@Validated
public class TenantUserInviteUserRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteUserRecordGateway tenantUserInviteUserRecordGateway;

	/**
	 * 执行租户用户邀请记录添加指令
	 * @param tenantUserInviteUserRecordCreateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteUserRecordVO> execute(@Valid TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand) {
		TenantUserInviteUserRecord tenantUserInviteUserRecord = createByTenantUserInviteUserRecordCreateCommand(tenantUserInviteUserRecordCreateCommand);
		tenantUserInviteUserRecord.setAddControl(tenantUserInviteUserRecordCreateCommand);
		boolean save = tenantUserInviteUserRecordGateway.save(tenantUserInviteUserRecord);
		if (save) {
			return SingleResponse.of(TenantUserInviteUserRecordAppStructMapping.instance.toTenantUserInviteUserRecordVO(tenantUserInviteUserRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户邀请记录创建指令创建租户用户邀请记录模型
	 * @param tenantUserInviteUserRecordCreateCommand
	 * @return
	 */
	private TenantUserInviteUserRecord createByTenantUserInviteUserRecordCreateCommand(TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand){
		TenantUserInviteUserRecord tenantUserInviteUserRecord = TenantUserInviteUserRecord.create();
		TenantUserInviteUserRecordCreateCommandToTenantUserInviteUserRecordMapping.instance.fillTenantUserInviteUserRecordByTenantUserInviteUserRecordCreateCommand(tenantUserInviteUserRecord, tenantUserInviteUserRecordCreateCommand);
		return tenantUserInviteUserRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TenantUserInviteUserRecordCreateCommandToTenantUserInviteUserRecordMapping{
		TenantUserInviteUserRecordCreateCommandToTenantUserInviteUserRecordMapping instance = Mappers.getMapper( TenantUserInviteUserRecordCreateCommandToTenantUserInviteUserRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUserInviteUserRecord
		 * @param tenantUserInviteUserRecordCreateCommand
		 */
		void fillTenantUserInviteUserRecordByTenantUserInviteUserRecordCreateCommand(@MappingTarget TenantUserInviteUserRecord tenantUserInviteUserRecord, TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand);
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
