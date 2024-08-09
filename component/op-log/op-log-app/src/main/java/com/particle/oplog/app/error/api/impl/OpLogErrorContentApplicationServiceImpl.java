package com.particle.oplog.app.error.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.error.executor.OpLogErrorContentCommandExecutor;
import com.particle.oplog.app.error.executor.OpLogErrorContentDeleteCommandExecutor;
import com.particle.oplog.client.error.api.IOpLogErrorContentApplicationService;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 操作异常日志内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Transactional
@Service
@CatchAndLog
public class OpLogErrorContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogErrorContentApplicationService {

	private OpLogErrorContentDeleteCommandExecutor opLogErrorContentDeleteCommandExecutor;

	private OpLogErrorContentCommandExecutor opLogErrorContentCommandExecutor;


	@Override
	public SingleResponse<OpLogErrorContentVO> delete(IdCommand deleteCommand) {
		return opLogErrorContentDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setOpLogErrorContentDeleteCommandExecutor(OpLogErrorContentDeleteCommandExecutor opLogErrorContentDeleteCommandExecutor) {
		this.opLogErrorContentDeleteCommandExecutor = opLogErrorContentDeleteCommandExecutor;
	}

	@Autowired
	public void setOpLogErrorContentCommandExecutor(OpLogErrorContentCommandExecutor opLogErrorContentCommandExecutor) {
		this.opLogErrorContentCommandExecutor = opLogErrorContentCommandExecutor;
	}
}
