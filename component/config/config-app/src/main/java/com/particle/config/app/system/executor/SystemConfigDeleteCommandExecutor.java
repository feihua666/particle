package com.particle.config.app.system.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.config.app.system.structmapping.SystemConfigAppStructMapping;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.SystemConfigId;
import com.particle.config.domain.system.gateway.SystemConfigGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 系统参数配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Component
@Validated
public class SystemConfigDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SystemConfigGateway systemConfigGateway;

	/**
	 * 执行 系统参数配置 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SystemConfigVO> execute(@Valid IdCommand deleteCommand) {
		SystemConfigId systemConfigId = SystemConfigId.of(deleteCommand.getId());
		SystemConfig byId = systemConfigGateway.getById(systemConfigId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = systemConfigGateway.delete(systemConfigId);
		if (delete) {
			return SingleResponse.of(SystemConfigAppStructMapping.instance.toSystemConfigVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param systemConfigGateway
	 */
	@Autowired
	public void setSystemConfigGateway(SystemConfigGateway systemConfigGateway) {
		this.systemConfigGateway = systemConfigGateway;
	}
}
