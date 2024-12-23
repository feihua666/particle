package com.particle.tenant.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.gateway.TenantGateway;
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
 * 租户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Component
@Validated
public class TenantCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantGateway tenantGateway;

	/**
	 * 执行租户添加指令
	 * @param tenantCreateCommand
	 * @return
	 */
	public SingleResponse<TenantVO> execute(@Valid TenantCreateCommand tenantCreateCommand) {
		Tenant tenant = createByTenantCreateCommand(tenantCreateCommand);
		tenant.setAddControl(tenantCreateCommand);
		boolean save = tenantGateway.save(tenant);
		if (save) {
			return SingleResponse.of(TenantAppStructMapping.instance.toTenantVO(tenant));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户创建指令创建租户模型
	 * @param tenantCreateCommand
	 * @return
	 */
	private Tenant createByTenantCreateCommand(TenantCreateCommand tenantCreateCommand){
		Tenant tenant = Tenant.create();
		TenantCreateCommandToTenantMapping.instance.fillTenantByTenantCreateCommand(tenant, tenantCreateCommand);
		return tenant;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TenantCreateCommandToTenantMapping{
		TenantCreateCommandToTenantMapping instance = Mappers.getMapper( TenantCreateCommandToTenantMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenant
		 * @param tenantCreateCommand
		 */
		void fillTenantByTenantCreateCommand(@MappingTarget Tenant tenant, TenantCreateCommand tenantCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantGateway
	 */
	@Autowired
	public void setTenantGateway(TenantGateway tenantGateway) {
		this.tenantGateway = tenantGateway;
	}
}
