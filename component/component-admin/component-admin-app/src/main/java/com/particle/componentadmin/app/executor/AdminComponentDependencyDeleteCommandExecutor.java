package com.particle.componentadmin.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.componentadmin.app.structmapping.AdminComponentDependencyAppStructMapping;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.AdminComponentDependencyId;
import com.particle.componentadmin.domain.gateway.AdminComponentDependencyGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentDependencyService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
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
public class AdminComponentDependencyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentDependencyGateway adminComponentDependencyGateway;
	private IAdminComponentDependencyService iAdminComponentDependencyService;

	/**
	 * 执行 组件依赖关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AdminComponentDependencyVO> execute(@Valid IdCommand deleteCommand) {
		AdminComponentDependencyId adminComponentDependencyId = AdminComponentDependencyId.of(deleteCommand.getId());
		AdminComponentDependency byId = adminComponentDependencyGateway.getById(adminComponentDependencyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = adminComponentDependencyGateway.delete(adminComponentDependencyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AdminComponentDependencyAppStructMapping.instance.toAdminComponentDependencyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 componentId 删除
	 * @param componentIdCommand
	 * @return
	 */
	public Response deleteByComponentId(@Valid IdCommand componentIdCommand) {
		boolean result = iAdminComponentDependencyService.deleteByColumn(componentIdCommand.getId(), AdminComponentDependencyDO::getComponentId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 dependComponentId 删除
	 * @param dependComponentIdCommand
	 * @return
	 */
	public Response deleteByDependComponentId(@Valid IdCommand dependComponentIdCommand) {
		boolean result = iAdminComponentDependencyService.deleteByColumn(dependComponentIdCommand.getId(), AdminComponentDependencyDO::getDependComponentId);
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
