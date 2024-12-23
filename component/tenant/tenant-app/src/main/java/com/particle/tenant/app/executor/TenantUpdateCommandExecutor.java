package com.particle.tenant.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
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
 * 租户 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantGateway tenantGateway;

	/**
	 * 执行 租户 更新指令
	 * @param tenantUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantVO> execute(@Valid TenantUpdateCommand tenantUpdateCommand) {
		Tenant tenant = createByTenantUpdateCommand(tenantUpdateCommand);
		tenant.setUpdateControl(tenantUpdateCommand);
		boolean save = tenantGateway.save(tenant);
		if (save) {
			return SingleResponse.of(TenantAppStructMapping.instance.toTenantVO(tenant));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户更新指令创建租户模型
	 * @param tenantUpdateCommand
	 * @return
	 */
	private Tenant createByTenantUpdateCommand(TenantUpdateCommand tenantUpdateCommand){
		Tenant tenant = Tenant.create();
		TenantUpdateCommandToTenantMapping.instance.fillTenantByTenantUpdateCommand(tenant, tenantUpdateCommand);
		return tenant;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface TenantUpdateCommandToTenantMapping{
		TenantUpdateCommandToTenantMapping instance = Mappers.getMapper(TenantUpdateCommandToTenantMapping.class );

		default TenantId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenant
		 * @param tenantUpdateCommand
		 */
		void fillTenantByTenantUpdateCommand(@MappingTarget Tenant tenant, TenantUpdateCommand tenantUpdateCommand);
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
