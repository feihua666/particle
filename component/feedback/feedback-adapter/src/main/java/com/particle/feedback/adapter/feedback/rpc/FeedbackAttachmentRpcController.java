package com.particle.feedback.adapter.feedback.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.feedback.adapter.feign.client.feedback.rpc.FeedbackAttachmentRpcFeignClient;
import com.particle.feedback.client.feedback.api.IFeedbackAttachmentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈附件远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Tag(name = "意见反馈附件远程调用相关接口")
@RestController
@RequestMapping("/rpc/feedback_attachment")
public class FeedbackAttachmentRpcController extends AbstractBaseRpcAdapter implements FeedbackAttachmentRpcFeignClient  {

	@Autowired
	private IFeedbackAttachmentApplicationService iFeedbackAttachmentApplicationService;


}
