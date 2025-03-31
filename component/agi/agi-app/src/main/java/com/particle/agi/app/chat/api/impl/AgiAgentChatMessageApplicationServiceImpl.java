package com.particle.agi.app.chat.api.impl;

import com.particle.agi.app.chat.executor.AgiAgentChatMessageCreateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageDeleteCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageUpdateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageUpdateCommand;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 智能体对话消息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentChatMessageApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageApplicationService {

    private AgiAgentChatMessageCreateCommandExecutor agiAgentChatMessageCreateCommandExecutor;

    private AgiAgentChatMessageDeleteCommandExecutor agiAgentChatMessageDeleteCommandExecutor;

    private AgiAgentChatMessageUpdateCommandExecutor agiAgentChatMessageUpdateCommandExecutor;

    private AgiAgentChatMessageCommandExecutor agiAgentChatMessageCommandExecutor;


    @Override
    public SingleResponse<AgiAgentChatMessageVO> create(AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand) {
        return agiAgentChatMessageCreateCommandExecutor.execute(agiAgentChatMessageCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageVO> delete(IdCommand deleteCommand) {
        return agiAgentChatMessageDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageVO> update(AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand) {
        return agiAgentChatMessageUpdateCommandExecutor.execute(agiAgentChatMessageUpdateCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageCreateCommandExecutor(AgiAgentChatMessageCreateCommandExecutor agiAgentChatMessageCreateCommandExecutor) {
        this.agiAgentChatMessageCreateCommandExecutor = agiAgentChatMessageCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentChatMessageDeleteCommandExecutor(AgiAgentChatMessageDeleteCommandExecutor agiAgentChatMessageDeleteCommandExecutor) {
        this.agiAgentChatMessageDeleteCommandExecutor = agiAgentChatMessageDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageUpdateCommandExecutor(AgiAgentChatMessageUpdateCommandExecutor agiAgentChatMessageUpdateCommandExecutor) {
        this.agiAgentChatMessageUpdateCommandExecutor = agiAgentChatMessageUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageCommandExecutor(AgiAgentChatMessageCommandExecutor agiAgentChatMessageCommandExecutor) {
        this.agiAgentChatMessageCommandExecutor = agiAgentChatMessageCommandExecutor;
    }
}
