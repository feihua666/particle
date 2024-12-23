package com.particle.feedback.adapter.reply.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.feedback.client.reply.api.IFeedbackReplyAttachmentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈回复附件后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Tag(name = "意见反馈回复附件移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/feedback_reply_attachment")
public class FeedbackReplyAttachmentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFeedbackReplyAttachmentApplicationService iFeedbackReplyAttachmentApplicationService;


}
