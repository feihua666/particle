package com.particle.report.app.template.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.template.executor.representation.ReportSegmentTemplateQueryCommandExecutor;
import com.particle.report.client.template.api.representation.IReportSegmentTemplateRepresentationApplicationService;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplatePageQueryCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplateQueryListCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 报告片段模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Service
@CatchAndLog
public class ReportSegmentTemplateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IReportSegmentTemplateRepresentationApplicationService {

    private ReportSegmentTemplateQueryCommandExecutor reportSegmentTemplateQueryCommandExecutor;

    @Override
    public SingleResponse<ReportSegmentTemplateVO> queryDetail(IdCommand detailCommand) {
        return reportSegmentTemplateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<ReportSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return reportSegmentTemplateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<ReportSegmentTemplateVO> pageQuery(ReportSegmentTemplatePageQueryCommand reportSegmentTemplatePageQueryCommand) {
        return reportSegmentTemplateQueryCommandExecutor.execute(reportSegmentTemplatePageQueryCommand);
    }

    @Override
    public MultiResponse<ReportSegmentTemplateVO> queryList(ReportSegmentTemplateQueryListCommand reportSegmentTemplateQueryListCommand) {
        return reportSegmentTemplateQueryCommandExecutor.execute(reportSegmentTemplateQueryListCommand);
    }

    @Autowired
    public void setReportSegmentTemplateQueryCommandExecutor(ReportSegmentTemplateQueryCommandExecutor reportSegmentTemplateQueryCommandExecutor) {
        this.reportSegmentTemplateQueryCommandExecutor = reportSegmentTemplateQueryCommandExecutor;
    }
}
