package com.particle.componentadmin.app.executor;

import com.particle.componentadmin.domain.gateway.AdminComponentGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 组件 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Component
@Validated
public class AdminComponentCommandExecutor  extends AbstractBaseExecutor {

	private AdminComponentGateway adminComponentGateway;
	private IAdminComponentService iAdminComponentService;
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
