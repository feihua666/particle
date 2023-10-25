package com.particle.usagecount.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.UsageCountRecordDetailDeleteCommandExecutor;
import com.particle.usagecount.client.api.IUsageCountRecordDetailApplicationService;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 使用次数记录明细 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Transactional
@Service
@CatchAndLog
public class UsageCountRecordDetailApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountRecordDetailApplicationService {

	private UsageCountRecordDetailDeleteCommandExecutor usageCountRecordDetailDeleteCommandExecutor;


	@Override
	public SingleResponse<UsageCountRecordDetailVO> delete(IdCommand deleteCommand) {
		return usageCountRecordDetailDeleteCommandExecutor.execute(deleteCommand);
	}


	@Autowired
	public void setUsageCountRecordDetailDeleteCommandExecutor(UsageCountRecordDetailDeleteCommandExecutor usageCountRecordDetailDeleteCommandExecutor) {
		this.usageCountRecordDetailDeleteCommandExecutor = usageCountRecordDetailDeleteCommandExecutor;
	}

}
