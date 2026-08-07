package com.particle.agi.app.model.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.model.executor.representation.AgiAiModelQueryCommandExecutor;
import com.particle.agi.client.model.api.representation.IAgiAiModelRepresentationApplicationService;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * AI模型 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Service
@CatchAndLog
public class AgiAiModelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiAiModelRepresentationApplicationService {

    private AgiAiModelQueryCommandExecutor agiAiModelQueryCommandExecutor;

    @Override
    public SingleResponse<AgiAiModelVO> queryDetail(CommonIdCommand detailCommand) {
        return agiAiModelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiAiModelVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return agiAiModelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiAiModelVO> pageQuery(AgiAiModelPageQueryCommand agiAiModelPageQueryCommand) {
        return agiAiModelQueryCommandExecutor.execute(agiAiModelPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiAiModelVO> queryList(AgiAiModelQueryListCommand agiAiModelQueryListCommand) {
        return agiAiModelQueryCommandExecutor.execute(agiAiModelQueryListCommand);
    }


    @Autowired
    public void setAgiAiModelQueryCommandExecutor(AgiAiModelQueryCommandExecutor agiAiModelQueryCommandExecutor) {
        this.agiAiModelQueryCommandExecutor = agiAiModelQueryCommandExecutor;
    }
}
