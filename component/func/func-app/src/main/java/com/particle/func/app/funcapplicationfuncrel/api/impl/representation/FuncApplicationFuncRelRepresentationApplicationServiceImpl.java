package com.particle.func.app.funcapplicationfuncrel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.funcapplicationfuncrel.executor.representation.FuncApplicationFuncRelQueryCommandExecutor;
import com.particle.func.client.funcapplicationfuncrel.api.representation.IFuncApplicationFuncRelRepresentationApplicationService;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelPageQueryCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelQueryListCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 功能应用功能关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Service
@CatchAndLog
public class FuncApplicationFuncRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationFuncRelRepresentationApplicationService {

    private FuncApplicationFuncRelQueryCommandExecutor funcApplicationFuncRelQueryCommandExecutor;

    @Override
    public SingleResponse<FuncApplicationFuncRelVO> queryDetail(IdCommand detailCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<FuncApplicationFuncRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<FuncApplicationFuncRelVO> pageQuery(FuncApplicationFuncRelPageQueryCommand funcApplicationFuncRelPageQueryCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.execute(funcApplicationFuncRelPageQueryCommand);
    }

    @Override
    public MultiResponse<Long> queryFuncApplicationIdsByFuncId(IdCommand funcIdCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.queryFuncApplicationIdsByFuncId(funcIdCommand);
    }

    @Override
    public MultiResponse<Long> queryFuncIdsByFuncApplicationId(IdCommand funcApplicationIdCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.queryFuncIdsByFuncApplicationId(funcApplicationIdCommand);
    }

    @Override
    public MultiResponse<FuncApplicationFuncRelVO> queryList(FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand) {
        return funcApplicationFuncRelQueryCommandExecutor.execute(funcApplicationFuncRelQueryListCommand);
    }

    @Autowired
    public void setFuncApplicationFuncRelQueryCommandExecutor(FuncApplicationFuncRelQueryCommandExecutor funcApplicationFuncRelQueryCommandExecutor) {
        this.funcApplicationFuncRelQueryCommandExecutor = funcApplicationFuncRelQueryCommandExecutor;
    }
}
