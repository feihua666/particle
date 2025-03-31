package com.particle.agi.app.chat.api.impl;

import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolCreateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolDeleteCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolUpdateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatMessageToolCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolUpdateCommand;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageToolApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 智能体对话消息工具 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentChatMessageToolApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageToolApplicationService {

    private AgiAgentChatMessageToolCreateCommandExecutor agiAgentChatMessageToolCreateCommandExecutor;

    private AgiAgentChatMessageToolDeleteCommandExecutor agiAgentChatMessageToolDeleteCommandExecutor;

    private AgiAgentChatMessageToolUpdateCommandExecutor agiAgentChatMessageToolUpdateCommandExecutor;

    private AgiAgentChatMessageToolCommandExecutor agiAgentChatMessageToolCommandExecutor;


    @Override
    public SingleResponse<AgiAgentChatMessageToolVO> create(AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand) {
        return agiAgentChatMessageToolCreateCommandExecutor.execute(agiAgentChatMessageToolCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolVO> delete(IdCommand deleteCommand) {
        return agiAgentChatMessageToolDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolVO> update(AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand) {
        return agiAgentChatMessageToolUpdateCommandExecutor.execute(agiAgentChatMessageToolUpdateCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageToolCreateCommandExecutor(AgiAgentChatMessageToolCreateCommandExecutor agiAgentChatMessageToolCreateCommandExecutor) {
        this.agiAgentChatMessageToolCreateCommandExecutor = agiAgentChatMessageToolCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentChatMessageToolDeleteCommandExecutor(AgiAgentChatMessageToolDeleteCommandExecutor agiAgentChatMessageToolDeleteCommandExecutor) {
        this.agiAgentChatMessageToolDeleteCommandExecutor = agiAgentChatMessageToolDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageToolUpdateCommandExecutor(AgiAgentChatMessageToolUpdateCommandExecutor agiAgentChatMessageToolUpdateCommandExecutor) {
        this.agiAgentChatMessageToolUpdateCommandExecutor = agiAgentChatMessageToolUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatMessageToolCommandExecutor(AgiAgentChatMessageToolCommandExecutor agiAgentChatMessageToolCommandExecutor) {
        this.agiAgentChatMessageToolCommandExecutor = agiAgentChatMessageToolCommandExecutor;
    }
}
