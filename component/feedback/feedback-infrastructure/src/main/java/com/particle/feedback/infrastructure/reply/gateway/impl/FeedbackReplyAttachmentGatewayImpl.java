package com.particle.feedback.infrastructure.reply.gateway.impl;

import com.particle.feedback.domain.reply.FeedbackReplyAttachment;
import com.particle.feedback.domain.reply.FeedbackReplyAttachmentId;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyAttachmentGateway;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyAttachmentService;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.feedback.infrastructure.reply.structmapping.FeedbackReplyAttachmentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 意见反馈回复附件 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Component
public class FeedbackReplyAttachmentGatewayImpl extends AbstractBaseGatewayImpl<FeedbackReplyAttachmentId,FeedbackReplyAttachment> implements FeedbackReplyAttachmentGateway {

	private IFeedbackReplyAttachmentService iFeedbackReplyAttachmentService;

	@Override
	public FeedbackReplyAttachment getById(FeedbackReplyAttachmentId feedbackReplyAttachmentId) {
		FeedbackReplyAttachmentDO byId = iFeedbackReplyAttachmentService.getById(feedbackReplyAttachmentId.getId());
		FeedbackReplyAttachment feedbackReplyAttachment = DomainFactory.create(FeedbackReplyAttachment.class);
		feedbackReplyAttachment = FeedbackReplyAttachmentInfrastructureStructMapping.instance. feedbackReplyAttachmentDOToFeedbackReplyAttachment(feedbackReplyAttachment,byId);
		return feedbackReplyAttachment;
	}

	@Override
	public boolean doSave(FeedbackReplyAttachment feedbackReplyAttachment) {
		FeedbackReplyAttachmentDO feedbackReplyAttachmentDO = FeedbackReplyAttachmentInfrastructureStructMapping.instance.feedbackReplyAttachmentToFeedbackReplyAttachmentDO(feedbackReplyAttachment);
		if (feedbackReplyAttachmentDO.getId() == null) {
			feedbackReplyAttachmentDO.setAddControl(feedbackReplyAttachment.getAddControl());
			FeedbackReplyAttachmentDO add = iFeedbackReplyAttachmentService.add(feedbackReplyAttachmentDO);
			feedbackReplyAttachment.setId(FeedbackReplyAttachmentId.of(add.getId()));
			return add != null;
		}
		feedbackReplyAttachmentDO.setUpdateControl(feedbackReplyAttachment.getUpdateControl());
		FeedbackReplyAttachmentDO update = iFeedbackReplyAttachmentService.update(feedbackReplyAttachmentDO);
		return update != null;
	}

	@Override
	public boolean delete(FeedbackReplyAttachmentId feedbackReplyAttachmentId) {
		return iFeedbackReplyAttachmentService.deleteById(feedbackReplyAttachmentId.getId());
	}

	@Override
	public boolean delete(FeedbackReplyAttachmentId id, IdCommand idCommand) {
		return iFeedbackReplyAttachmentService.deleteById(idCommand);
	}

	@Autowired
	public void setIFeedbackReplyAttachmentService(IFeedbackReplyAttachmentService iFeedbackReplyAttachmentService) {
		this.iFeedbackReplyAttachmentService = iFeedbackReplyAttachmentService;
	}
}
