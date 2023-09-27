package com.particle.report.adapter.template.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告片段模板前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Tag(name = "报告片段模板pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/report_segment_template")
public class ReportSegmentTemplateFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IReportSegmentTemplateApplicationService iReportSegmentTemplateApplicationService;


}