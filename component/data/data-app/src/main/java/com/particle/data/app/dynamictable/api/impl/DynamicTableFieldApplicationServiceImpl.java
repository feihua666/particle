package com.particle.data.app.dynamictable.api.impl;

import com.particle.data.app.dynamictable.executor.DynamicTableFieldCreateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldDeleteCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldUpdateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldUpdateCommand;
import com.particle.data.client.dynamictable.api.IDynamicTableFieldApplicationService;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 动态数据表格字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Transactional
@Service
@CatchAndLog
public class DynamicTableFieldApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableFieldApplicationService {

    private DynamicTableFieldCreateCommandExecutor dynamicTableFieldCreateCommandExecutor;

    private DynamicTableFieldDeleteCommandExecutor dynamicTableFieldDeleteCommandExecutor;

    private DynamicTableFieldUpdateCommandExecutor dynamicTableFieldUpdateCommandExecutor;

    private DynamicTableFieldCommandExecutor dynamicTableFieldCommandExecutor;


    @Override
    public SingleResponse<DynamicTableFieldVO> create(DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand) {
        return dynamicTableFieldCreateCommandExecutor.execute(dynamicTableFieldCreateCommand);
    }

    @Override
    public SingleResponse<DynamicTableFieldVO> delete(IdCommand deleteCommand) {
        return dynamicTableFieldDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicTableFieldVO> update(DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand) {
        return dynamicTableFieldUpdateCommandExecutor.execute(dynamicTableFieldUpdateCommand);
    }


    @Autowired
    public void setDynamicTableFieldCreateCommandExecutor(DynamicTableFieldCreateCommandExecutor dynamicTableFieldCreateCommandExecutor) {
        this.dynamicTableFieldCreateCommandExecutor = dynamicTableFieldCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicTableFieldDeleteCommandExecutor(DynamicTableFieldDeleteCommandExecutor dynamicTableFieldDeleteCommandExecutor) {
        this.dynamicTableFieldDeleteCommandExecutor = dynamicTableFieldDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicTableFieldUpdateCommandExecutor(DynamicTableFieldUpdateCommandExecutor dynamicTableFieldUpdateCommandExecutor) {
        this.dynamicTableFieldUpdateCommandExecutor = dynamicTableFieldUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableFieldCommandExecutor(DynamicTableFieldCommandExecutor dynamicTableFieldCommandExecutor) {
        this.dynamicTableFieldCommandExecutor = dynamicTableFieldCommandExecutor;
    }
}
