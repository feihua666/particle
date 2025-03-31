package com.particle.agi.app.chat.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.chat.executor.representation.AgiAgentChatMessageToolQueryCommandExecutor;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageToolRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体对话消息工具 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Service
@CatchAndLog
public class AgiAgentChatMessageToolRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageToolRepresentationApplicationService {

    private AgiAgentChatMessageToolQueryCommandExecutor agiAgentChatMessageToolQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentChatMessageToolVO> queryDetail(IdCommand detailCommand) {
        return agiAgentChatMessageToolQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageToolVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentChatMessageToolQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentChatMessageToolVO> pageQuery(AgiAgentChatMessageToolPageQueryCommand agiAgentChatMessageToolPageQueryCommand) {
        return agiAgentChatMessageToolQueryCommandExecutor.execute(agiAgentChatMessageToolPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentChatMessageToolVO> queryList(AgiAgentChatMessageToolQueryListCommand agiAgentChatMessageToolQueryListCommand) {
        return agiAgentChatMessageToolQueryCommandExecutor.execute(agiAgentChatMessageToolQueryListCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageToolQueryCommandExecutor(AgiAgentChatMessageToolQueryCommandExecutor agiAgentChatMessageToolQueryCommandExecutor) {
        this.agiAgentChatMessageToolQueryCommandExecutor = agiAgentChatMessageToolQueryCommandExecutor;
    }
}
