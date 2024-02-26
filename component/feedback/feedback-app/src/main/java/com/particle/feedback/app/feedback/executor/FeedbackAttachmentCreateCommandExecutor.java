package com.particle.feedback.app.feedback.executor;

import com.particle.feedback.app.feedback.structmapping.FeedbackAttachmentAppStructMapping;
import com.particle.feedback.client.feedback.dto.command.FeedbackAttachmentCreateCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.feedback.domain.feedback.FeedbackAttachment;
import com.particle.feedback.domain.feedback.gateway.FeedbackAttachmentGateway;
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
 * 意见反馈附件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Component
@Validated
public class FeedbackAttachmentCreateCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackAttachmentGateway feedbackAttachmentGateway;

	/**
	 * 执行意见反馈附件添加指令
	 * @param feedbackAttachmentCreateCommand
	 * @return
	 */
	public SingleResponse<FeedbackAttachmentVO> execute(@Valid FeedbackAttachmentCreateCommand feedbackAttachmentCreateCommand) {
		FeedbackAttachment feedbackAttachment = createByFeedbackAttachmentCreateCommand(feedbackAttachmentCreateCommand);
		feedbackAttachment.setAddControl(feedbackAttachmentCreateCommand);
		boolean save = feedbackAttachmentGateway.save(feedbackAttachment);
		if (save) {
			return SingleResponse.of(FeedbackAttachmentAppStructMapping.instance.toFeedbackAttachmentVO(feedbackAttachment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据意见反馈附件创建指令创建意见反馈附件模型
	 * @param feedbackAttachmentCreateCommand
	 * @return
	 */
	private FeedbackAttachment createByFeedbackAttachmentCreateCommand(FeedbackAttachmentCreateCommand feedbackAttachmentCreateCommand){
		FeedbackAttachment feedbackAttachment = FeedbackAttachment.create();
		FeedbackAttachmentCreateCommandToFeedbackAttachmentMapping.instance.fillFeedbackAttachmentByFeedbackAttachmentCreateCommand(feedbackAttachment, feedbackAttachmentCreateCommand);
		return feedbackAttachment;
	}

	@Mapper
	interface  FeedbackAttachmentCreateCommandToFeedbackAttachmentMapping{
		FeedbackAttachmentCreateCommandToFeedbackAttachmentMapping instance = Mappers.getMapper( FeedbackAttachmentCreateCommandToFeedbackAttachmentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param feedbackAttachment
		 * @param feedbackAttachmentCreateCommand
		 */
		void fillFeedbackAttachmentByFeedbackAttachmentCreateCommand(@MappingTarget FeedbackAttachment feedbackAttachment, FeedbackAttachmentCreateCommand feedbackAttachmentCreateCommand);
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
