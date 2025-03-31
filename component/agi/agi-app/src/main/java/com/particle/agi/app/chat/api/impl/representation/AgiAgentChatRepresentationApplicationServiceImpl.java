package com.particle.agi.app.chat.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.chat.executor.representation.AgiAgentChatQueryCommandExecutor;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体对话 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Service
@CatchAndLog
public class AgiAgentChatRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentChatRepresentationApplicationService {

    private AgiAgentChatQueryCommandExecutor agiAgentChatQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentChatVO> queryDetail(IdCommand detailCommand) {
        return agiAgentChatQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentChatVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentChatQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentChatVO> pageQuery(AgiAgentChatPageQueryCommand agiAgentChatPageQueryCommand) {
        return agiAgentChatQueryCommandExecutor.execute(agiAgentChatPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentChatVO> queryList(AgiAgentChatQueryListCommand agiAgentChatQueryListCommand) {
        return agiAgentChatQueryCommandExecutor.execute(agiAgentChatQueryListCommand);
    }


    @Autowired
    public void setAgiAgentChatQueryCommandExecutor(AgiAgentChatQueryCommandExecutor agiAgentChatQueryCommandExecutor) {
        this.agiAgentChatQueryCommandExecutor = agiAgentChatQueryCommandExecutor;
    }
}
