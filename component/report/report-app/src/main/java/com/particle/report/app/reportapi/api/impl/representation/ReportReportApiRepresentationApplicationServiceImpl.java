package com.particle.report.app.reportapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.reportapi.executor.representation.ReportReportApiQueryCommandExecutor;
import com.particle.report.client.reportapi.api.representation.IReportReportApiRepresentationApplicationService;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiPageQueryCommand;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiQueryListCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 报告接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Service
@CatchAndLog
public class ReportReportApiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IReportReportApiRepresentationApplicationService {

    private ReportReportApiQueryCommandExecutor reportReportApiQueryCommandExecutor;

    @Override
    public SingleResponse<ReportReportApiVO> queryDetail(IdCommand detailCommand) {
        return reportReportApiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<ReportReportApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return reportReportApiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<ReportReportApiVO> pageQuery(ReportReportApiPageQueryCommand reportReportApiPageQueryCommand) {
        return reportReportApiQueryCommandExecutor.execute(reportReportApiPageQueryCommand);
    }

    @Override
    public MultiResponse<ReportReportApiVO> queryList(ReportReportApiQueryListCommand reportReportApiQueryListCommand) {
        return reportReportApiQueryCommandExecutor.execute(reportReportApiQueryListCommand);
    }

    @Autowired
    public void setReportReportApiQueryCommandExecutor(ReportReportApiQueryCommandExecutor reportReportApiQueryCommandExecutor) {
        this.reportReportApiQueryCommandExecutor = reportReportApiQueryCommandExecutor;
    }
}
