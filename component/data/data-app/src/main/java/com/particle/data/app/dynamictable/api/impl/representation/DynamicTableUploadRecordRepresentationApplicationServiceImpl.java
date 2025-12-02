package com.particle.data.app.dynamictable.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamictable.executor.representation.DynamicTableUploadRecordQueryCommandExecutor;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableUploadRecordRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 动态数据表格上传记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Service
@CatchAndLog
public class DynamicTableUploadRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableUploadRecordRepresentationApplicationService {

    private DynamicTableUploadRecordQueryCommandExecutor dynamicTableUploadRecordQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicTableUploadRecordVO> queryDetail(IdCommand detailCommand) {
        return dynamicTableUploadRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicTableUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicTableUploadRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicTableUploadRecordVO> pageQuery(DynamicTableUploadRecordPageQueryCommand dynamicTableUploadRecordPageQueryCommand) {
        return dynamicTableUploadRecordQueryCommandExecutor.execute(dynamicTableUploadRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicTableUploadRecordVO> queryList(DynamicTableUploadRecordQueryListCommand dynamicTableUploadRecordQueryListCommand) {
        return dynamicTableUploadRecordQueryCommandExecutor.execute(dynamicTableUploadRecordQueryListCommand);
    }


    @Autowired
    public void setDynamicTableUploadRecordQueryCommandExecutor(DynamicTableUploadRecordQueryCommandExecutor dynamicTableUploadRecordQueryCommandExecutor) {
        this.dynamicTableUploadRecordQueryCommandExecutor = dynamicTableUploadRecordQueryCommandExecutor;
    }
}
