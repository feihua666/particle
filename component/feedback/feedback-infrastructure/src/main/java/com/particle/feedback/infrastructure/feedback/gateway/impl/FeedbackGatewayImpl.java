package com.particle.feedback.infrastructure.feedback.gateway.impl;

import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.domain.feedback.gateway.FeedbackGateway;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackService;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.feedback.infrastructure.feedback.structmapping.FeedbackInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 意见反馈 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Component
public class FeedbackGatewayImpl extends AbstractBaseGatewayImpl<FeedbackId,Feedback> implements FeedbackGateway {

	private IFeedbackService iFeedbackService;

	@Override
	public Feedback getById(FeedbackId feedbackId) {
		FeedbackDO byId = iFeedbackService.getById(feedbackId.getId());
		Feedback feedback = DomainFactory.create(Feedback.class);
		feedback = FeedbackInfrastructureStructMapping.instance. feedbackDOToFeedback(feedback,byId);
		return feedback;
	}

	@Override
	public boolean doSave(Feedback feedback) {
		FeedbackDO feedbackDO = FeedbackInfrastructureStructMapping.instance.feedbackToFeedbackDO(feedback);
		if (feedbackDO.getId() == null) {
			feedbackDO.setAddControl(feedback.getAddControl());
			FeedbackDO add = iFeedbackService.add(feedbackDO);
			feedback.setId(FeedbackId.of(add.getId()));
			return add != null;
		}
		feedbackDO.setUpdateControl(feedback.getUpdateControl());
		FeedbackDO update = iFeedbackService.update(feedbackDO);
		return update != null;
	}

	@Override
	public boolean delete(FeedbackId feedbackId) {
		return iFeedbackService.deleteById(feedbackId.getId());
	}

	@Override
	public boolean delete(FeedbackId id, IdCommand idCommand) {
		return iFeedbackService.deleteById(idCommand);
	}

	@Autowired
	public void setIFeedbackService(IFeedbackService iFeedbackService) {
		this.iFeedbackService = iFeedbackService;
	}
}
