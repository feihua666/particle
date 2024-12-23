package com.particle.feedback.app.feedback.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.structmapping.FeedbackAttachmentAppStructMapping;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.feedback.domain.feedback.FeedbackAttachment;
import com.particle.feedback.domain.feedback.FeedbackAttachmentId;
import com.particle.feedback.domain.feedback.gateway.FeedbackAttachmentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 意见反馈附件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Component
@Validated
public class FeedbackAttachmentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackAttachmentGateway feedbackAttachmentGateway;

	/**
	 * 执行 意见反馈附件 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FeedbackAttachmentVO> execute(@Valid IdCommand deleteCommand) {
		FeedbackAttachmentId feedbackAttachmentId = FeedbackAttachmentId.of(deleteCommand.getId());
		FeedbackAttachment byId = feedbackAttachmentGateway.getById(feedbackAttachmentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = feedbackAttachmentGateway.delete(feedbackAttachmentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FeedbackAttachmentAppStructMapping.instance.toFeedbackAttachmentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackAttachmentGateway
	 */
	@Autowired
	public void setFeedbackAttachmentGateway(FeedbackAttachmentGateway feedbackAttachmentGateway) {
		this.feedbackAttachmentGateway = feedbackAttachmentGateway;
	}
}
