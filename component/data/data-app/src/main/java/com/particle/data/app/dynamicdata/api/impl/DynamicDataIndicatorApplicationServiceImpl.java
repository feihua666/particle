package com.particle.data.app.dynamicdata.api.impl;

import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorDeleteCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorUpdateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorUpdateCommand;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 动态数据指标 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Transactional
@Service
@CatchAndLog
public class DynamicDataIndicatorApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorApplicationService {

    private DynamicDataIndicatorCreateCommandExecutor dynamicDataIndicatorCreateCommandExecutor;

    private DynamicDataIndicatorDeleteCommandExecutor dynamicDataIndicatorDeleteCommandExecutor;

    private DynamicDataIndicatorUpdateCommandExecutor dynamicDataIndicatorUpdateCommandExecutor;

    private DynamicDataIndicatorCommandExecutor dynamicDataIndicatorCommandExecutor;


    @Override
    public SingleResponse<DynamicDataIndicatorVO> create(DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand) {
        return dynamicDataIndicatorCreateCommandExecutor.execute(dynamicDataIndicatorCreateCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorVO> delete(IdCommand deleteCommand) {
        return dynamicDataIndicatorDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorVO> update(DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand) {
        return dynamicDataIndicatorUpdateCommandExecutor.execute(dynamicDataIndicatorUpdateCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorCreateCommandExecutor(DynamicDataIndicatorCreateCommandExecutor dynamicDataIndicatorCreateCommandExecutor) {
        this.dynamicDataIndicatorCreateCommandExecutor = dynamicDataIndicatorCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicDataIndicatorDeleteCommandExecutor(DynamicDataIndicatorDeleteCommandExecutor dynamicDataIndicatorDeleteCommandExecutor) {
        this.dynamicDataIndicatorDeleteCommandExecutor = dynamicDataIndicatorDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorUpdateCommandExecutor(DynamicDataIndicatorUpdateCommandExecutor dynamicDataIndicatorUpdateCommandExecutor) {
        this.dynamicDataIndicatorUpdateCommandExecutor = dynamicDataIndicatorUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorCommandExecutor(DynamicDataIndicatorCommandExecutor dynamicDataIndicatorCommandExecutor) {
        this.dynamicDataIndicatorCommandExecutor = dynamicDataIndicatorCommandExecutor;
    }
}
