package com.particle.data.app.dynamicdata.api.impl;

import com.particle.data.app.dynamicdata.executor.DynamicDataCategoryCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataCategoryDeleteCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataCategoryUpdateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataCategoryCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.api.IDynamicDataCategoryApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 动态数据分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Transactional
@Service
@CatchAndLog
public class DynamicDataCategoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataCategoryApplicationService {

    private DynamicDataCategoryCreateCommandExecutor dynamicDataCategoryCreateCommandExecutor;

    private DynamicDataCategoryDeleteCommandExecutor dynamicDataCategoryDeleteCommandExecutor;

    private DynamicDataCategoryUpdateCommandExecutor dynamicDataCategoryUpdateCommandExecutor;

    private DynamicDataCategoryCommandExecutor dynamicDataCategoryCommandExecutor;


    @Override
    public SingleResponse<DynamicDataCategoryVO> create(DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand) {
        return dynamicDataCategoryCreateCommandExecutor.execute(dynamicDataCategoryCreateCommand);
    }

    @Override
    public SingleResponse<DynamicDataCategoryVO> delete(IdCommand deleteCommand) {
        return dynamicDataCategoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicDataCategoryVO> update(DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand) {
        return dynamicDataCategoryUpdateCommandExecutor.execute(dynamicDataCategoryUpdateCommand);
    }


    @Autowired
    public void setDynamicDataCategoryCreateCommandExecutor(DynamicDataCategoryCreateCommandExecutor dynamicDataCategoryCreateCommandExecutor) {
        this.dynamicDataCategoryCreateCommandExecutor = dynamicDataCategoryCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicDataCategoryDeleteCommandExecutor(DynamicDataCategoryDeleteCommandExecutor dynamicDataCategoryDeleteCommandExecutor) {
        this.dynamicDataCategoryDeleteCommandExecutor = dynamicDataCategoryDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicDataCategoryUpdateCommandExecutor(DynamicDataCategoryUpdateCommandExecutor dynamicDataCategoryUpdateCommandExecutor) {
        this.dynamicDataCategoryUpdateCommandExecutor = dynamicDataCategoryUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicDataCategoryCommandExecutor(DynamicDataCategoryCommandExecutor dynamicDataCategoryCommandExecutor) {
        this.dynamicDataCategoryCommandExecutor = dynamicDataCategoryCommandExecutor;
    }
}
