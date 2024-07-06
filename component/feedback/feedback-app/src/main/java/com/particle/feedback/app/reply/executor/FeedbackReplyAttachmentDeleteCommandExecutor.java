package com.particle.feedback.app.reply.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.feedback.app.reply.structmapping.FeedbackReplyAttachmentAppStructMapping;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.feedback.domain.reply.FeedbackReplyAttachment;
import com.particle.feedback.domain.reply.FeedbackReplyAttachmentId;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyAttachmentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 意见反馈回复附件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Component
@Validated
public class FeedbackReplyAttachmentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackReplyAttachmentGateway feedbackReplyAttachmentGateway;

	/**
	 * 执行 意见反馈回复附件 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyAttachmentVO> execute(@Valid IdCommand deleteCommand) {
		FeedbackReplyAttachmentId feedbackReplyAttachmentId = FeedbackReplyAttachmentId.of(deleteCommand.getId());
		FeedbackReplyAttachment byId = feedbackReplyAttachmentGateway.getById(feedbackReplyAttachmentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = feedbackReplyAttachmentGateway.delete(feedbackReplyAttachmentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FeedbackReplyAttachmentAppStructMapping.instance.toFeedbackReplyAttachmentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackReplyAttachmentGateway
	 */
	@Autowired
	public void setFeedbackReplyAttachmentGateway(FeedbackReplyAttachmentGateway feedbackReplyAttachmentGateway) {
		this.feedbackReplyAttachmentGateway = feedbackReplyAttachmentGateway;
	}
}
