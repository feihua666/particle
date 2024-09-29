package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台客户月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService {

    private OpenplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> pageQuery(OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand openplatformOpenapiRecordCustomerMonthBillPageQueryCommand) {
        return openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor.execute(openplatformOpenapiRecordCustomerMonthBillPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryList(OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand openplatformOpenapiRecordCustomerMonthBillQueryListCommand) {
        return openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor.execute(openplatformOpenapiRecordCustomerMonthBillQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor(OpenplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor) {
        this.openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor = openplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor;
    }
}
