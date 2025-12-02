package com.particle.data.app.dynamicdata.api.impl;

import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryDeleteCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryUpdateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryDataDeleteCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryImportDataCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;


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
 * 动态数据指标分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Transactional
@Service
@CatchAndLog
public class DynamicDataIndicatorCategoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorCategoryApplicationService {

    private DynamicDataIndicatorCategoryCreateCommandExecutor dynamicDataIndicatorCategoryCreateCommandExecutor;

    private DynamicDataIndicatorCategoryDeleteCommandExecutor dynamicDataIndicatorCategoryDeleteCommandExecutor;

    private DynamicDataIndicatorCategoryUpdateCommandExecutor dynamicDataIndicatorCategoryUpdateCommandExecutor;

    private DynamicDataIndicatorCategoryCommandExecutor dynamicDataIndicatorCategoryCommandExecutor;


    @Override
    public SingleResponse<DynamicDataIndicatorCategoryVO> create(DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand) {
        return dynamicDataIndicatorCategoryCreateCommandExecutor.execute(dynamicDataIndicatorCategoryCreateCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryVO> delete(IdCommand deleteCommand) {
        return dynamicDataIndicatorCategoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryVO> update(DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand) {
        return dynamicDataIndicatorCategoryUpdateCommandExecutor.execute(dynamicDataIndicatorCategoryUpdateCommand);
    }

    @Override
    public Response importData(DynamicDataIndicatorCategoryImportDataCommand importDataCommand,Boolean isPublic,Long batchId) {
        return dynamicDataIndicatorCategoryCommandExecutor.importData(importDataCommand,isPublic,batchId);
    }

    @Override
    public SingleResponse<Map<String, Object>> dataDelete(DynamicDataIndicatorCategoryDataDeleteCommand deleteCommand) {
        return dynamicDataIndicatorCategoryDeleteCommandExecutor.dataDelete(deleteCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorCategoryCreateCommandExecutor(DynamicDataIndicatorCategoryCreateCommandExecutor dynamicDataIndicatorCategoryCreateCommandExecutor) {
        this.dynamicDataIndicatorCategoryCreateCommandExecutor = dynamicDataIndicatorCategoryCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicDataIndicatorCategoryDeleteCommandExecutor(DynamicDataIndicatorCategoryDeleteCommandExecutor dynamicDataIndicatorCategoryDeleteCommandExecutor) {
        this.dynamicDataIndicatorCategoryDeleteCommandExecutor = dynamicDataIndicatorCategoryDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryUpdateCommandExecutor(DynamicDataIndicatorCategoryUpdateCommandExecutor dynamicDataIndicatorCategoryUpdateCommandExecutor) {
        this.dynamicDataIndicatorCategoryUpdateCommandExecutor = dynamicDataIndicatorCategoryUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryCommandExecutor(DynamicDataIndicatorCategoryCommandExecutor dynamicDataIndicatorCategoryCommandExecutor) {
        this.dynamicDataIndicatorCategoryCommandExecutor = dynamicDataIndicatorCategoryCommandExecutor;
    }
}
