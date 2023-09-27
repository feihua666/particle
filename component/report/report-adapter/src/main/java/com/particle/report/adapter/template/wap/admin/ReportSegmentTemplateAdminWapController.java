package com.particle.report.adapter.template.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告片段模板后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Tag(name = "报告片段模板wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/report_segment_template")
public class ReportSegmentTemplateAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IReportSegmentTemplateApplicationService iReportSegmentTemplateApplicationService;


}