package com.particle.agi.app.model.api.impl;

import com.particle.agi.app.model.executor.AgiModelProviderCreateCommandExecutor;
import com.particle.agi.app.model.executor.AgiModelProviderDeleteCommandExecutor;
import com.particle.agi.app.model.executor.AgiModelProviderUpdateCommandExecutor;
import com.particle.agi.app.model.executor.AgiModelProviderCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.agi.client.model.dto.command.AgiModelProviderUpdateCommand;
import com.particle.agi.client.model.api.IAgiModelProviderApplicationService;
import com.particle.agi.client.model.dto.command.AgiModelProviderCreateCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * AI模型提供商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Transactional
@Service
@CatchAndLog
public class AgiModelProviderApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiModelProviderApplicationService {

    private AgiModelProviderCreateCommandExecutor agiModelProviderCreateCommandExecutor;

    private AgiModelProviderDeleteCommandExecutor agiModelProviderDeleteCommandExecutor;

    private AgiModelProviderUpdateCommandExecutor agiModelProviderUpdateCommandExecutor;

    private AgiModelProviderCommandExecutor agiModelProviderCommandExecutor;


    @Override
    public SingleResponse<AgiModelProviderVO> create(AgiModelProviderCreateCommand agiModelProviderCreateCommand) {
        return agiModelProviderCreateCommandExecutor.execute(agiModelProviderCreateCommand);
    }

    @Override
    public SingleResponse<AgiModelProviderVO> delete(CommonIdCommand deleteCommand) {
        return agiModelProviderDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiModelProviderVO> update(AgiModelProviderUpdateCommand agiModelProviderUpdateCommand) {
        return agiModelProviderUpdateCommandExecutor.execute(agiModelProviderUpdateCommand);
    }


    @Autowired
    public void setAgiModelProviderCreateCommandExecutor(AgiModelProviderCreateCommandExecutor agiModelProviderCreateCommandExecutor) {
        this.agiModelProviderCreateCommandExecutor = agiModelProviderCreateCommandExecutor;
    }

    @Autowired
    public void setAgiModelProviderDeleteCommandExecutor(AgiModelProviderDeleteCommandExecutor agiModelProviderDeleteCommandExecutor) {
        this.agiModelProviderDeleteCommandExecutor = agiModelProviderDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiModelProviderUpdateCommandExecutor(AgiModelProviderUpdateCommandExecutor agiModelProviderUpdateCommandExecutor) {
        this.agiModelProviderUpdateCommandExecutor = agiModelProviderUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiModelProviderCommandExecutor(AgiModelProviderCommandExecutor agiModelProviderCommandExecutor) {
        this.agiModelProviderCommandExecutor = agiModelProviderCommandExecutor;
    }
}
