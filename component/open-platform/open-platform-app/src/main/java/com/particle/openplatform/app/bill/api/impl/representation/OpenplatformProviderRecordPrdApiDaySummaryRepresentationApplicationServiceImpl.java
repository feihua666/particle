package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台供应商接口日汇总 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService {

    private OpenplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> pageQuery(OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand openplatformProviderRecordPrdApiDaySummaryPageQueryCommand) {
        return openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor.execute(openplatformProviderRecordPrdApiDaySummaryPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryList(OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand openplatformProviderRecordPrdApiDaySummaryQueryListCommand) {
        return openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor.execute(openplatformProviderRecordPrdApiDaySummaryQueryListCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor(OpenplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor) {
        this.openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor = openplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor;
    }
}
