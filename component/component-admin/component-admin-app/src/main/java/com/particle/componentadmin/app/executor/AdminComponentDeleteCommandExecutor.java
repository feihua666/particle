package com.particle.componentadmin.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.componentadmin.app.structmapping.AdminComponentAppStructMapping;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.AdminComponentId;
import com.particle.componentadmin.domain.gateway.AdminComponentGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
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
public class AdminComponentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentGateway adminComponentGateway;
	private IAdminComponentService iAdminComponentService;

	/**
	 * 执行 组件 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AdminComponentVO> execute(@Valid IdCommand deleteCommand) {
		AdminComponentId adminComponentId = AdminComponentId.of(deleteCommand.getId());
		AdminComponent byId = adminComponentGateway.getById(adminComponentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = adminComponentGateway.delete(adminComponentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AdminComponentAppStructMapping.instance.toAdminComponentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param adminComponentGateway
	 */
	@Autowired
	public void setAdminComponentGateway(AdminComponentGateway adminComponentGateway) {
		this.adminComponentGateway = adminComponentGateway;
	}
	@Autowired
	public void setIAdminComponentService(IAdminComponentService iAdminComponentService) {
		this.iAdminComponentService = iAdminComponentService;
	}
}
