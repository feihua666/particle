package com.particle.openplatform.app.bill.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiDaySummaryCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台供应商接口日汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdApiDaySummaryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdApiDaySummaryApplicationService {

    private OpenplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor openplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor;

    private OpenplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor openplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor;

    private OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor openplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor;

    private OpenplatformProviderRecordPrdApiDaySummaryCommandExecutor openplatformProviderRecordPrdApiDaySummaryCommandExecutor;


    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> create(OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand) {
        return openplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor.execute(openplatformProviderRecordPrdApiDaySummaryCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> delete(IdCommand deleteCommand) {
        return openplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> update(OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand) {
        return openplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor.execute(openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor(OpenplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor openplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor) {
        this.openplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor = openplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor(OpenplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor openplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor) {
        this.openplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor = openplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor(OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor openplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor) {
        this.openplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor = openplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdApiDaySummaryCommandExecutor(OpenplatformProviderRecordPrdApiDaySummaryCommandExecutor openplatformProviderRecordPrdApiDaySummaryCommandExecutor) {
        this.openplatformProviderRecordPrdApiDaySummaryCommandExecutor = openplatformProviderRecordPrdApiDaySummaryCommandExecutor;
    }
}
