package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.app.structmapping.AdminComponentAppStructMapping;
import com.particle.componentadmin.client.dto.command.AdminComponentUpdateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.AdminComponentId;
import com.particle.componentadmin.domain.gateway.AdminComponentGateway;
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
 * 组件 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AdminComponentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentGateway adminComponentGateway;

	/**
	 * 执行 组件 更新指令
	 * @param adminComponentUpdateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentVO> execute(@Valid AdminComponentUpdateCommand adminComponentUpdateCommand) {
		AdminComponent adminComponent = createByAdminComponentUpdateCommand(adminComponentUpdateCommand);
		adminComponent.setUpdateControl(adminComponentUpdateCommand);
		boolean save = adminComponentGateway.save(adminComponent);
		if (save) {
			return SingleResponse.of(AdminComponentAppStructMapping.instance.toAdminComponentVO(adminComponent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据组件更新指令创建组件模型
	 * @param adminComponentUpdateCommand
	 * @return
	 */
	private AdminComponent createByAdminComponentUpdateCommand(AdminComponentUpdateCommand adminComponentUpdateCommand){
		AdminComponent adminComponent = AdminComponent.create();
		AdminComponentUpdateCommandToAdminComponentMapping.instance.fillAdminComponentByAdminComponentUpdateCommand(adminComponent, adminComponentUpdateCommand);
		return adminComponent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AdminComponentUpdateCommandToAdminComponentMapping{
		AdminComponentUpdateCommandToAdminComponentMapping instance = Mappers.getMapper(AdminComponentUpdateCommandToAdminComponentMapping.class );

		default AdminComponentId map(Long id){
			if (id == null) {
				return null;
			}
			return AdminComponentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param adminComponent
		 * @param adminComponentUpdateCommand
		 */
		void fillAdminComponentByAdminComponentUpdateCommand(@MappingTarget AdminComponent adminComponent, AdminComponentUpdateCommand adminComponentUpdateCommand);
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
