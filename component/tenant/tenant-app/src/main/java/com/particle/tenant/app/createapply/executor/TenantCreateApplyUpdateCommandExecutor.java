package com.particle.tenant.app.createapply.executor;

import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
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
 * 租户创建申请 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantCreateApplyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;

	/**
	 * 执行 租户创建申请 更新指令
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyUpdateCommand(tenantCreateApplyUpdateCommand);
		tenantCreateApply.setUpdateControl(tenantCreateApplyUpdateCommand);
		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyUpdateCommand(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyUpdateCommand(tenantCreateApply, tenantCreateApplyUpdateCommand);
		return tenantCreateApply;
	}

	@Mapper
	interface TenantCreateApplyUpdateCommandToTenantCreateApplyMapping{
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping instance = Mappers.getMapper(TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.class );

		default TenantCreateApplyId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantCreateApplyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantCreateApply
		 * @param tenantCreateApplyUpdateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyUpdateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);
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
