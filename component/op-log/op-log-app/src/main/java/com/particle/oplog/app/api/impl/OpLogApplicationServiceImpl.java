package com.particle.oplog.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.executor.OpLogDeleteCommandExecutor;
import com.particle.oplog.client.api.IOpLogApplicationService;
import com.particle.oplog.client.dto.data.OpLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 操作日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Transactional
@Service
@CatchAndLog
public class OpLogApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogApplicationService {

	private OpLogDeleteCommandExecutor opLogDeleteCommandExecutor;


	@Override
	public SingleResponse<OpLogVO> delete(IdCommand deleteCommand) {
		return opLogDeleteCommandExecutor.execute(deleteCommand);
	}


	@Autowired
	public void setOpLogDeleteCommandExecutor(OpLogDeleteCommandExecutor opLogDeleteCommandExecutor) {
		this.opLogDeleteCommandExecutor = opLogDeleteCommandExecutor;
	}


}
