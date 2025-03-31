package com.particle.agi.app.chat.api.impl;

import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolcallCreateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolcallDeleteCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolcallUpdateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolcallCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallUpdateCommand;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageToolcallApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 智能体对话消息工具调用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentChatMessageToolcallApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageToolcallApplicationService {

    private AgiAgentChatMessageToolcallCreateCommandExecutor agiAgentChatMessageToolcallCreateCommandExecutor;

    private AgiAgentChatMessageToolcallDeleteCommandExecutor agiAgentChatMessageToolcallDeleteCommandExecutor;

    private AgiAgentChatMessageToolcallUpdateCommandExecutor agiAgentChatMessageToolcallUpdateCommandExecutor;

    private AgiAgentChatMessageToolcallCommandExecutor agiAgentChatMessageToolcallCommandExecutor;


    @Override
    public SingleResponse<AgiAgentChatMessageToolcallVO> create(AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand) {
        return agiAgentChatMessageToolcallCreateCommandExecutor.execute(agiAgentChatMessageToolcallCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolcallVO> delete(IdCommand deleteCommand) {
        return agiAgentChatMessageToolcallDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolcallVO> update(AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand) {
        return agiAgentChatMessageToolcallUpdateCommandExecutor.execute(agiAgentChatMessageToolcallUpdateCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageToolcallCreateCommandExecutor(AgiAgentChatMessageToolcallCreateCommandExecutor agiAgentChatMessageToolcallCreateCommandExecutor) {
        this.agiAgentChatMessageToolcallCreateCommandExecutor = agiAgentChatMessageToolcallCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentChatMessageToolcallDeleteCommandExecutor(AgiAgentChatMessageToolcallDeleteCommandExecutor agiAgentChatMessageToolcallDeleteCommandExecutor) {
        this.agiAgentChatMessageToolcallDeleteCommandExecutor = agiAgentChatMessageToolcallDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageToolcallUpdateCommandExecutor(AgiAgentChatMessageToolcallUpdateCommandExecutor agiAgentChatMessageToolcallUpdateCommandExecutor) {
        this.agiAgentChatMessageToolcallUpdateCommandExecutor = agiAgentChatMessageToolcallUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageToolcallCommandExecutor(AgiAgentChatMessageToolcallCommandExecutor agiAgentChatMessageToolcallCommandExecutor) {
        this.agiAgentChatMessageToolcallCommandExecutor = agiAgentChatMessageToolcallCommandExecutor;
    }
}
