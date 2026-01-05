package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.domain.gateway.AdminComponentDependencyGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentDependencyService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.client.dto.command.ComponentAssignDependComponentCommand;
import com.particle.componentadmin.client.dto.command.DependComponentAssignComponentCommand;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import java.util.Optional;

/**
 * <p>
 * 组件依赖关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Component
@Validated
public class AdminComponentDependencyCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentDependencyGateway adminComponentDependencyGateway;
	private IAdminComponentDependencyService iAdminComponentDependencyService;
	/**
	 * 源组件分配依赖组件
	 * @param componentAssignDependComponentCommand
	 * @return
	 */
	public Response componentAssignDependComponent(@Valid ComponentAssignDependComponentCommand componentAssignDependComponentCommand) {
		boolean result = iAdminComponentDependencyService.removeAndAssignRel(componentAssignDependComponentCommand.getComponentId(),
				componentAssignDependComponentCommand.getCheckedDependComponentIds(),componentAssignDependComponentCommand.getUncheckedDependComponentIds(),
				componentAssignDependComponentCommand.getIsLazyLoad(), AdminComponentDependencyDO::getComponentId,AdminComponentDependencyDO::getDependComponentId,
				(relDto)->{
                    AdminComponentDependencyDO adminComponentDependencyDO = new AdminComponentDependencyDO().setComponentId(relDto.getMainId()).setDependComponentId(relDto.getOtherId());
                    adminComponentDependencyDO.setIsRequired(Optional.ofNullable(componentAssignDependComponentCommand.getIsRequired()).orElse(false));
                    return adminComponentDependencyDO;
                });
		return Response.buildSuccess();
	}

	/**
	 * 依赖组件分配源组件
	 * @param dependComponentAssignComponentCommand
	 * @return
	 */
	public Response dependComponentAssignComponent(@Valid DependComponentAssignComponentCommand dependComponentAssignComponentCommand) {
		boolean result = iAdminComponentDependencyService.removeAndAssignRel(dependComponentAssignComponentCommand.getDependComponentId(),
				dependComponentAssignComponentCommand.getCheckedComponentIds(),dependComponentAssignComponentCommand.getUncheckedComponentIds(),
				dependComponentAssignComponentCommand.getIsLazyLoad(), AdminComponentDependencyDO::getDependComponentId,AdminComponentDependencyDO::getComponentId,
				(relDto)->{
                    AdminComponentDependencyDO adminComponentDependencyDO = new AdminComponentDependencyDO().setDependComponentId(relDto.getMainId()).setComponentId(relDto.getOtherId());
                    adminComponentDependencyDO.setIsRequired(Optional.ofNullable(dependComponentAssignComponentCommand.getIsRequired()).orElse(false));
                    return adminComponentDependencyDO;
                });
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param adminComponentDependencyGateway
	 */
	@Autowired
	public void setAdminComponentDependencyGateway(AdminComponentDependencyGateway adminComponentDependencyGateway) {
		this.adminComponentDependencyGateway = adminComponentDependencyGateway;
	}
	@Autowired
	public void setIAdminComponentDependencyService(IAdminComponentDependencyService iAdminComponentDependencyService) {
		this.iAdminComponentDependencyService = iAdminComponentDependencyService;
	}
}
