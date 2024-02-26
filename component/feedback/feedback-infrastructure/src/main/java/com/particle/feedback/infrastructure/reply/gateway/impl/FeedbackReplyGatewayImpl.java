package com.particle.feedback.infrastructure.reply.gateway.impl;

import com.particle.feedback.domain.reply.FeedbackReply;
import com.particle.feedback.domain.reply.FeedbackReplyId;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyGateway;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyService;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import com.particle.feedback.infrastructure.reply.structmapping.FeedbackReplyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 意见反馈回复 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Component
public class FeedbackReplyGatewayImpl extends AbstractBaseGatewayImpl<FeedbackReplyId,FeedbackReply> implements FeedbackReplyGateway {

	private IFeedbackReplyService iFeedbackReplyService;

	@Override
	public FeedbackReply getById(FeedbackReplyId feedbackReplyId) {
		FeedbackReplyDO byId = iFeedbackReplyService.getById(feedbackReplyId.getId());
		FeedbackReply feedbackReply = DomainFactory.create(FeedbackReply.class);
		feedbackReply = FeedbackReplyInfrastructureStructMapping.instance. feedbackReplyDOToFeedbackReply(feedbackReply,byId);
		return feedbackReply;
	}

	@Override
	public boolean doSave(FeedbackReply feedbackReply) {
		FeedbackReplyDO feedbackReplyDO = FeedbackReplyInfrastructureStructMapping.instance.feedbackReplyToFeedbackReplyDO(feedbackReply);
		if (feedbackReplyDO.getId() == null) {
			feedbackReplyDO.setAddControl(feedbackReply.getAddControl());
			FeedbackReplyDO add = iFeedbackReplyService.add(feedbackReplyDO);
			feedbackReply.setId(FeedbackReplyId.of(add.getId()));
			return add != null;
		}
		feedbackReplyDO.setUpdateControl(feedbackReply.getUpdateControl());
		FeedbackReplyDO update = iFeedbackReplyService.update(feedbackReplyDO);
		return update != null;
	}

	@Override
	public boolean delete(FeedbackReplyId feedbackReplyId) {
		return iFeedbackReplyService.deleteById(feedbackReplyId.getId());
	}


	@Autowired
	public void setIFeedbackReplyService(IFeedbackReplyService iFeedbackReplyService) {
		this.iFeedbackReplyService = iFeedbackReplyService;
	}
}
