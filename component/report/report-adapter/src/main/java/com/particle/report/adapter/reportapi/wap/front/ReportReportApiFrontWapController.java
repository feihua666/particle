package com.particle.report.adapter.reportapi.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/report_report_api")
public class ReportReportApiFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;


}