package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.app.structmapping.AdminComponentDependencyAppStructMapping;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyUpdateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.AdminComponentDependencyId;
import com.particle.componentadmin.domain.gateway.AdminComponentDependencyGateway;
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
 * 组件依赖关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AdminComponentDependencyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentDependencyGateway adminComponentDependencyGateway;

	/**
	 * 执行 组件依赖关系 更新指令
	 * @param adminComponentDependencyUpdateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentDependencyVO> execute(@Valid AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand) {
		AdminComponentDependency adminComponentDependency = createByAdminComponentDependencyUpdateCommand(adminComponentDependencyUpdateCommand);
		adminComponentDependency.setUpdateControl(adminComponentDependencyUpdateCommand);
		boolean save = adminComponentDependencyGateway.save(adminComponentDependency);
		if (save) {
			return SingleResponse.of(AdminComponentDependencyAppStructMapping.instance.toAdminComponentDependencyVO(adminComponentDependency));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据组件依赖关系更新指令创建组件依赖关系模型
	 * @param adminComponentDependencyUpdateCommand
	 * @return
	 */
	private AdminComponentDependency createByAdminComponentDependencyUpdateCommand(AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand){
		AdminComponentDependency adminComponentDependency = AdminComponentDependency.create();
		AdminComponentDependencyUpdateCommandToAdminComponentDependencyMapping.instance.fillAdminComponentDependencyByAdminComponentDependencyUpdateCommand(adminComponentDependency, adminComponentDependencyUpdateCommand);
		return adminComponentDependency;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AdminComponentDependencyUpdateCommandToAdminComponentDependencyMapping{
		AdminComponentDependencyUpdateCommandToAdminComponentDependencyMapping instance = Mappers.getMapper(AdminComponentDependencyUpdateCommandToAdminComponentDependencyMapping.class );

		default AdminComponentDependencyId map(Long id){
			if (id == null) {
				return null;
			}
			return AdminComponentDependencyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param adminComponentDependency
		 * @param adminComponentDependencyUpdateCommand
		 */
		void fillAdminComponentDependencyByAdminComponentDependencyUpdateCommand(@MappingTarget AdminComponentDependency adminComponentDependency, AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand);
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
