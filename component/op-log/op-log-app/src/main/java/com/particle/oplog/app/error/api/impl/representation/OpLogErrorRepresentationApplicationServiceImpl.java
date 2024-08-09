package com.particle.oplog.app.error.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.error.executor.representation.OpLogErrorQueryCommandExecutor;
import com.particle.oplog.client.error.api.representation.IOpLogErrorRepresentationApplicationService;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorPageQueryCommand;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorQueryListCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 操作异常日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Service
@CatchAndLog
public class OpLogErrorRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogErrorRepresentationApplicationService {

    private OpLogErrorQueryCommandExecutor opLogErrorQueryCommandExecutor;

    @Override
    public SingleResponse<OpLogErrorVO> queryDetail(IdCommand detailCommand) {
        return opLogErrorQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<OpLogErrorVO> pageQuery(OpLogErrorPageQueryCommand opLogErrorPageQueryCommand) {
        return opLogErrorQueryCommandExecutor.execute(opLogErrorPageQueryCommand);
    }

    @Override
    public MultiResponse<OpLogErrorVO> queryList(OpLogErrorQueryListCommand opLogErrorQueryListCommand) {
        return opLogErrorQueryCommandExecutor.execute(opLogErrorQueryListCommand);
    }


    @Autowired
    public void setOpLogErrorQueryCommandExecutor(OpLogErrorQueryCommandExecutor opLogErrorQueryCommandExecutor) {
        this.opLogErrorQueryCommandExecutor = opLogErrorQueryCommandExecutor;
    }
}
