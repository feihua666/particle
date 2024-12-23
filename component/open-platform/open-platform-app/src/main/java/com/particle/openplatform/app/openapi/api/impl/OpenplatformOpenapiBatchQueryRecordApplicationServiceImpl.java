package com.particle.openplatform.app.openapi.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDeleteCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordUpdateCommandExecutor;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口批量查询记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiBatchQueryRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiBatchQueryRecordApplicationService {

    private OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor openplatformOpenapiBatchQueryRecordCreateCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordDeleteCommandExecutor openplatformOpenapiBatchQueryRecordDeleteCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordUpdateCommandExecutor openplatformOpenapiBatchQueryRecordUpdateCommandExecutor;

    private OpenplatformOpenapiBatchQueryRecordCommandExecutor openplatformOpenapiBatchQueryRecordCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> create(OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand) {
        return openplatformOpenapiBatchQueryRecordCreateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiBatchQueryRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> update(OpenplatformOpenapiBatchQueryRecordUpdateCommand openplatformOpenapiBatchQueryRecordUpdateCommand) {
        return openplatformOpenapiBatchQueryRecordUpdateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordUpdateCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordCreateCommandExecutor(OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor openplatformOpenapiBatchQueryRecordCreateCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordCreateCommandExecutor = openplatformOpenapiBatchQueryRecordCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordDeleteCommandExecutor(OpenplatformOpenapiBatchQueryRecordDeleteCommandExecutor openplatformOpenapiBatchQueryRecordDeleteCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordDeleteCommandExecutor = openplatformOpenapiBatchQueryRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordUpdateCommandExecutor(OpenplatformOpenapiBatchQueryRecordUpdateCommandExecutor openplatformOpenapiBatchQueryRecordUpdateCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordUpdateCommandExecutor = openplatformOpenapiBatchQueryRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiBatchQueryRecordCommandExecutor(OpenplatformOpenapiBatchQueryRecordCommandExecutor openplatformOpenapiBatchQueryRecordCommandExecutor) {
        this.openplatformOpenapiBatchQueryRecordCommandExecutor = openplatformOpenapiBatchQueryRecordCommandExecutor;
    }
}
