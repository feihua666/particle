package com.particle.feedback.app.reply.executor;

import com.particle.feedback.app.reply.structmapping.FeedbackReplyAttachmentAppStructMapping;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyAttachmentCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.feedback.domain.reply.FeedbackReplyAttachment;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyAttachmentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
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
public class FeedbackReplyAttachmentCreateCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackReplyAttachmentGateway feedbackReplyAttachmentGateway;

	/**
	 * 执行意见反馈回复附件添加指令
	 * @param feedbackReplyAttachmentCreateCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyAttachmentVO> execute(@Valid FeedbackReplyAttachmentCreateCommand feedbackReplyAttachmentCreateCommand) {
		FeedbackReplyAttachment feedbackReplyAttachment = createByFeedbackReplyAttachmentCreateCommand(feedbackReplyAttachmentCreateCommand);
		feedbackReplyAttachment.setAddControl(feedbackReplyAttachmentCreateCommand);
		boolean save = feedbackReplyAttachmentGateway.save(feedbackReplyAttachment);
		if (save) {
			return SingleResponse.of(FeedbackReplyAttachmentAppStructMapping.instance.toFeedbackReplyAttachmentVO(feedbackReplyAttachment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据意见反馈回复附件创建指令创建意见反馈回复附件模型
	 * @param feedbackReplyAttachmentCreateCommand
	 * @return
	 */
	private FeedbackReplyAttachment createByFeedbackReplyAttachmentCreateCommand(FeedbackReplyAttachmentCreateCommand feedbackReplyAttachmentCreateCommand){
		FeedbackReplyAttachment feedbackReplyAttachment = FeedbackReplyAttachment.create();
		FeedbackReplyAttachmentCreateCommandToFeedbackReplyAttachmentMapping.instance.fillFeedbackReplyAttachmentByFeedbackReplyAttachmentCreateCommand(feedbackReplyAttachment, feedbackReplyAttachmentCreateCommand);
		return feedbackReplyAttachment;
	}

	@Mapper
	interface  FeedbackReplyAttachmentCreateCommandToFeedbackReplyAttachmentMapping{
		FeedbackReplyAttachmentCreateCommandToFeedbackReplyAttachmentMapping instance = Mappers.getMapper( FeedbackReplyAttachmentCreateCommandToFeedbackReplyAttachmentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param feedbackReplyAttachment
		 * @param feedbackReplyAttachmentCreateCommand
		 */
		void fillFeedbackReplyAttachmentByFeedbackReplyAttachmentCreateCommand(@MappingTarget FeedbackReplyAttachment feedbackReplyAttachment, FeedbackReplyAttachmentCreateCommand feedbackReplyAttachmentCreateCommand);
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
