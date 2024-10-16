package com.particle.openplatform.app.bill.api.impl;

import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor;

    private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> create(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> update(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor = openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor = openplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor = openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor;
    }
}
