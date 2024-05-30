package com.particle.config.app.system.api.impl;

import com.particle.config.app.system.executor.SystemConfigCreateCommandExecutor;
import com.particle.config.app.system.executor.SystemConfigDeleteCommandExecutor;
import com.particle.config.app.system.executor.SystemConfigUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.api.ISystemConfigApplicationService;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 系统参数配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Transactional
@Service
@CatchAndLog
public class SystemConfigApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISystemConfigApplicationService {

	private SystemConfigCreateCommandExecutor systemConfigCreateCommandExecutor;

	private SystemConfigDeleteCommandExecutor systemConfigDeleteCommandExecutor;

	private SystemConfigUpdateCommandExecutor systemConfigUpdateCommandExecutor;


	@Override
	public SingleResponse<SystemConfigVO> create(SystemConfigCreateCommand systemConfigCreateCommand) {
		return systemConfigCreateCommandExecutor.execute(systemConfigCreateCommand);
	}

	@Override
	public SingleResponse<SystemConfigVO> delete(IdCommand deleteCommand) {
		return systemConfigDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<SystemConfigVO> update(SystemConfigUpdateCommand systemConfigUpdateCommand) {
		return systemConfigUpdateCommandExecutor.execute(systemConfigUpdateCommand);
	}

	@Autowired
	public void setSystemConfigCreateCommandExecutor(SystemConfigCreateCommandExecutor systemConfigCreateCommandExecutor) {
		this.systemConfigCreateCommandExecutor = systemConfigCreateCommandExecutor;
	}

	@Autowired
	public void setSystemConfigDeleteCommandExecutor(SystemConfigDeleteCommandExecutor systemConfigDeleteCommandExecutor) {
		this.systemConfigDeleteCommandExecutor = systemConfigDeleteCommandExecutor;
	}
	@Autowired
	public void setSystemConfigUpdateCommandExecutor(SystemConfigUpdateCommandExecutor systemConfigUpdateCommandExecutor) {
		this.systemConfigUpdateCommandExecutor = systemConfigUpdateCommandExecutor;
	}

}
