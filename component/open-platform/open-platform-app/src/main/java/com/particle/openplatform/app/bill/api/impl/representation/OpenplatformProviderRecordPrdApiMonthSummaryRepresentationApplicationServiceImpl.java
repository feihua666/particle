package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台供应商接口月汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService {

    private OpenplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> pageQuery(OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor.execute(openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryList(OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand openplatformProviderRecordPrdApiMonthSummaryQueryListCommand) {
        return openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor.execute(openplatformProviderRecordPrdApiMonthSummaryQueryListCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor(OpenplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor) {
        this.openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor = openplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor;
    }
}
