package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.app.structmapping.AdminComponentAppStructMapping;
import com.particle.componentadmin.client.dto.command.AdminComponentCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.gateway.AdminComponentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 组件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Component
@Validated
public class AdminComponentCreateCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentGateway adminComponentGateway;

	/**
	 * 执行组件添加指令
	 * @param adminComponentCreateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentVO> execute(@Valid AdminComponentCreateCommand adminComponentCreateCommand) {
		AdminComponent adminComponent = createByAdminComponentCreateCommand(adminComponentCreateCommand);
		adminComponent.setAddControl(adminComponentCreateCommand);
		boolean save = adminComponentGateway.save(adminComponent);
		if (save) {
			return SingleResponse.of(AdminComponentAppStructMapping.instance.toAdminComponentVO(adminComponent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据组件创建指令创建组件模型
	 * @param adminComponentCreateCommand
	 * @return
	 */
	private AdminComponent createByAdminComponentCreateCommand(AdminComponentCreateCommand adminComponentCreateCommand){
		AdminComponent adminComponent = AdminComponent.create();
		AdminComponentCreateCommandToAdminComponentMapping.instance.fillAdminComponentByAdminComponentCreateCommand(adminComponent, adminComponentCreateCommand);
		return adminComponent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AdminComponentCreateCommandToAdminComponentMapping{
		AdminComponentCreateCommandToAdminComponentMapping instance = Mappers.getMapper( AdminComponentCreateCommandToAdminComponentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param adminComponent
		 * @param adminComponentCreateCommand
		 */
		void fillAdminComponentByAdminComponentCreateCommand(@MappingTarget AdminComponent adminComponent, AdminComponentCreateCommand adminComponentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param adminComponentGateway
	 */
	@Autowired
	public void setAdminComponentGateway(AdminComponentGateway adminComponentGateway) {
		this.adminComponentGateway = adminComponentGateway;
	}
}
