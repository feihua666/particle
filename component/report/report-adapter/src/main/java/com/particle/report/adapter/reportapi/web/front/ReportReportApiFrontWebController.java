package com.particle.report.adapter.reportapi.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/report_report_api")
public class ReportReportApiFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;


}