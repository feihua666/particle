package com.particle.agi.app.chat.api.impl;

import com.particle.agi.app.chat.executor.AgiAgentChatCreateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatDeleteCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatUpdateCommandExecutor;
import com.particle.agi.app.chat.executor.AgiAgentChatCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatUpdateCommand;
import com.particle.agi.client.chat.api.IAgiAgentChatApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 智能体对话 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Transactional
@Service
@CatchAndLog
public class AgiAgentChatApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatApplicationService {

    private AgiAgentChatCreateCommandExecutor agiAgentChatCreateCommandExecutor;

    private AgiAgentChatDeleteCommandExecutor agiAgentChatDeleteCommandExecutor;

    private AgiAgentChatUpdateCommandExecutor agiAgentChatUpdateCommandExecutor;

    private AgiAgentChatCommandExecutor agiAgentChatCommandExecutor;


    @Override
    public SingleResponse<AgiAgentChatVO> create(AgiAgentChatCreateCommand agiAgentChatCreateCommand) {
        return agiAgentChatCreateCommandExecutor.execute(agiAgentChatCreateCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatVO> delete(IdCommand deleteCommand) {
        return agiAgentChatDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatVO> update(AgiAgentChatUpdateCommand agiAgentChatUpdateCommand) {
        return agiAgentChatUpdateCommandExecutor.execute(agiAgentChatUpdateCommand);
    }


    @Autowired
    public void setAgiAgentChatCreateCommandExecutor(AgiAgentChatCreateCommandExecutor agiAgentChatCreateCommandExecutor) {
        this.agiAgentChatCreateCommandExecutor = agiAgentChatCreateCommandExecutor;
    }

    @Autowired
    public void setAgiAgentChatDeleteCommandExecutor(AgiAgentChatDeleteCommandExecutor agiAgentChatDeleteCommandExecutor) {
        this.agiAgentChatDeleteCommandExecutor = agiAgentChatDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatUpdateCommandExecutor(AgiAgentChatUpdateCommandExecutor agiAgentChatUpdateCommandExecutor) {
        this.agiAgentChatUpdateCommandExecutor = agiAgentChatUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiAgentChatCommandExecutor(AgiAgentChatCommandExecutor agiAgentChatCommandExecutor) {
        this.agiAgentChatCommandExecutor = agiAgentChatCommandExecutor;
    }
}
