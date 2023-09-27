package com.particle.report.adapter.reportapi.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/report_report_api")
public class ReportReportApiFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;


}