package com.particle.oplog.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.executor.representation.OpLogQueryCommandExecutor;
import com.particle.oplog.client.api.representation.IOpLogRepresentationApplicationService;
import com.particle.oplog.client.dto.command.representation.OpLogPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 操作日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Service
@CatchAndLog
public class OpLogRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogRepresentationApplicationService {

    private OpLogQueryCommandExecutor opLogQueryCommandExecutor;

    @Override
    public SingleResponse<OpLogVO> queryDetail(IdCommand detailCommand) {
        return opLogQueryCommandExecutor.executeDetail(detailCommand);
    }


    @Override
    public PageResponse<OpLogVO> pageQuery(OpLogPageQueryCommand opLogPageQueryCommand) {
        return opLogQueryCommandExecutor.execute(opLogPageQueryCommand);
    }

    @Override
    public MultiResponse<OpLogVO> queryList(OpLogQueryListCommand opLogQueryListCommand) {
        return opLogQueryCommandExecutor.execute(opLogQueryListCommand);
    }

    @Autowired
    public void setOpLogQueryCommandExecutor(OpLogQueryCommandExecutor opLogQueryCommandExecutor) {
        this.opLogQueryCommandExecutor = opLogQueryCommandExecutor;
    }
}
