package com.particle.usagecount.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.UsageCountConfigCreateCommandExecutor;
import com.particle.usagecount.app.executor.UsageCountConfigDeleteCommandExecutor;
import com.particle.usagecount.app.executor.UsageCountConfigUpdateCommandExecutor;
import com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountConfigCreateCommand;
import com.particle.usagecount.client.dto.command.UsageCountConfigUpdateCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 使用次数配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Transactional
@Service
@CatchAndLog
public class UsageCountConfigApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountConfigApplicationService {

	private UsageCountConfigCreateCommandExecutor usageCountConfigCreateCommandExecutor;

	private UsageCountConfigDeleteCommandExecutor usageCountConfigDeleteCommandExecutor;

	private UsageCountConfigUpdateCommandExecutor usageCountConfigUpdateCommandExecutor;


	@Override
	public SingleResponse<UsageCountConfigVO> create(UsageCountConfigCreateCommand usageCountConfigCreateCommand) {
		return usageCountConfigCreateCommandExecutor.execute(usageCountConfigCreateCommand);
	}

	@Override
	public SingleResponse<UsageCountConfigVO> delete(IdCommand deleteCommand) {
		return usageCountConfigDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<UsageCountConfigVO> update(UsageCountConfigUpdateCommand usageCountConfigUpdateCommand) {
		return usageCountConfigUpdateCommandExecutor.execute(usageCountConfigUpdateCommand);
	}

	@Autowired
	public void setUsageCountConfigCreateCommandExecutor(UsageCountConfigCreateCommandExecutor usageCountConfigCreateCommandExecutor) {
		this.usageCountConfigCreateCommandExecutor = usageCountConfigCreateCommandExecutor;
	}

	@Autowired
	public void setUsageCountConfigDeleteCommandExecutor(UsageCountConfigDeleteCommandExecutor usageCountConfigDeleteCommandExecutor) {
		this.usageCountConfigDeleteCommandExecutor = usageCountConfigDeleteCommandExecutor;
	}
	@Autowired
	public void setUsageCountConfigUpdateCommandExecutor(UsageCountConfigUpdateCommandExecutor usageCountConfigUpdateCommandExecutor) {
		this.usageCountConfigUpdateCommandExecutor = usageCountConfigUpdateCommandExecutor;
	}

}
