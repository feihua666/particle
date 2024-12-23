package com.particle.func.app.application.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.application.executor.representation.FuncApplicationQueryCommandExecutor;
import com.particle.func.client.application.api.representation.IFuncApplicationRepresentationApplicationService;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 功能应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Service
@CatchAndLog
public class FuncApplicationRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationRepresentationApplicationService {

    private FuncApplicationQueryCommandExecutor funcApplicationQueryCommandExecutor;

    @Override
    public SingleResponse<FuncApplicationVO> queryDetail(IdCommand detailCommand) {
        return funcApplicationQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<FuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return funcApplicationQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<FuncApplicationVO> pageQuery(FuncApplicationPageQueryCommand funcApplicationPageQueryCommand) {
        return funcApplicationQueryCommandExecutor.execute(funcApplicationPageQueryCommand);
    }

    @Override
    public MultiResponse<FuncApplicationVO> queryList(FuncApplicationQueryListCommand funcApplicationQueryListCommand) {
        return funcApplicationQueryCommandExecutor.execute(funcApplicationQueryListCommand);
    }

    @Autowired
    public void setFuncApplicationQueryCommandExecutor(FuncApplicationQueryCommandExecutor funcApplicationQueryCommandExecutor) {
        this.funcApplicationQueryCommandExecutor = funcApplicationQueryCommandExecutor;
    }
}
