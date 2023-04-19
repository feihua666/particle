package com.particle.tenant.app.tenantfuncapplication.executor;

import com.particle.tenant.app.tenantfuncapplication.structmapping.TenantFuncApplicationAppStructMapping;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationUpdateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
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
 * 租户功能应用 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantFuncApplicationUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncApplicationGateway tenantFuncApplicationGateway;

	/**
	 * 执行 租户功能应用 更新指令
	 * @param tenantFuncApplicationUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncApplicationVO> execute(@Valid TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand) {
		TenantFuncApplication tenantFuncApplication = createByTenantFuncApplicationUpdateCommand(tenantFuncApplicationUpdateCommand);
		tenantFuncApplication.setUpdateControl(tenantFuncApplicationUpdateCommand);
		boolean save = tenantFuncApplicationGateway.save(tenantFuncApplication);
		if (save) {
			return SingleResponse.of(TenantFuncApplicationAppStructMapping.instance.toTenantFuncApplicationVO(tenantFuncApplication));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantFuncApplicationUpdateCommand
	 * @return
	 */
	private TenantFuncApplication createByTenantFuncApplicationUpdateCommand(TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand){
		TenantFuncApplication tenantFuncApplication = TenantFuncApplication.create();
		TenantFuncApplicationUpdateCommandToTenantFuncApplicationMapping.instance.fillTenantFuncApplicationByTenantFuncApplicationUpdateCommand(tenantFuncApplication, tenantFuncApplicationUpdateCommand);
		return tenantFuncApplication;
	}

	@Mapper
	interface TenantFuncApplicationUpdateCommandToTenantFuncApplicationMapping{
		TenantFuncApplicationUpdateCommandToTenantFuncApplicationMapping instance = Mappers.getMapper(TenantFuncApplicationUpdateCommandToTenantFuncApplicationMapping.class );

		default TenantFuncApplicationId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantFuncApplicationId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantFuncApplication
		 * @param tenantFuncApplicationUpdateCommand
		 */
		void fillTenantFuncApplicationByTenantFuncApplicationUpdateCommand(@MappingTarget TenantFuncApplication tenantFuncApplication, TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand);
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
