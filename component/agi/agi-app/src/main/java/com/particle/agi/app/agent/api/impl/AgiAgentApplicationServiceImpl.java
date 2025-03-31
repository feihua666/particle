package com.particle.agi.app.agent.api.impl;

import com.particle.agi.app.agent.executor.AgiAgentCreateCommandExecutor;
import com.particle.agi.app.agent.executor.AgiAgentDeleteCommandExecutor;
import com.particle.agi.app.agent.executor.AgiAgentUpdateCommandExecutor;
import com.particle.agi.app.agent.executor.AgiAgentCommandExecutor;
import com.particle.agi.client.agent.dto.command.AgiAgentChatCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentChatResponseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.agent.dto.command.AgiAgentUpdateCommand;
import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import com.particle.agi.client.agent.dto.command.AgiAgentCreateCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 智能体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentApplicationService {

    private AgiAgentCreateCommandExecutor agiAgentCreateCommandExecutor;

    private AgiAgentDeleteCommandExecutor agiAgentDeleteCommandExecutor;

    private AgiAgentUpdateCommandExecutor agiAgentUpdateCommandExecutor;

    private AgiAgentCommandExecutor agiAgentCommandExecutor;


    @Override
    public SingleResponse<AgiAgentVO> create(AgiAgentCreateCommand agiAgentCreateCommand) {
        return agiAgentCreateCommandExecutor.execute(agiAgentCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentVO> delete(IdCommand deleteCommand) {
        return agiAgentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentVO> update(AgiAgentUpdateCommand agiAgentUpdateCommand) {
        return agiAgentUpdateCommandExecutor.execute(agiAgentUpdateCommand);
    }

    @Override
    public Flux<SingleResponse<AgiAgentChatResponseVO>> chatStream(AgiAgentChatCommand agiAgentChatCommand) {
        return agiAgentCommandExecutor.chatStream(agiAgentChatCommand);
    }


    @Autowired
    public void setAgiAgentCreateCommandExecutor(AgiAgentCreateCommandExecutor agiAgentCreateCommandExecutor) {
        this.agiAgentCreateCommandExecutor = agiAgentCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentDeleteCommandExecutor(AgiAgentDeleteCommandExecutor agiAgentDeleteCommandExecutor) {
        this.agiAgentDeleteCommandExecutor = agiAgentDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentUpdateCommandExecutor(AgiAgentUpdateCommandExecutor agiAgentUpdateCommandExecutor) {
        this.agiAgentUpdateCommandExecutor = agiAgentUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentCommandExecutor(AgiAgentCommandExecutor agiAgentCommandExecutor) {
        this.agiAgentCommandExecutor = agiAgentCommandExecutor;
    }
}
