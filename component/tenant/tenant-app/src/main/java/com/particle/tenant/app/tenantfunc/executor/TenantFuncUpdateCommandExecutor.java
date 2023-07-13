package com.particle.tenant.app.tenantfunc.executor;

import com.particle.tenant.app.tenantfunc.structmapping.TenantFuncAppStructMapping;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncUpdateCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
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
 * 租户功能菜单 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantFuncUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantFuncGateway tenantFuncGateway;

	/**
	 * 执行 租户功能菜单 更新指令
	 * @param tenantFuncUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncVO> execute(@Valid TenantFuncUpdateCommand tenantFuncUpdateCommand) {
		TenantFunc tenantFunc = createByTenantFuncUpdateCommand(tenantFuncUpdateCommand);
		tenantFunc.setUpdateControl(tenantFuncUpdateCommand);
		boolean save = tenantFuncGateway.save(tenantFunc);
		if (save) {
			return SingleResponse.of(TenantFuncAppStructMapping.instance.toTenantFuncVO(tenantFunc));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户功能菜单更新指令创建租户功能菜单模型
	 * @param tenantFuncUpdateCommand
	 * @return
	 */
	private TenantFunc createByTenantFuncUpdateCommand(TenantFuncUpdateCommand tenantFuncUpdateCommand){
		TenantFunc tenantFunc = TenantFunc.create();
		TenantFuncUpdateCommandToTenantFuncMapping.instance.fillTenantFuncByTenantFuncUpdateCommand(tenantFunc, tenantFuncUpdateCommand);
		return tenantFunc;
	}

	@Mapper
	interface TenantFuncUpdateCommandToTenantFuncMapping{
		TenantFuncUpdateCommandToTenantFuncMapping instance = Mappers.getMapper(TenantFuncUpdateCommandToTenantFuncMapping.class );

		default TenantFuncId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantFuncId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantFunc
		 * @param tenantFuncUpdateCommand
		 */
		void fillTenantFuncByTenantFuncUpdateCommand(@MappingTarget TenantFunc tenantFunc, TenantFuncUpdateCommand tenantFuncUpdateCommand);
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
