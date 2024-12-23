package com.particle.report.adapter.template.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import com.particle.report.client.template.api.representation.IReportSegmentTemplateRepresentationApplicationService;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCopyCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCreateCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateUpdateCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplatePageQueryCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplateQueryListCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 报告片段模板后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Tag(name = "报告片段模板pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/report_segment_template")
public class ReportSegmentTemplateAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IReportSegmentTemplateApplicationService iReportSegmentTemplateApplicationService;
	@Autowired
	private IReportSegmentTemplateRepresentationApplicationService iReportSegmentTemplateRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:create')")
	@Operation(summary = "添加报告片段模板")
	@PostMapping("/create")
	@OpLog(name = "添加报告片段模板",module = OpLogConstants.Module.report,type = OpLogConstants.Type.create)
	public SingleResponse<ReportSegmentTemplateVO> create(@RequestBody ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand){
		return iReportSegmentTemplateApplicationService.create(reportSegmentTemplateCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:delete')")
	@Operation(summary = "删除报告片段模板")
	@DeleteMapping("/delete")
	@OpLog(name = "删除报告片段模板",module = OpLogConstants.Module.report,type = OpLogConstants.Type.delete)
	public SingleResponse<ReportSegmentTemplateVO> delete(@RequestBody IdCommand deleteCommand){
		return iReportSegmentTemplateApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:update')")
	@Operation(summary = "更新报告片段模板")
	@PutMapping("/update")
	@OpLog(name = "更新报告片段模板",module = OpLogConstants.Module.report,type = OpLogConstants.Type.update)
	public SingleResponse<ReportSegmentTemplateVO> update(@RequestBody ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand){
		return iReportSegmentTemplateApplicationService.update(reportSegmentTemplateUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:update')")
	@Operation(summary = "报告片段模板更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<ReportSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iReportSegmentTemplateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:detail')")
	@Operation(summary = "报告片段模板详情展示")
	@GetMapping("/detail")
	public SingleResponse<ReportSegmentTemplateVO> queryDetail(IdCommand detailCommand){
		return iReportSegmentTemplateRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:queryList')")
	@Operation(summary = "列表查询报告片段模板")
	@GetMapping("/list")
	public MultiResponse<ReportSegmentTemplateVO> queryList(ReportSegmentTemplateQueryListCommand reportSegmentTemplateQueryListCommand){
		return iReportSegmentTemplateRepresentationApplicationService.queryList(reportSegmentTemplateQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:pageQuery')")
	@Operation(summary = "分页查询报告片段模板")
	@GetMapping("/page")
	public PageResponse<ReportSegmentTemplateVO> pageQueryList(ReportSegmentTemplatePageQueryCommand reportSegmentTemplatePageQueryCommand){
		return iReportSegmentTemplateRepresentationApplicationService.pageQuery(reportSegmentTemplatePageQueryCommand);
	}
	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:copy')")
	@Operation(summary = "片段模板复制")
	@PostMapping("/copy")
	@OpLog(name = "片段模板复制",module = OpLogConstants.Module.report,type = OpLogConstants.Type.create)
	public SingleResponse<ReportSegmentTemplateVO> copy(@RequestBody ReportSegmentTemplateCopyCommand reportSegmentTemplateCopyCommand){
		return iReportSegmentTemplateApplicationService.copy(reportSegmentTemplateCopyCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:reportSegmentTemplate:refreshCache')")
	@Operation(summary = "刷新报告片段模板缓存")
	@PutMapping("/refreshCache")
	@OpLog(name = "刷新报告片段模板缓存",module = OpLogConstants.Module.report,type = OpLogConstants.Type.update)
	public SingleResponse<String> refreshCache(@RequestBody IdCommand deleteCommand){
		return iReportSegmentTemplateApplicationService.refreshCache(deleteCommand);
	}
}
