package com.particle.feedback.adapter.feign.client.feedback.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 意见反馈远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@FeignClient(name = "${particle.feign-client.feedback.name:feedback-start}", contextId = "feedbackRpcFeignClient", url = "${particle.feign-client.feedback.url:}", path = "/rpc/feedback")
public interface FeedbackRpcFeignClient {









}
