package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> pageQuery(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand) {
        return openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor = openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor;
    }
}
