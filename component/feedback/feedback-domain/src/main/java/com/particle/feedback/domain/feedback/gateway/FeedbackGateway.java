package com.particle.feedback.domain.feedback.gateway;

import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 意见反馈 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
public interface FeedbackGateway extends IBaseGateway<FeedbackId,Feedback> {
}
