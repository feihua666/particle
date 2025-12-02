package com.particle.data.app.dynamicdata.api.impl;

import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryUploadRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordUpdateCommand;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 动态数据指标分类上传记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Transactional
@Service
@CatchAndLog
public class DynamicDataIndicatorCategoryUploadRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorCategoryUploadRecordApplicationService {

    private DynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor dynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor;

    private DynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor dynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor;

    private DynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor dynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor;

    private DynamicDataIndicatorCategoryUploadRecordCommandExecutor dynamicDataIndicatorCategoryUploadRecordCommandExecutor;


    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> create(DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand) {
        return dynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor.execute(dynamicDataIndicatorCategoryUploadRecordCreateCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> delete(IdCommand deleteCommand) {
        return dynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> update(DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand) {
        return dynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor.execute(dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> publish(IdCommand publishCommand) {
        return dynamicDataIndicatorCategoryUploadRecordCommandExecutor.publish(publishCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor(DynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor dynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor) {
        this.dynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor = dynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor(DynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor dynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor) {
        this.dynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor = dynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor(DynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor dynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor) {
        this.dynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor = dynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryUploadRecordCommandExecutor(DynamicDataIndicatorCategoryUploadRecordCommandExecutor dynamicDataIndicatorCategoryUploadRecordCommandExecutor) {
        this.dynamicDataIndicatorCategoryUploadRecordCommandExecutor = dynamicDataIndicatorCategoryUploadRecordCommandExecutor;
    }
}
