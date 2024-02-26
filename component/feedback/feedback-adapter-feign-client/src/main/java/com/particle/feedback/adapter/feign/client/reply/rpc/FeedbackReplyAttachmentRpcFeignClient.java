package com.particle.feedback.adapter.feign.client.reply.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 意见反馈回复附件远程调用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@FeignClient(name = "${particle.feign-client.name.feedback:feedback}",path = "/rpc/feedback_reply_attachment")
public interface FeedbackReplyAttachmentRpcFeignClient {









}
