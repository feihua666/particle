package com.particle.openplatform.app.bill.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiMonthSummaryCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiMonthSummaryApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台供应商接口月汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdApiMonthSummaryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdApiMonthSummaryApplicationService {

    private OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor openplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor;

    private OpenplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor openplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor;

    private OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor openplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor;

    private OpenplatformProviderRecordPrdApiMonthSummaryCommandExecutor openplatformProviderRecordPrdApiMonthSummaryCommandExecutor;


    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> create(OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor.execute(openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> delete(IdCommand deleteCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> update(OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor.execute(openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor(OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor openplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor) {
        this.openplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor = openplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor(OpenplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor openplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor) {
        this.openplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor = openplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor(OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor openplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor) {
        this.openplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor = openplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdApiMonthSummaryCommandExecutor(OpenplatformProviderRecordPrdApiMonthSummaryCommandExecutor openplatformProviderRecordPrdApiMonthSummaryCommandExecutor) {
        this.openplatformProviderRecordPrdApiMonthSummaryCommandExecutor = openplatformProviderRecordPrdApiMonthSummaryCommandExecutor;
    }
}
