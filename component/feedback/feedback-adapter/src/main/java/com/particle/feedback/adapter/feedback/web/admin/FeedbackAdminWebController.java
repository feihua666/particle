package com.particle.feedback.adapter.feedback.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import com.particle.feedback.client.feedback.api.representation.IFeedbackRepresentationApplicationService;
import com.particle.feedback.client.feedback.dto.command.FeedbackCreateCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackManualHandleCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 意见反馈后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Tag(name = "意见反馈pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/feedback")
public class FeedbackAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFeedbackApplicationService iFeedbackApplicationService;
	@Autowired
	private IFeedbackRepresentationApplicationService iFeedbackRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:feedback:create')")
	@Operation(summary = "添加意见反馈")
	@PostMapping("/create")
	@OpLog(name = "添加意见反馈",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.create)
	public SingleResponse<FeedbackVO> create(@RequestBody FeedbackCreateCommand feedbackCreateCommand){
		return iFeedbackApplicationService.create(feedbackCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedback:delete')")
	@Operation(summary = "删除意见反馈")
	@DeleteMapping("/delete")
	@OpLog(name = "删除意见反馈",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.delete)
	public SingleResponse<FeedbackVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_feedback_feedback, DataConstraintContext.Action.delete.name());
		return iFeedbackApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedback:detail')")
	@Operation(summary = "意见反馈详情展示")
	@GetMapping("/detail")
	public SingleResponse<FeedbackVO> queryDetail(IdCommand detailCommand){
		return iFeedbackRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedback:queryList')")
	@Operation(summary = "列表查询意见反馈")
	@GetMapping("/list")
	public MultiResponse<FeedbackVO> queryList(FeedbackQueryListCommand feedbackQueryListCommand){
		feedbackQueryListCommand.dcdo(DataConstraintConstants.data_object_feedback_feedback,DataConstraintContext.Action.query.name());
		return iFeedbackRepresentationApplicationService.queryList(feedbackQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedback:pageQuery')")
	@Operation(summary = "分页查询意见反馈")
	@GetMapping("/page")
	public PageResponse<FeedbackVO> pageQueryList(FeedbackPageQueryCommand feedbackPageQueryCommand){
		feedbackPageQueryCommand.dcdo(DataConstraintConstants.data_object_feedback_feedback,DataConstraintContext.Action.query.name());
		return iFeedbackRepresentationApplicationService.pageQuery(feedbackPageQueryCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedback:manualHandle')")
	@Operation(summary = "手动处理意见反馈")
	@PostMapping("/manualHandle")
	@OpLog(name = "手动处理意见反馈",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.create)
	public SingleResponse<FeedbackVO> manualHandle(@RequestBody FeedbackManualHandleCommand feedbackManualHandleCommand){
		return iFeedbackApplicationService.manualHandle(feedbackManualHandleCommand);
	}

}