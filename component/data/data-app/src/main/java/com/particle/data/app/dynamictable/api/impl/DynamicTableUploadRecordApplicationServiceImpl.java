package com.particle.data.app.dynamictable.api.impl;

import com.particle.data.app.dynamictable.executor.DynamicTableUploadRecordCreateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableUploadRecordDeleteCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableUploadRecordUpdateCommandExecutor;
import com.particle.data.app.dynamictable.executor.DynamicTableUploadRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordUpdateCommand;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 动态数据表格上传记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Transactional
@Service
@CatchAndLog
public class DynamicTableUploadRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableUploadRecordApplicationService {

    private DynamicTableUploadRecordCreateCommandExecutor dynamicTableUploadRecordCreateCommandExecutor;

    private DynamicTableUploadRecordDeleteCommandExecutor dynamicTableUploadRecordDeleteCommandExecutor;

    private DynamicTableUploadRecordUpdateCommandExecutor dynamicTableUploadRecordUpdateCommandExecutor;

    private DynamicTableUploadRecordCommandExecutor dynamicTableUploadRecordCommandExecutor;


    @Override
    public SingleResponse<DynamicTableUploadRecordVO> create(DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand) {
        return dynamicTableUploadRecordCreateCommandExecutor.execute(dynamicTableUploadRecordCreateCommand);
    }

    @Override
    public SingleResponse<DynamicTableUploadRecordVO> delete(IdCommand deleteCommand) {
        return dynamicTableUploadRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DynamicTableUploadRecordVO> update(DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand) {
        return dynamicTableUploadRecordUpdateCommandExecutor.execute(dynamicTableUploadRecordUpdateCommand);
    }

    @Override
    public SingleResponse<DynamicTableUploadRecordVO> publish(IdCommand publishCommand) {
        return dynamicTableUploadRecordCommandExecutor.publish(publishCommand);
    }


    @Autowired
    public void setDynamicTableUploadRecordCreateCommandExecutor(DynamicTableUploadRecordCreateCommandExecutor dynamicTableUploadRecordCreateCommandExecutor) {
        this.dynamicTableUploadRecordCreateCommandExecutor = dynamicTableUploadRecordCreateCommandExecutor;
    }

    @Autowired
    public void setDynamicTableUploadRecordDeleteCommandExecutor(DynamicTableUploadRecordDeleteCommandExecutor dynamicTableUploadRecordDeleteCommandExecutor) {
        this.dynamicTableUploadRecordDeleteCommandExecutor = dynamicTableUploadRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicTableUploadRecordUpdateCommandExecutor(DynamicTableUploadRecordUpdateCommandExecutor dynamicTableUploadRecordUpdateCommandExecutor) {
        this.dynamicTableUploadRecordUpdateCommandExecutor = dynamicTableUploadRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableUploadRecordCommandExecutor(DynamicTableUploadRecordCommandExecutor dynamicTableUploadRecordCommandExecutor) {
        this.dynamicTableUploadRecordCommandExecutor = dynamicTableUploadRecordCommandExecutor;
    }
}
