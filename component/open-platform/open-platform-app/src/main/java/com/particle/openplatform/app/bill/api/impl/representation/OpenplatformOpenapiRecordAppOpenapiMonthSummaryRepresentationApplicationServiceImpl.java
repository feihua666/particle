package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用开放接口月汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> pageQuery(OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand) {
        return openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor;
    }
}
