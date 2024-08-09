package com.particle.oplog.app.error.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.error.executor.representation.OpLogErrorContentQueryCommandExecutor;
import com.particle.oplog.client.error.api.representation.IOpLogErrorContentRepresentationApplicationService;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 操作异常日志内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Service
@CatchAndLog
public class OpLogErrorContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpLogErrorContentRepresentationApplicationService {

    private OpLogErrorContentQueryCommandExecutor opLogErrorContentQueryCommandExecutor;

    @Override
    public SingleResponse<OpLogErrorContentVO> queryDetail(IdCommand detailCommand) {
        return opLogErrorContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpLogErrorContentVO> detailByOpLogErrorId(IdCommand detailCommand) {
        return opLogErrorContentQueryCommandExecutor.detailByOpLogErrorId(detailCommand);
    }


    @Autowired
    public void setOpLogErrorContentQueryCommandExecutor(OpLogErrorContentQueryCommandExecutor opLogErrorContentQueryCommandExecutor) {
        this.opLogErrorContentQueryCommandExecutor = opLogErrorContentQueryCommandExecutor;
    }
}
