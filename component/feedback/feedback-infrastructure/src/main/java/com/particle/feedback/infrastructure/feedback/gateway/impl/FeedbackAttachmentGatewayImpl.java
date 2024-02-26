package com.particle.feedback.infrastructure.feedback.gateway.impl;

import com.particle.feedback.domain.feedback.FeedbackAttachment;
import com.particle.feedback.domain.feedback.FeedbackAttachmentId;
import com.particle.feedback.domain.feedback.gateway.FeedbackAttachmentGateway;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackAttachmentService;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.feedback.infrastructure.feedback.structmapping.FeedbackAttachmentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 意见反馈附件 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Component
public class FeedbackAttachmentGatewayImpl extends AbstractBaseGatewayImpl<FeedbackAttachmentId,FeedbackAttachment> implements FeedbackAttachmentGateway {

	private IFeedbackAttachmentService iFeedbackAttachmentService;

	@Override
	public FeedbackAttachment getById(FeedbackAttachmentId feedbackAttachmentId) {
		FeedbackAttachmentDO byId = iFeedbackAttachmentService.getById(feedbackAttachmentId.getId());
		FeedbackAttachment feedbackAttachment = DomainFactory.create(FeedbackAttachment.class);
		feedbackAttachment = FeedbackAttachmentInfrastructureStructMapping.instance. feedbackAttachmentDOToFeedbackAttachment(feedbackAttachment,byId);
		return feedbackAttachment;
	}

	@Override
	public boolean doSave(FeedbackAttachment feedbackAttachment) {
		FeedbackAttachmentDO feedbackAttachmentDO = FeedbackAttachmentInfrastructureStructMapping.instance.feedbackAttachmentToFeedbackAttachmentDO(feedbackAttachment);
		if (feedbackAttachmentDO.getId() == null) {
			feedbackAttachmentDO.setAddControl(feedbackAttachment.getAddControl());
			FeedbackAttachmentDO add = iFeedbackAttachmentService.add(feedbackAttachmentDO);
			feedbackAttachment.setId(FeedbackAttachmentId.of(add.getId()));
			return add != null;
		}
		feedbackAttachmentDO.setUpdateControl(feedbackAttachment.getUpdateControl());
		FeedbackAttachmentDO update = iFeedbackAttachmentService.update(feedbackAttachmentDO);
		return update != null;
	}

	@Override
	public boolean delete(FeedbackAttachmentId feedbackAttachmentId) {
		return iFeedbackAttachmentService.deleteById(feedbackAttachmentId.getId());
	}


	@Autowired
	public void setIFeedbackAttachmentService(IFeedbackAttachmentService iFeedbackAttachmentService) {
		this.iFeedbackAttachmentService = iFeedbackAttachmentService;
	}
}
