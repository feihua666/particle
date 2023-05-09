package com.particle.oplog.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.executor.OpLogAuditDataDeleteCommandExecutor;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 操作日志审计数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Transactional
@Service
@CatchAndLog
public class OpLogAuditDataApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogAuditDataApplicationService {


	private OpLogAuditDataDeleteCommandExecutor opLogAuditDataDeleteCommandExecutor;


	@Override
	public SingleResponse<OpLogAuditDataVO> delete(IdCommand deleteCommand) {
		return opLogAuditDataDeleteCommandExecutor.execute(deleteCommand);
	}


	@Autowired
	public void setOpLogAuditDataDeleteCommandExecutor(OpLogAuditDataDeleteCommandExecutor opLogAuditDataDeleteCommandExecutor) {
		this.opLogAuditDataDeleteCommandExecutor = opLogAuditDataDeleteCommandExecutor;
	}


}
