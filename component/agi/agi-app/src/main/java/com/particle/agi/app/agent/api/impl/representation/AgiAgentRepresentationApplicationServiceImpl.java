package com.particle.agi.app.agent.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.agent.executor.representation.AgiAgentQueryCommandExecutor;
import com.particle.agi.client.agent.api.representation.IAgiAgentRepresentationApplicationService;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentPageQueryCommand;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentQueryListCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 智能体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Service
@CatchAndLog
public class AgiAgentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAgentRepresentationApplicationService {

    private AgiAgentQueryCommandExecutor agiAgentQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAgentVO> queryDetail(IdCommand detailCommand) {
        return agiAgentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAgentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiAgentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAgentVO> pageQuery(AgiAgentPageQueryCommand agiAgentPageQueryCommand) {
        return agiAgentQueryCommandExecutor.execute(agiAgentPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAgentVO> queryList(AgiAgentQueryListCommand agiAgentQueryListCommand) {
        return agiAgentQueryCommandExecutor.execute(agiAgentQueryListCommand);
    }


    @Autowired
    public void setAgiAgentQueryCommandExecutor(AgiAgentQueryCommandExecutor agiAgentQueryCommandExecutor) {
        this.agiAgentQueryCommandExecutor = agiAgentQueryCommandExecutor;
    }
}
