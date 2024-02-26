package com.particle.feedback.adapter.reply.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.feedback.client.reply.api.IFeedbackReplyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈回复后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Tag(name = "意见反馈回复wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/feedback_reply")
public class FeedbackReplyAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFeedbackReplyApplicationService iFeedbackReplyApplicationService;


}