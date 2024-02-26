package com.particle.feedback.adapter.feign.client.reply.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 意见反馈回复远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@FeignClient(name = "${particle.feign-client.name.feedback:feedback}",path = "/rpc/feedback_reply")
public interface FeedbackReplyRpcFeignClient {









}
