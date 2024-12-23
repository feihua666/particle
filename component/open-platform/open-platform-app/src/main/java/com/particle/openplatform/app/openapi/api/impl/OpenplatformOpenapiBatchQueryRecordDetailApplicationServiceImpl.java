package com.particle.openplatform.app.openapi.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDetailCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口批量查询记录明细 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiBatchQueryRecordDetailApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiBatchQueryRecordDetailApplicationService {

    private OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor openplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor openplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordDetailCommandExecutor openplatformOpenapiBatchQueryRecordDetailCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> create(OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand) {
        return openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordDetailCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> update(OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand openplatformOpenapiBatchQueryRecordDetailUpdateCommand) {
        return openplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor = openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor openplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor = openplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor openplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor = openplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDetailCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailCommandExecutor openplatformOpenapiBatchQueryRecordDetailCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDetailCommandExecutor = openplatformOpenapiBatchQueryRecordDetailCommandExecutor;
    }
}
