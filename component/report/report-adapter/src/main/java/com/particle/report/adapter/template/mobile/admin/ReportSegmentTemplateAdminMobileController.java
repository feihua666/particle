package com.particle.report.adapter.template.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告片段模板后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Tag(name = "报告片段模板移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/report_segment_template")
public class ReportSegmentTemplateAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IReportSegmentTemplateApplicationService iReportSegmentTemplateApplicationService;


}