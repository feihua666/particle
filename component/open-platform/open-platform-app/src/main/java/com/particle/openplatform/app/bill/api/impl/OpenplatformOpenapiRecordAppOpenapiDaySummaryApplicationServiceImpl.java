package com.particle.openplatform.app.bill.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * <p>
 * 开放平台应用开放接口日汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> create(OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> update(OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
    }

    @Override
    public Response statistic(LocalDate localDate,Long openplatformAppId) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor.statistic(localDate,openplatformAppId);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor;
    }
}
