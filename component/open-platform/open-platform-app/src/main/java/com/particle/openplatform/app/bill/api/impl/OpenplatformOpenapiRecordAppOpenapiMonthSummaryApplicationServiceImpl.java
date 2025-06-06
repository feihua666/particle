package com.particle.openplatform.app.bill.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台应用开放接口月汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> create(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> update(OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
    }

    @Override
    public Response statistic(Integer year, Integer month,Boolean isIncludeDaySummary, Long openplatformAppId) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor.statistic(year, month, isIncludeDaySummary,openplatformAppId);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
    }
}
