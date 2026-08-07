package com.particle.agi.app.model.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.model.executor.representation.AgiModelProviderQueryCommandExecutor;
import com.particle.agi.client.model.api.representation.IAgiModelProviderRepresentationApplicationService;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * AI模型提供商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Service
@CatchAndLog
public class AgiModelProviderRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiModelProviderRepresentationApplicationService {

    private AgiModelProviderQueryCommandExecutor agiModelProviderQueryCommandExecutor;

    @Override
    public SingleResponse<AgiModelProviderVO> queryDetail(CommonIdCommand detailCommand) {
        return agiModelProviderQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiModelProviderVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return agiModelProviderQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiModelProviderVO> pageQuery(AgiModelProviderPageQueryCommand agiModelProviderPageQueryCommand) {
        return agiModelProviderQueryCommandExecutor.execute(agiModelProviderPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiModelProviderVO> queryList(AgiModelProviderQueryListCommand agiModelProviderQueryListCommand) {
        return agiModelProviderQueryCommandExecutor.execute(agiModelProviderQueryListCommand);
    }


    @Autowired
    public void setAgiModelProviderQueryCommandExecutor(AgiModelProviderQueryCommandExecutor agiModelProviderQueryCommandExecutor) {
        this.agiModelProviderQueryCommandExecutor = agiModelProviderQueryCommandExecutor;
    }
}
