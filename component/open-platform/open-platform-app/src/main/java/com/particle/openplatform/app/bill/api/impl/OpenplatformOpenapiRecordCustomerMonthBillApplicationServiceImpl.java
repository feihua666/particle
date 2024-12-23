package com.particle.openplatform.app.bill.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordCustomerMonthBillCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordCustomerMonthBillApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台客户月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordCustomerMonthBillApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordCustomerMonthBillApplicationService {

    private OpenplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor openplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor;

    private OpenplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor openplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor;

    private OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor openplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor;

    private OpenplatformOpenapiRecordCustomerMonthBillCommandExecutor openplatformOpenapiRecordCustomerMonthBillCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> create(OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand) {
        return openplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor.execute(openplatformOpenapiRecordCustomerMonthBillCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> update(OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand) {
        return openplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor.execute(openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
    }

    @Override
    public Response statistic(Integer year, Integer month, Boolean isIncludeMonthSummary, Boolean isIncludeDaySummary) {
        return openplatformOpenapiRecordCustomerMonthBillCommandExecutor.statistic(year, month, isIncludeMonthSummary, isIncludeDaySummary);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor(OpenplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor openplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor) {
        this.openplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor = openplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor(OpenplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor openplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor) {
        this.openplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor = openplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor(OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor openplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor) {
        this.openplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor = openplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiRecordCustomerMonthBillCommandExecutor(OpenplatformOpenapiRecordCustomerMonthBillCommandExecutor openplatformOpenapiRecordCustomerMonthBillCommandExecutor) {
        this.openplatformOpenapiRecordCustomerMonthBillCommandExecutor = openplatformOpenapiRecordCustomerMonthBillCommandExecutor;
    }
}
