package com.particle.tenant.app.tenantfuncapplication.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.tenantfuncapplication.structmapping.TenantFuncApplicationAppStructMapping;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationCreateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
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
 * 租户功能应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Component
@Validated
public class TenantFuncApplicationCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncApplicationGateway tenantFuncApplicationGateway;

	/**
	 * 执行租户功能应用添加指令
	 * @param tenantFuncApplicationCreateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncApplicationVO> execute(@Valid TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand) {
		TenantFuncApplication tenantFuncApplication = createByTenantFuncApplicationCreateCommand(tenantFuncApplicationCreateCommand);
		tenantFuncApplication.setAddControl(tenantFuncApplicationCreateCommand);
		boolean save = tenantFuncApplicationGateway.save(tenantFuncApplication);
		if (save) {
			return SingleResponse.of(TenantFuncApplicationAppStructMapping.instance.toTenantFuncApplicationVO(tenantFuncApplication));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户功能应用创建指令创建租户功能应用模型
	 * @param tenantFuncApplicationCreateCommand
	 * @return
	 */
	private TenantFuncApplication createByTenantFuncApplicationCreateCommand(TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand){
		TenantFuncApplication tenantFuncApplication = TenantFuncApplication.create();
		TenantFuncApplicationCreateCommandToTenantFuncApplicationMapping.instance.fillTenantFuncApplicationByTenantFuncApplicationCreateCommand(tenantFuncApplication, tenantFuncApplicationCreateCommand);
		return tenantFuncApplication;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TenantFuncApplicationCreateCommandToTenantFuncApplicationMapping{
		TenantFuncApplicationCreateCommandToTenantFuncApplicationMapping instance = Mappers.getMapper( TenantFuncApplicationCreateCommandToTenantFuncApplicationMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantFuncApplication
		 * @param tenantFuncApplicationCreateCommand
		 */
		void fillTenantFuncApplicationByTenantFuncApplicationCreateCommand(@MappingTarget TenantFuncApplication tenantFuncApplication, TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantFuncApplicationGateway
	 */
	@Autowired
	public void setTenantFuncApplicationGateway(TenantFuncApplicationGateway tenantFuncApplicationGateway) {
		this.tenantFuncApplicationGateway = tenantFuncApplicationGateway;
	}
}
