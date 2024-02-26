package com.particle.feedback.adapter.reply.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.feedback.client.reply.api.IFeedbackReplyApplicationService;
import com.particle.feedback.client.reply.api.representation.IFeedbackReplyRepresentationApplicationService;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyCreateCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 意见反馈回复后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Tag(name = "意见反馈回复pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/feedback_reply")
public class FeedbackReplyAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFeedbackReplyApplicationService iFeedbackReplyApplicationService;
	@Autowired
	private IFeedbackReplyRepresentationApplicationService iFeedbackReplyRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:feedbackReply:create')")
	@Operation(summary = "添加意见反馈回复")
	@PostMapping("/create")
	@OpLog(name = "添加意见反馈回复",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.create)
	public SingleResponse<FeedbackReplyVO> create(@RequestBody FeedbackReplyCreateCommand feedbackReplyCreateCommand, LoginUser loginUser){
		feedbackReplyCreateCommand.setReplyUserId(loginUser.getId());
		return iFeedbackReplyApplicationService.create(feedbackReplyCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedbackReply:delete')")
	@Operation(summary = "删除意见反馈回复")
	@DeleteMapping("/delete")
	@OpLog(name = "删除意见反馈回复",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.delete)
	public SingleResponse<FeedbackReplyVO> delete(@RequestBody IdCommand deleteCommand){
		return iFeedbackReplyApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedbackReply:detail')")
	@Operation(summary = "意见反馈回复详情展示")
	@GetMapping("/detail")
	public SingleResponse<FeedbackReplyVO> queryDetail(IdCommand detailCommand){
		return iFeedbackReplyRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedbackReply:queryList')")
	@Operation(summary = "列表查询意见反馈回复")
	@GetMapping("/list")
	public MultiResponse<FeedbackReplyVO> queryList(FeedbackReplyQueryListCommand feedbackReplyQueryListCommand){
		return iFeedbackReplyRepresentationApplicationService.queryList(feedbackReplyQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:feedbackReply:pageQuery')")
	@Operation(summary = "分页查询意见反馈回复")
	@GetMapping("/page")
	public PageResponse<FeedbackReplyVO> pageQueryList(FeedbackReplyPageQueryCommand feedbackReplyPageQueryCommand){
		return iFeedbackReplyRepresentationApplicationService.pageQuery(feedbackReplyPageQueryCommand);
	}

}