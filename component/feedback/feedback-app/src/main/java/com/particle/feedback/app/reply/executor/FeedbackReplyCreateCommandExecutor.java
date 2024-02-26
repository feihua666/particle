package com.particle.feedback.app.reply.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.feedback.app.reply.structmapping.FeedbackReplyAppStructMapping;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyAttachmentCreateCommand;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.domain.feedback.gateway.FeedbackGateway;
import com.particle.feedback.domain.reply.FeedbackReply;
import com.particle.feedback.domain.reply.gateway.FeedbackReplyGateway;
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
import java.util.List;

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
public class FeedbackReplyCreateCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackReplyGateway feedbackReplyGateway;
	private FeedbackGateway feedbackGateway;
	private FeedbackReplyAttachmentCreateCommandExecutor feedbackReplyAttachmentCreateCommandExecutor;


	/**
	 * 执行意见反馈回复添加指令
	 * @param feedbackReplyCreateCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyVO> execute(@Valid FeedbackReplyCreateCommand feedbackReplyCreateCommand) {
		FeedbackReply feedbackReply = createByFeedbackReplyCreateCommand(feedbackReplyCreateCommand);
		feedbackReply.setAddControl(feedbackReplyCreateCommand);

		feedbackReply.initForAdd();
		boolean save = feedbackReplyGateway.save(feedbackReply);
		if (save) {
			List<FeedbackReplyAttachmentCreateCommand> attachmentCreateCommands = feedbackReplyCreateCommand.getAttachmentCreateCommands();
			if (CollectionUtil.isNotEmpty(attachmentCreateCommands)) {
				for (FeedbackReplyAttachmentCreateCommand attachmentCreateCommand : attachmentCreateCommands) {

					feedbackReplyAttachmentCreateCommandExecutor.execute(attachmentCreateCommand);
				}
			}
			Feedback feedback = feedbackGateway.getById(FeedbackId.of(feedbackReplyCreateCommand.getFeedbackId()));
			if (!feedback.getIsHandle()) {
				feedback.manualHandle("回复");
			}
			feedbackGateway.save(feedback);
			return SingleResponse.of(FeedbackReplyAppStructMapping.instance.toFeedbackReplyVO(feedbackReply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据意见反馈回复创建指令创建意见反馈回复模型
	 * @param feedbackReplyCreateCommand
	 * @return
	 */
	private FeedbackReply createByFeedbackReplyCreateCommand(FeedbackReplyCreateCommand feedbackReplyCreateCommand){
		FeedbackReply feedbackReply = FeedbackReply.create();
		FeedbackReplyCreateCommandToFeedbackReplyMapping.instance.fillFeedbackReplyByFeedbackReplyCreateCommand(feedbackReply, feedbackReplyCreateCommand);
		return feedbackReply;
	}

	@Mapper
	interface  FeedbackReplyCreateCommandToFeedbackReplyMapping{
		FeedbackReplyCreateCommandToFeedbackReplyMapping instance = Mappers.getMapper( FeedbackReplyCreateCommandToFeedbackReplyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param feedbackReply
		 * @param feedbackReplyCreateCommand
		 */
		void fillFeedbackReplyByFeedbackReplyCreateCommand(@MappingTarget FeedbackReply feedbackReply, FeedbackReplyCreateCommand feedbackReplyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackReplyGateway
	 */
	@Autowired
	public void setFeedbackReplyGateway(FeedbackReplyGateway feedbackReplyGateway) {
		this.feedbackReplyGateway = feedbackReplyGateway;
	}
	@Autowired
	public void setFeedbackGateway(FeedbackGateway feedbackGateway) {
		this.feedbackGateway = feedbackGateway;
	}

	@Autowired
	public void setFeedbackReplyAttachmentCreateCommandExecutor(FeedbackReplyAttachmentCreateCommandExecutor feedbackReplyAttachmentCreateCommandExecutor) {
		this.feedbackReplyAttachmentCreateCommandExecutor = feedbackReplyAttachmentCreateCommandExecutor;
	}
}
