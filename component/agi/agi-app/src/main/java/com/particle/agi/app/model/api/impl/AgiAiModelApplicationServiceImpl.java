package com.particle.agi.app.model.api.impl;

import com.particle.agi.app.model.executor.AgiAiModelCreateCommandExecutor;
import com.particle.agi.app.model.executor.AgiAiModelDeleteCommandExecutor;
import com.particle.agi.app.model.executor.AgiAiModelUpdateCommandExecutor;
import com.particle.agi.app.model.executor.AgiAiModelCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.agi.client.model.dto.command.AgiAiModelUpdateCommand;
import com.particle.agi.client.model.api.IAgiAiModelApplicationService;
import com.particle.agi.client.model.dto.command.AgiAiModelCreateCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * AI模型 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Transactional
@Service
@CatchAndLog
public class AgiAiModelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAiModelApplicationService {

    private AgiAiModelCreateCommandExecutor agiAiModelCreateCommandExecutor;

    private AgiAiModelDeleteCommandExecutor agiAiModelDeleteCommandExecutor;

    private AgiAiModelUpdateCommandExecutor agiAiModelUpdateCommandExecutor;

    private AgiAiModelCommandExecutor agiAiModelCommandExecutor;


    @Override
    public SingleResponse<AgiAiModelVO> create(AgiAiModelCreateCommand agiAiModelCreateCommand) {
        return agiAiModelCreateCommandExecutor.execute(agiAiModelCreateCommand);
    }

    @Override
    public SingleResponse<AgiAiModelVO> delete(CommonIdCommand deleteCommand) {
        return agiAiModelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAiModelVO> update(AgiAiModelUpdateCommand agiAiModelUpdateCommand) {
        return agiAiModelUpdateCommandExecutor.execute(agiAiModelUpdateCommand);
    }


    @Autowired
    public void setAgiAiModelCreateCommandExecutor(AgiAiModelCreateCommandExecutor agiAiModelCreateCommandExecutor) {
        this.agiAiModelCreateCommandExecutor = agiAiModelCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAiModelDeleteCommandExecutor(AgiAiModelDeleteCommandExecutor agiAiModelDeleteCommandExecutor) {
        this.agiAiModelDeleteCommandExecutor = agiAiModelDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAiModelUpdateCommandExecutor(AgiAiModelUpdateCommandExecutor agiAiModelUpdateCommandExecutor) {
        this.agiAiModelUpdateCommandExecutor = agiAiModelUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAiModelCommandExecutor(AgiAiModelCommandExecutor agiAiModelCommandExecutor) {
        this.agiAiModelCommandExecutor = agiAiModelCommandExecutor;
    }
}
