package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用开放接口日汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService {

    private OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> pageQuery(OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand) {
        return openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor.execute(openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor) {
        this.openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor;
    }
}
