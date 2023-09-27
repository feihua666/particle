package com.particle.report.infrastructure.template.service;

import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderParam;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderResult;

/**
 * <p>
 * 代码片段模板渲染服务
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 15:08
 */
public interface IReportSegmentTemplateRenderService {


	/**
	 * 渲染模板片段
	 * @param reportSegmentTemplateRenderParam
	 * @return
	 */
	ReportSegmentTemplateRenderResult render(ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam);
}
