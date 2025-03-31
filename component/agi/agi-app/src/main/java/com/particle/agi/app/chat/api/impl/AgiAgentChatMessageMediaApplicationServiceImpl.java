package com.particle.agi.app.chat.api.impl;

import com.particle.agi.app.chat.executor.AgiAgentChatMessageMediaCreateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageMediaDeleteCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageMediaUpdateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageMediaCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaUpdateCommand;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageMediaApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 智能体对话消息媒体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentChatMessageMediaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageMediaApplicationService {

    private AgiAgentChatMessageMediaCreateCommandExecutor agiAgentChatMessageMediaCreateCommandExecutor;

    private AgiAgentChatMessageMediaDeleteCommandExecutor agiAgentChatMessageMediaDeleteCommandExecutor;

    private AgiAgentChatMessageMediaUpdateCommandExecutor agiAgentChatMessageMediaUpdateCommandExecutor;

    private AgiAgentChatMessageMediaCommandExecutor agiAgentChatMessageMediaCommandExecutor;


    @Override
    public SingleResponse<AgiAgentChatMessageMediaVO> create(AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand) {
        return agiAgentChatMessageMediaCreateCommandExecutor.execute(agiAgentChatMessageMediaCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageMediaVO> delete(IdCommand deleteCommand) {
        return agiAgentChatMessageMediaDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageMediaVO> update(AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand) {
        return agiAgentChatMessageMediaUpdateCommandExecutor.execute(agiAgentChatMessageMediaUpdateCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageMediaCreateCommandExecutor(AgiAgentChatMessageMediaCreateCommandExecutor agiAgentChatMessageMediaCreateCommandExecutor) {
        this.agiAgentChatMessageMediaCreateCommandExecutor = agiAgentChatMessageMediaCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentChatMessageMediaDeleteCommandExecutor(AgiAgentChatMessageMediaDeleteCommandExecutor agiAgentChatMessageMediaDeleteCommandExecutor) {
        this.agiAgentChatMessageMediaDeleteCommandExecutor = agiAgentChatMessageMediaDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageMediaUpdateCommandExecutor(AgiAgentChatMessageMediaUpdateCommandExecutor agiAgentChatMessageMediaUpdateCommandExecutor) {
        this.agiAgentChatMessageMediaUpdateCommandExecutor = agiAgentChatMessageMediaUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageMediaCommandExecutor(AgiAgentChatMessageMediaCommandExecutor agiAgentChatMessageMediaCommandExecutor) {
        this.agiAgentChatMessageMediaCommandExecutor = agiAgentChatMessageMediaCommandExecutor;
    }
}
