package com.particle.func.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.func.app.api.executor.FuncCreateCommandExecutor;
import com.particle.func.client.api.FuncApplicationService;
import com.particle.func.client.dto.command.CreateFuncCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-02 11:57
 */
@Service
@CatchAndLog
public class FuncApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements FuncApplicationService {

    private FuncCreateCommandExecutor funcCreateCommandExecutor;



    @Autowired
    public void setAreaCreateCommandExecutor(FuncCreateCommandExecutor funcCreateCommandExecutor) {
        this.funcCreateCommandExecutor = funcCreateCommandExecutor;
    }
    /**
     * 创建菜单
     * @param createFuncCommand
     * @return
     */
    @Override
    public SingleResponse<FuncVO> create(CreateFuncCommand createFuncCommand) {

        return funcCreateCommandExecutor.execute(createFuncCommand);
    }
}
