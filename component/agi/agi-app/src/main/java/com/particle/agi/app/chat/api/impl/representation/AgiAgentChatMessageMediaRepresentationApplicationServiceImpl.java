package com.particle.agi.app.chat.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.chat.executor.representation.AgiAgentChatMessageMediaQueryCommandExecutor;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageMediaRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体对话消息媒体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Service
@CatchAndLog
public class AgiAgentChatMessageMediaRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatMessageMediaRepresentationApplicationService {

    private AgiAgentChatMessageMediaQueryCommandExecutor agiAgentChatMessageMediaQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentChatMessageMediaVO> queryDetail(IdCommand detailCommand) {
        return agiAgentChatMessageMediaQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatMessageMediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentChatMessageMediaQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentChatMessageMediaVO> pageQuery(AgiAgentChatMessageMediaPageQueryCommand agiAgentChatMessageMediaPageQueryCommand) {
        return agiAgentChatMessageMediaQueryCommandExecutor.execute(agiAgentChatMessageMediaPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentChatMessageMediaVO> queryList(AgiAgentChatMessageMediaQueryListCommand agiAgentChatMessageMediaQueryListCommand) {
        return agiAgentChatMessageMediaQueryCommandExecutor.execute(agiAgentChatMessageMediaQueryListCommand);
    }


    @Autowired
    public void setAgiAgentChatMessageMediaQueryCommandExecutor(AgiAgentChatMessageMediaQueryCommandExecutor agiAgentChatMessageMediaQueryCommandExecutor) {
        this.agiAgentChatMessageMediaQueryCommandExecutor = agiAgentChatMessageMediaQueryCommandExecutor;
    }
}
