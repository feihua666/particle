package com.particle.feedback.adapter.feign.client.feedback.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 意见反馈附件远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@FeignClient(name = "${particle.feign-client.name.feedback:feedback}",path = "/rpc/feedback_attachment")
public interface FeedbackAttachmentRpcFeignClient {









}
