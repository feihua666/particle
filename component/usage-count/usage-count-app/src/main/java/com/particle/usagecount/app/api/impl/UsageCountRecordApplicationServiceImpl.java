package com.particle.usagecount.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.UsageCountRecordDeleteCommandExecutor;
import com.particle.usagecount.app.executor.UsageCountRecordMarkCommandExecutor;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 使用次数记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Transactional
@Service
@CatchAndLog
public class UsageCountRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountRecordApplicationService {

	private UsageCountRecordMarkCommandExecutor usageCountRecordMarkCommandExecutor;

	private UsageCountRecordDeleteCommandExecutor usageCountRecordDeleteCommandExecutor;


	@Override
	public SingleResponse<UsageCountRecordMarkVO> mark(UsageCountRecordMarkCommand usageCountRecordMarkCommand) {
		return usageCountRecordMarkCommandExecutor.execute(usageCountRecordMarkCommand);
	}

	@Override
	public SingleResponse<UsageCountRecordVO> delete(IdCommand deleteCommand) {
		return usageCountRecordDeleteCommandExecutor.execute(deleteCommand);
	}


	@Autowired
	public void setUsageCountRecordCreateCommandExecutor(UsageCountRecordMarkCommandExecutor usageCountRecordMarkCommandExecutor) {
		this.usageCountRecordMarkCommandExecutor = usageCountRecordMarkCommandExecutor;
	}

	@Autowired
	public void setUsageCountRecordDeleteCommandExecutor(UsageCountRecordDeleteCommandExecutor usageCountRecordDeleteCommandExecutor) {
		this.usageCountRecordDeleteCommandExecutor = usageCountRecordDeleteCommandExecutor;
	}


}
