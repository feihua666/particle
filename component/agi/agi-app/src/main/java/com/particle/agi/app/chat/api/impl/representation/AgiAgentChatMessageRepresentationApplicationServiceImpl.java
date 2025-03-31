package com.particle.agi.app.chat.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.chat.executor.representation.AgiAgentChatMessageQueryCommandExecutor;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体对话消息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Service
@CatchAndLog
public class AgiAgentChatMessageRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageRepresentationApplicationService {

    private AgiAgentChatMessageQueryCommandExecutor agiAgentChatMessageQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentChatMessageVO> queryDetail(IdCommand detailCommand) {
        return agiAgentChatMessageQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentChatMessageQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentChatMessageVO> pageQuery(AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand) {
        return agiAgentChatMessageQueryCommandExecutor.execute(agiAgentChatMessagePageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentChatMessageVO> queryList(AgiAgentChatMessageQueryListCommand agiAgentChatMessageQueryListCommand) {
        return agiAgentChatMessageQueryCommandExecutor.execute(agiAgentChatMessageQueryListCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageQueryCommandExecutor(AgiAgentChatMessageQueryCommandExecutor agiAgentChatMessageQueryCommandExecutor) {
        this.agiAgentChatMessageQueryCommandExecutor = agiAgentChatMessageQueryCommandExecutor;
    }
}
