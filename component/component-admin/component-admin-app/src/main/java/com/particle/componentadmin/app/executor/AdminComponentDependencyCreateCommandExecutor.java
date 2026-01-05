package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.app.structmapping.AdminComponentDependencyAppStructMapping;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.gateway.AdminComponentDependencyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 组件依赖关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Component
@Validated
public class AdminComponentDependencyCreateCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentDependencyGateway adminComponentDependencyGateway;

	/**
	 * 执行组件依赖关系添加指令
	 * @param adminComponentDependencyCreateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentDependencyVO> execute(@Valid AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand) {
		AdminComponentDependency adminComponentDependency = createByAdminComponentDependencyCreateCommand(adminComponentDependencyCreateCommand);
		adminComponentDependency.setAddControl(adminComponentDependencyCreateCommand);
		boolean save = adminComponentDependencyGateway.save(adminComponentDependency);
		if (save) {
			return SingleResponse.of(AdminComponentDependencyAppStructMapping.instance.toAdminComponentDependencyVO(adminComponentDependency));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据组件依赖关系创建指令创建组件依赖关系模型
	 * @param adminComponentDependencyCreateCommand
	 * @return
	 */
	private AdminComponentDependency createByAdminComponentDependencyCreateCommand(AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand){
		AdminComponentDependency adminComponentDependency = AdminComponentDependency.create();
		AdminComponentDependencyCreateCommandToAdminComponentDependencyMapping.instance.fillAdminComponentDependencyByAdminComponentDependencyCreateCommand(adminComponentDependency, adminComponentDependencyCreateCommand);
		return adminComponentDependency;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AdminComponentDependencyCreateCommandToAdminComponentDependencyMapping{
		AdminComponentDependencyCreateCommandToAdminComponentDependencyMapping instance = Mappers.getMapper( AdminComponentDependencyCreateCommandToAdminComponentDependencyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param adminComponentDependency
		 * @param adminComponentDependencyCreateCommand
		 */
		void fillAdminComponentDependencyByAdminComponentDependencyCreateCommand(@MappingTarget AdminComponentDependency adminComponentDependency, AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param adminComponentDependencyGateway
	 */
	@Autowired
	public void setAdminComponentDependencyGateway(AdminComponentDependencyGateway adminComponentDependencyGateway) {
		this.adminComponentDependencyGateway = adminComponentDependencyGateway;
	}
}
