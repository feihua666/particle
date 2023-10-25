package com.particle.usagecount.app.api.impl;

import com.particle.usagecount.app.executor.UsageCountDefineCreateCommandExecutor;
import com.particle.usagecount.app.executor.UsageCountDefineDeleteCommandExecutor;
import com.particle.usagecount.app.executor.UsageCountDefineUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.usagecount.client.dto.command.UsageCountDefineUpdateCommand;
import com.particle.usagecount.client.api.IUsageCountDefineApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountDefineCreateCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
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
 * 使用次数定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Transactional
@Service
@CatchAndLog
public class UsageCountDefineApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountDefineApplicationService {

	private UsageCountDefineCreateCommandExecutor usageCountDefineCreateCommandExecutor;

	private UsageCountDefineDeleteCommandExecutor usageCountDefineDeleteCommandExecutor;

	private UsageCountDefineUpdateCommandExecutor usageCountDefineUpdateCommandExecutor;


	@Override
	public SingleResponse<UsageCountDefineVO> create(UsageCountDefineCreateCommand usageCountDefineCreateCommand) {
		return usageCountDefineCreateCommandExecutor.execute(usageCountDefineCreateCommand);
	}

	@Override
	public SingleResponse<UsageCountDefineVO> delete(IdCommand deleteCommand) {
		return usageCountDefineDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<UsageCountDefineVO> update(UsageCountDefineUpdateCommand usageCountDefineUpdateCommand) {
		return usageCountDefineUpdateCommandExecutor.execute(usageCountDefineUpdateCommand);
	}

	@Autowired
	public void setUsageCountDefineCreateCommandExecutor(UsageCountDefineCreateCommandExecutor usageCountDefineCreateCommandExecutor) {
		this.usageCountDefineCreateCommandExecutor = usageCountDefineCreateCommandExecutor;
	}

	@Autowired
	public void setUsageCountDefineDeleteCommandExecutor(UsageCountDefineDeleteCommandExecutor usageCountDefineDeleteCommandExecutor) {
		this.usageCountDefineDeleteCommandExecutor = usageCountDefineDeleteCommandExecutor;
	}
	@Autowired
	public void setUsageCountDefineUpdateCommandExecutor(UsageCountDefineUpdateCommandExecutor usageCountDefineUpdateCommandExecutor) {
		this.usageCountDefineUpdateCommandExecutor = usageCountDefineUpdateCommandExecutor;
	}

}
