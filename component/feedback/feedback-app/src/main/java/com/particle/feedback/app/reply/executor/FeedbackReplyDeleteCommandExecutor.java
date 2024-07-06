package com.particle.feedback.app.reply.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.feedback.app.reply.structmapping.FeedbackReplyAppStructMapping;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.feedback.domain.reply.FeedbackReply;
import com.particle.feedback.domain.reply.FeedbackReplyId;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 意见反馈回复 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Component
@Validated
public class FeedbackReplyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackReplyGateway feedbackReplyGateway;

	/**
	 * 执行 意见反馈回复 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyVO> execute(@Valid IdCommand deleteCommand) {
		FeedbackReplyId feedbackReplyId = FeedbackReplyId.of(deleteCommand.getId());
		FeedbackReply byId = feedbackReplyGateway.getById(feedbackReplyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = feedbackReplyGateway.delete(feedbackReplyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FeedbackReplyAppStructMapping.instance.toFeedbackReplyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackReplyGateway
	 */
	@Autowired
	public void setFeedbackReplyGateway(FeedbackReplyGateway feedbackReplyGateway) {
		this.feedbackReplyGateway = feedbackReplyGateway;
	}
}
