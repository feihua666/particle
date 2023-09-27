package com.particle.report.adapter.reportapi.web.admin;

import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import com.particle.report.client.reportapi.api.representation.IReportReportApiRepresentationApplicationService;
import com.particle.report.client.reportapi.dto.command.ReportReportApiCreateCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.report.client.reportapi.dto.command.ReportReportApiUpdateCommand;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiPageQueryCommand;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 报告接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/report_report_api")
public class ReportReportApiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;
	@Autowired
	private IReportReportApiRepresentationApplicationService iReportReportApiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:create')")
	@Operation(summary = "添加报告接口")
	@PostMapping("/create")
	@OpLog(name = "添加报告接口",module = OpLogConstants.Module.report,type = OpLogConstants.Type.create)
	public SingleResponse<ReportReportApiVO> create(@RequestBody ReportReportApiCreateCommand reportReportApiCreateCommand){
		return iReportReportApiApplicationService.create(reportReportApiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:delete')")
	@Operation(summary = "删除报告接口")
	@DeleteMapping("/delete")
	@OpLog(name = "删除报告接口",module = OpLogConstants.Module.report,type = OpLogConstants.Type.delete)
	public SingleResponse<ReportReportApiVO> delete(@RequestBody IdCommand deleteCommand){
		return iReportReportApiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:update')")
	@Operation(summary = "更新报告接口")
	@PutMapping("/update")
	@OpLog(name = "更新报告接口",module = OpLogConstants.Module.report,type = OpLogConstants.Type.update)
	public SingleResponse<ReportReportApiVO> update(@RequestBody ReportReportApiUpdateCommand reportReportApiUpdateCommand){
		return iReportReportApiApplicationService.update(reportReportApiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:update')")
	@Operation(summary = "报告接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<ReportReportApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iReportReportApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:detail')")
	@Operation(summary = "报告接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<ReportReportApiVO> queryDetail(IdCommand detailCommand){
		return iReportReportApiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:queryList')")
	@Operation(summary = "列表查询报告接口")
	@GetMapping("/list")
	public MultiResponse<ReportReportApiVO> queryList(ReportReportApiQueryListCommand reportReportApiQueryListCommand){
		return iReportReportApiRepresentationApplicationService.queryList(reportReportApiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportReportApi:pageQuery')")
	@Operation(summary = "分页查询报告接口")
	@GetMapping("/page")
	public PageResponse<ReportReportApiVO> pageQueryList(ReportReportApiPageQueryCommand reportReportApiPageQueryCommand){
		return iReportReportApiRepresentationApplicationService.pageQuery(reportReportApiPageQueryCommand);
	}

}