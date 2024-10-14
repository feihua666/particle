package com.particle.openplatform.app.bill.api.impl;

import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppMonthBillCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppMonthBillDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppMonthBillUpdateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordAppMonthBillCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppMonthBillApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台应用月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppMonthBillApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppMonthBillApplicationService {

    private OpenplatformOpenapiRecordAppMonthBillCreateCommandExecutor openplatformOpenapiRecordAppMonthBillCreateCommandExecutor;

    private OpenplatformOpenapiRecordAppMonthBillDeleteCommandExecutor openplatformOpenapiRecordAppMonthBillDeleteCommandExecutor;

    private OpenplatformOpenapiRecordAppMonthBillUpdateCommandExecutor openplatformOpenapiRecordAppMonthBillUpdateCommandExecutor;

    private OpenplatformOpenapiRecordAppMonthBillCommandExecutor openplatformOpenapiRecordAppMonthBillCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> create(OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand) {
        return openplatformOpenapiRecordAppMonthBillCreateCommandExecutor.execute(openplatformOpenapiRecordAppMonthBillCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiRecordAppMonthBillDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> update(OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand) {
        return openplatformOpenapiRecordAppMonthBillUpdateCommandExecutor.execute(openplatformOpenapiRecordAppMonthBillUpdateCommand);
    }

    @Override
    public Response statistic(Integer year, Integer month, Boolean isIncludeMonthSummary, Boolean isIncludeDaySummary,Long openplatformAppId) {
        return openplatformOpenapiRecordAppMonthBillCommandExecutor.statistic(year,month,isIncludeMonthSummary,isIncludeDaySummary,openplatformAppId);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppMonthBillCreateCommandExecutor(OpenplatformOpenapiRecordAppMonthBillCreateCommandExecutor openplatformOpenapiRecordAppMonthBillCreateCommandExecutor) {
        this.openplatformOpenapiRecordAppMonthBillCreateCommandExecutor = openplatformOpenapiRecordAppMonthBillCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiRecordAppMonthBillDeleteCommandExecutor(OpenplatformOpenapiRecordAppMonthBillDeleteCommandExecutor openplatformOpenapiRecordAppMonthBillDeleteCommandExecutor) {
        this.openplatformOpenapiRecordAppMonthBillDeleteCommandExecutor = openplatformOpenapiRecordAppMonthBillDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppMonthBillUpdateCommandExecutor(OpenplatformOpenapiRecordAppMonthBillUpdateCommandExecutor openplatformOpenapiRecordAppMonthBillUpdateCommandExecutor) {
        this.openplatformOpenapiRecordAppMonthBillUpdateCommandExecutor = openplatformOpenapiRecordAppMonthBillUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordAppMonthBillCommandExecutor(OpenplatformOpenapiRecordAppMonthBillCommandExecutor openplatformOpenapiRecordAppMonthBillCommandExecutor) {
        this.openplatformOpenapiRecordAppMonthBillCommandExecutor = openplatformOpenapiRecordAppMonthBillCommandExecutor;
    }
}
