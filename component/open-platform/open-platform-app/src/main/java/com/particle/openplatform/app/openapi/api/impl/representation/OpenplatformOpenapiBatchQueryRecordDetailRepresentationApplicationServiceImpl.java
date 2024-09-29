package com.particle.openplatform.app.openapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口批量查询记录明细 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService {

    private OpenplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> pageQuery(OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand openplatformOpenapiBatchQueryRecordDetailPageQueryCommand) {
        return openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor.execute(openplatformOpenapiBatchQueryRecordDetailPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryList(OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand openplatformOpenapiBatchQueryRecordDetailQueryListCommand) {
        return openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor.execute(openplatformOpenapiBatchQueryRecordDetailQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor = openplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor;
    }
}
