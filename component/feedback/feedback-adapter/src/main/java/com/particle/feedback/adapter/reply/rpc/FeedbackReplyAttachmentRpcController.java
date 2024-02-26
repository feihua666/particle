package com.particle.feedback.adapter.reply.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.feedback.client.reply.api.IFeedbackReplyAttachmentApplicationService;
import com.particle.feedback.adapter.feign.client.reply.rpc.FeedbackReplyAttachmentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈回复附件远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Tag(name = "意见反馈回复附件远程调用相关接口")
@RestController
@RequestMapping("/rpc/feedback_reply_attachment")
public class FeedbackReplyAttachmentRpcController extends AbstractBaseRpcAdapter implements FeedbackReplyAttachmentRpcFeignClient  {

	@Autowired
	private IFeedbackReplyAttachmentApplicationService iFeedbackReplyAttachmentApplicationService;


}