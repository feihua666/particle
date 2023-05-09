package com.particle.oplog.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.executor.representation.OpLogAuditDataQueryCommandExecutor;
import com.particle.oplog.client.api.representation.IOpLogAuditDataRepresentationApplicationService;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 操作日志审计数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Service
@CatchAndLog
public class OpLogAuditDataRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogAuditDataRepresentationApplicationService {

    private OpLogAuditDataQueryCommandExecutor opLogAuditDataQueryCommandExecutor;

    @Override
    public SingleResponse<OpLogAuditDataVO> queryDetail(IdCommand detailCommand) {
        return opLogAuditDataQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<OpLogAuditDataVO> pageQuery(OpLogAuditDataPageQueryCommand opLogAuditDataPageQueryCommand) {
        return opLogAuditDataQueryCommandExecutor.execute(opLogAuditDataPageQueryCommand);
    }

    @Override
    public MultiResponse<OpLogAuditDataVO> queryList(OpLogAuditDataQueryListCommand opLogAuditDataQueryListCommand) {
        return opLogAuditDataQueryCommandExecutor.execute(opLogAuditDataQueryListCommand);
    }

    @Autowired
    public void setOpLogAuditDataQueryCommandExecutor(OpLogAuditDataQueryCommandExecutor opLogAuditDataQueryCommandExecutor) {
        this.opLogAuditDataQueryCommandExecutor = opLogAuditDataQueryCommandExecutor;
    }
}
