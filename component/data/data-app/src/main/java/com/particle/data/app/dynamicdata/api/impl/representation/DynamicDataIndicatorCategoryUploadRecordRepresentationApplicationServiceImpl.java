package com.particle.data.app.dynamicdata.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamicdata.executor.representation.DynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 动态数据指标分类上传记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Service
@CatchAndLog
public class DynamicDataIndicatorCategoryUploadRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService {

    private DynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetail(IdCommand detailCommand) {
        return dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicDataIndicatorCategoryUploadRecordVO> pageQuery(DynamicDataIndicatorCategoryUploadRecordPageQueryCommand dynamicDataIndicatorCategoryUploadRecordPageQueryCommand) {
        return dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor.execute(dynamicDataIndicatorCategoryUploadRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryList(DynamicDataIndicatorCategoryUploadRecordQueryListCommand dynamicDataIndicatorCategoryUploadRecordQueryListCommand) {
        return dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor.execute(dynamicDataIndicatorCategoryUploadRecordQueryListCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor(DynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor) {
        this.dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor = dynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor;
    }
}
