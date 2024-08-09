package com.particle.oplog.app.error.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.error.executor.OpLogErrorCommandExecutor;
import com.particle.oplog.app.error.executor.OpLogErrorCreateCommandExecutor;
import com.particle.oplog.app.error.executor.OpLogErrorDeleteCommandExecutor;
import com.particle.oplog.client.error.api.IOpLogErrorApplicationService;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 操作异常日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Transactional
@Service
@CatchAndLog
public class OpLogErrorApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogErrorApplicationService {

	private OpLogErrorCreateCommandExecutor opLogErrorCreateCommandExecutor;

	private OpLogErrorDeleteCommandExecutor opLogErrorDeleteCommandExecutor;

	private OpLogErrorCommandExecutor opLogErrorCommandExecutor;


	@Override
	public SingleResponse<OpLogErrorVO> create(OpLogErrorCreateCommand opLogErrorCreateCommand) {
		return opLogErrorCreateCommandExecutor.execute(opLogErrorCreateCommand);
	}

	@Override
	public SingleResponse<OpLogErrorVO> delete(IdCommand deleteCommand) {
		return opLogErrorDeleteCommandExecutor.execute(deleteCommand);
	}



	@Autowired
	public void setOpLogErrorCreateCommandExecutor(OpLogErrorCreateCommandExecutor opLogErrorCreateCommandExecutor) {
		this.opLogErrorCreateCommandExecutor = opLogErrorCreateCommandExecutor;
	}

	@Autowired
	public void setOpLogErrorDeleteCommandExecutor(OpLogErrorDeleteCommandExecutor opLogErrorDeleteCommandExecutor) {
		this.opLogErrorDeleteCommandExecutor = opLogErrorDeleteCommandExecutor;
	}

	@Autowired
	public void setOpLogErrorCommandExecutor(OpLogErrorCommandExecutor opLogErrorCommandExecutor) {
		this.opLogErrorCommandExecutor = opLogErrorCommandExecutor;
	}
}
