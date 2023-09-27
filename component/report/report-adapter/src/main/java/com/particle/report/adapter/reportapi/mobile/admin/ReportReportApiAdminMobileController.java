package com.particle.report.adapter.reportapi.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/report_report_api")
public class ReportReportApiAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;


}