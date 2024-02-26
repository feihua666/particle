package com.particle.feedback.adapter.feedback.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import com.particle.feedback.client.feedback.dto.command.FeedbackCreateCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUserTool;
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
 * 意见反馈前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Tag(name = "意见反馈pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/feedback")
public class FeedbackFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFeedbackApplicationService iFeedbackApplicationService;

	@Operation(summary = "添加意见反馈")
	@PostMapping("/create")
	@OpLog(name = "添加意见反馈",module = OpLogConstants.Module.feedback,type = OpLogConstants.Type.create)
	public SingleResponse<FeedbackVO> create(@RequestBody FeedbackCreateCommand feedbackCreateCommand){
		Long loginUserId = LoginUserTool.getLoginUserId();
		feedbackCreateCommand.setFeedbackUserId(loginUserId);
		return iFeedbackApplicationService.create(feedbackCreateCommand);
	}
}