package com.particle.tenant.app.tenantfunc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.tenantfunc.structmapping.TenantFuncAppStructMapping;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncCreateCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
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
 * 租户功能菜单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Component
@Validated
public class TenantFuncCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncGateway tenantFuncGateway;

	/**
	 * 执行租户功能菜单添加指令
	 * @param tenantFuncCreateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncVO> execute(@Valid TenantFuncCreateCommand tenantFuncCreateCommand) {
		TenantFunc tenantFunc = createByTenantFuncCreateCommand(tenantFuncCreateCommand);
		tenantFunc.setAddControl(tenantFuncCreateCommand);
		boolean save = tenantFuncGateway.save(tenantFunc);
		if (save) {
			return SingleResponse.of(TenantFuncAppStructMapping.instance.toTenantFuncVO(tenantFunc));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户功能菜单创建指令创建租户功能菜单模型
	 * @param tenantFuncCreateCommand
	 * @return
	 */
	private TenantFunc createByTenantFuncCreateCommand(TenantFuncCreateCommand tenantFuncCreateCommand){
		TenantFunc tenantFunc = TenantFunc.create();
		TenantFuncCreateCommandToTenantFuncMapping.instance.fillTenantFuncByTenantFuncCreateCommand(tenantFunc, tenantFuncCreateCommand);
		return tenantFunc;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TenantFuncCreateCommandToTenantFuncMapping{
		TenantFuncCreateCommandToTenantFuncMapping instance = Mappers.getMapper( TenantFuncCreateCommandToTenantFuncMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantFunc
		 * @param tenantFuncCreateCommand
		 */
		void fillTenantFuncByTenantFuncCreateCommand(@MappingTarget TenantFunc tenantFunc, TenantFuncCreateCommand tenantFuncCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantFuncGateway
	 */
	@Autowired
	public void setTenantFuncGateway(TenantFuncGateway tenantFuncGateway) {
		this.tenantFuncGateway = tenantFuncGateway;
	}
}
