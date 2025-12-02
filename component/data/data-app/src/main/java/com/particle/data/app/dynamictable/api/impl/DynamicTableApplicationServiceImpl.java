package com.particle.data.app.dynamictable.api.impl;

import com.particle.data.app.dynamictable.executor.DynamicTableCreateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableDeleteCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableUpdateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableDataDeleteCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableImportDataCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUpdateCommand;
import com.particle.data.client.dynamictable.api.IDynamicTableApplicationService;
import com.particle.data.client.dynamictable.dto.command.DynamicTableCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 动态数据表格 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Transactional
@Service
@CatchAndLog
public class DynamicTableApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableApplicationService {

    private DynamicTableCreateCommandExecutor dynamicTableCreateCommandExecutor;

    private DynamicTableDeleteCommandExecutor dynamicTableDeleteCommandExecutor;

    private DynamicTableUpdateCommandExecutor dynamicTableUpdateCommandExecutor;

    private DynamicTableCommandExecutor dynamicTableCommandExecutor;


    @Override
    public SingleResponse<DynamicTableVO> create(DynamicTableCreateCommand dynamicTableCreateCommand) {
        return dynamicTableCreateCommandExecutor.execute(dynamicTableCreateCommand);
    }

    @Override
    public SingleResponse<DynamicTableVO> delete(IdCommand deleteCommand) {
        return dynamicTableDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicTableVO> update(DynamicTableUpdateCommand dynamicTableUpdateCommand) {
        return dynamicTableUpdateCommandExecutor.execute(dynamicTableUpdateCommand);
    }

    @Override
    public Response importData(DynamicTableImportDataCommand importDataCommand,Boolean isPublic,Long batchId) {
        return dynamicTableCommandExecutor.importData(importDataCommand,null,true,isPublic,batchId);
    }

    @Override
    public SingleResponse<Map<String, Object>> dataDelete(DynamicTableDataDeleteCommand deleteCommand) {
        return dynamicTableDeleteCommandExecutor.dataDelete(deleteCommand, null,true);
    }


    @Autowired
    public void setDynamicTableCreateCommandExecutor(DynamicTableCreateCommandExecutor dynamicTableCreateCommandExecutor) {
        this.dynamicTableCreateCommandExecutor = dynamicTableCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicTableDeleteCommandExecutor(DynamicTableDeleteCommandExecutor dynamicTableDeleteCommandExecutor) {
        this.dynamicTableDeleteCommandExecutor = dynamicTableDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicTableUpdateCommandExecutor(DynamicTableUpdateCommandExecutor dynamicTableUpdateCommandExecutor) {
        this.dynamicTableUpdateCommandExecutor = dynamicTableUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableCommandExecutor(DynamicTableCommandExecutor dynamicTableCommandExecutor) {
        this.dynamicTableCommandExecutor = dynamicTableCommandExecutor;
    }
}
