package com.particle.feedback.adapter.feedback.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.feedback.adapter.feign.client.feedback.rpc.FeedbackRpcFeignClient;
import com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Tag(name = "意见反馈远程调用相关接口")
@RestController
@RequestMapping("/rpc/feedback")
public class FeedbackRpcController extends AbstractBaseRpcAdapter implements FeedbackRpcFeignClient  {

	@Autowired
	private IFeedbackApplicationService iFeedbackApplicationService;


}
