package com.particle.report.app.executor;

import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderParam;

/**
 * <p>
 * 报告生成数据注入器
 * 旨在对外提供额外的扩展，可以注入额外的参数
 * </p>
 *
 * @author yangwei
 * @since 2023/9/27 17:17
 */
public interface IReportApiGenerateDataInjector {


    /**
     * 可以对 渲染参数做一些处理或注入一些数据，以供模板使用
     * @param reportSegmentTemplateRenderParam
     * @param reportReportApiDO
     */
    void dataInject(ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam, ReportReportApiDO reportReportApiDO);
}
