package com.particle.report.adapter.reportapi.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.api.IReportApiApplicationService;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-27 11:04:47
 */
@Tag(name = "报告接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/report_report_api/test")
public class ReportReportApiAdminWebTestController extends AbstractBaseWebAdapter {
	@Autowired
	private IReportApiApplicationService iReportApiApplicationService;

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:test')")
	@Operation(summary = "报告接口测试")
	@PostMapping("/api_test")
	public SingleResponse<ReportApiGenerateVO> reportApiTest(@RequestBody ReportApiGenerateCommand reportApiGenerateCommand){
		SingleResponse<ReportApiGenerateVO> r = iReportApiApplicationService.reportApiGenerate(reportApiGenerateCommand);
		return r;
	}
}
