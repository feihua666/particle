package com.particle.feedback.app.feedback.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.structmapping.FeedbackAppStructMapping;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.domain.feedback.gateway.FeedbackGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 意见反馈 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Component
@Validated
public class FeedbackDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackGateway feedbackGateway;

	/**
	 * 执行 意见反馈 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FeedbackVO> execute(@Valid IdCommand deleteCommand) {
		FeedbackId feedbackId = FeedbackId.of(deleteCommand.getId());
		Feedback byId = feedbackGateway.getById(feedbackId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = feedbackGateway.delete(feedbackId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FeedbackAppStructMapping.instance.toFeedbackVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackGateway
	 */
	@Autowired
	public void setFeedbackGateway(FeedbackGateway feedbackGateway) {
		this.feedbackGateway = feedbackGateway;
	}
}
