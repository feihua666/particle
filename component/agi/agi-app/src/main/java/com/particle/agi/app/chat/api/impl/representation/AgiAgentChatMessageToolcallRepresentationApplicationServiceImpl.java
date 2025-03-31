package com.particle.agi.app.chat.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.chat.executor.representation.AgiAgentChatMessageToolcallQueryCommandExecutor;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageToolcallRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体对话消息工具调用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Service
@CatchAndLog
public class AgiAgentChatMessageToolcallRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageToolcallRepresentationApplicationService {

    private AgiAgentChatMessageToolcallQueryCommandExecutor agiAgentChatMessageToolcallQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentChatMessageToolcallVO> queryDetail(IdCommand detailCommand) {
        return agiAgentChatMessageToolcallQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolcallVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentChatMessageToolcallQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentChatMessageToolcallVO> pageQuery(AgiAgentChatMessageToolcallPageQueryCommand agiAgentChatMessageToolcallPageQueryCommand) {
        return agiAgentChatMessageToolcallQueryCommandExecutor.execute(agiAgentChatMessageToolcallPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentChatMessageToolcallVO> queryList(AgiAgentChatMessageToolcallQueryListCommand agiAgentChatMessageToolcallQueryListCommand) {
        return agiAgentChatMessageToolcallQueryCommandExecutor.execute(agiAgentChatMessageToolcallQueryListCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageToolcallQueryCommandExecutor(AgiAgentChatMessageToolcallQueryCommandExecutor agiAgentChatMessageToolcallQueryCommandExecutor) {
        this.agiAgentChatMessageToolcallQueryCommandExecutor = agiAgentChatMessageToolcallQueryCommandExecutor;
    }
}
