package com.particle.openplatform.app.openapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.representation.OpenplatformOpenapiBatchQueryRecordQueryCommandExecutor;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口批量查询记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiBatchQueryRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService {

    private OpenplatformOpenapiBatchQueryRecordQueryCommandExecutor openplatformOpenapiBatchQueryRecordQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiBatchQueryRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiBatchQueryRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiBatchQueryRecordVO> pageQuery(OpenplatformOpenapiBatchQueryRecordPageQueryCommand openplatformOpenapiBatchQueryRecordPageQueryCommand) {
        return openplatformOpenapiBatchQueryRecordQueryCommandExecutor.execute(openplatformOpenapiBatchQueryRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiBatchQueryRecordVO> queryList(OpenplatformOpenapiBatchQueryRecordQueryListCommand openplatformOpenapiBatchQueryRecordQueryListCommand) {
        return openplatformOpenapiBatchQueryRecordQueryCommandExecutor.execute(openplatformOpenapiBatchQueryRecordQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordQueryCommandExecutor(OpenplatformOpenapiBatchQueryRecordQueryCommandExecutor openplatformOpenapiBatchQueryRecordQueryCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordQueryCommandExecutor = openplatformOpenapiBatchQueryRecordQueryCommandExecutor;
    }
}
