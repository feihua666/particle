package com.particle.feedback.adapter.reply.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.feedback.adapter.feign.client.reply.rpc.FeedbackReplyRpcFeignClient;
import com.particle.feedback.client.reply.api.IFeedbackReplyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈回复远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Tag(name = "意见反馈回复远程调用相关接口")
@RestController
@RequestMapping("/rpc/feedback_reply")
public class FeedbackReplyRpcController extends AbstractBaseRpcAdapter implements FeedbackReplyRpcFeignClient  {

	@Autowired
	private IFeedbackReplyApplicationService iFeedbackReplyApplicationService;


}
