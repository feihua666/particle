package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformOpenapiRecordAppMonthBillQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppMonthBillRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService {

    private OpenplatformOpenapiRecordAppMonthBillQueryCommandExecutor openplatformOpenapiRecordAppMonthBillQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordAppMonthBillQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiRecordAppMonthBillQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordAppMonthBillVO> pageQuery(OpenplatformOpenapiRecordAppMonthBillPageQueryCommand openplatformOpenapiRecordAppMonthBillPageQueryCommand) {
        return openplatformOpenapiRecordAppMonthBillQueryCommandExecutor.execute(openplatformOpenapiRecordAppMonthBillPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryList(OpenplatformOpenapiRecordAppMonthBillQueryListCommand openplatformOpenapiRecordAppMonthBillQueryListCommand) {
        return openplatformOpenapiRecordAppMonthBillQueryCommandExecutor.execute(openplatformOpenapiRecordAppMonthBillQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppMonthBillQueryCommandExecutor(OpenplatformOpenapiRecordAppMonthBillQueryCommandExecutor openplatformOpenapiRecordAppMonthBillQueryCommandExecutor) {
        this.openplatformOpenapiRecordAppMonthBillQueryCommandExecutor = openplatformOpenapiRecordAppMonthBillQueryCommandExecutor;
    }
}
