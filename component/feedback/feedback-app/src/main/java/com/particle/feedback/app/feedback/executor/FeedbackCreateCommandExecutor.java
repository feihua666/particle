package com.particle.feedback.app.feedback.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.feedback.app.feedback.structmapping.FeedbackAppStructMapping;
import com.particle.feedback.client.feedback.dto.command.FeedbackAttachmentCreateCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackCreateCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackManualHandleCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.domain.feedback.gateway.FeedbackGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
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
 * 意见反馈 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Component
@Validated
public class FeedbackCreateCommandExecutor  extends AbstractBaseExecutor {

	private FeedbackGateway feedbackGateway;

	private FeedbackAttachmentCreateCommandExecutor feedbackAttachmentCreateCommandExecutor;

	/**
	 * 执行意见反馈添加指令
	 * @param feedbackCreateCommand
	 * @return
	 */
	public SingleResponse<FeedbackVO> execute(@Valid FeedbackCreateCommand feedbackCreateCommand) {
		Feedback feedback = createByFeedbackCreateCommand(feedbackCreateCommand);
		feedback.setAddControl(feedbackCreateCommand);

		feedback.initForAdd();
		boolean save = feedbackGateway.save(feedback);
		if (save) {
			// 添加附件信息
			List<FeedbackAttachmentCreateCommand> attachmentCreateCommands = feedbackCreateCommand.getAttachmentCreateCommands();
			if (CollectionUtil.isNotEmpty(attachmentCreateCommands)) {
				for (FeedbackAttachmentCreateCommand attachmentCreateCommand : attachmentCreateCommands) {
					attachmentCreateCommand.setFeedbackId(feedback.getId().getId());
					feedbackAttachmentCreateCommandExecutor.execute(attachmentCreateCommand);
				}
			}

			return SingleResponse.of(FeedbackAppStructMapping.instance.toFeedbackVO(feedback));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}


	/**
	 * 手动处理
	 * @param feedbackManualHandleCommand
	 * @return
	 */
	public SingleResponse<FeedbackVO> manualHandle(FeedbackManualHandleCommand feedbackManualHandleCommand) {
		Feedback feedback = feedbackGateway.getById(FeedbackId.of(feedbackManualHandleCommand.getId()));

		Assert.isFalse(feedback.getIsHandle(),"当前数据已处理，请勿重复处理");

		feedback.manualHandle(feedbackManualHandleCommand.getHandleResult());
		boolean save = feedbackGateway.save(feedback);

		if (save) {
			return SingleResponse.of(FeedbackAppStructMapping.instance.toFeedbackVO(feedback));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据意见反馈创建指令创建意见反馈模型
	 * @param feedbackCreateCommand
	 * @return
	 */
	private Feedback createByFeedbackCreateCommand(FeedbackCreateCommand feedbackCreateCommand){
		Feedback feedback = Feedback.create();
		FeedbackCreateCommandToFeedbackMapping.instance.fillFeedbackByFeedbackCreateCommand(feedback, feedbackCreateCommand);
		return feedback;
	}

	@Mapper
	interface  FeedbackCreateCommandToFeedbackMapping{
		FeedbackCreateCommandToFeedbackMapping instance = Mappers.getMapper( FeedbackCreateCommandToFeedbackMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param feedback
		 * @param feedbackCreateCommand
		 */
		void fillFeedbackByFeedbackCreateCommand(@MappingTarget Feedback feedback, FeedbackCreateCommand feedbackCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param feedbackGateway
	 */
	@Autowired
	public void setFeedbackGateway(FeedbackGateway feedbackGateway) {
		this.feedbackGateway = feedbackGateway;
	}

	@Autowired
	public void setFeedbackAttachmentCreateCommandExecutor(FeedbackAttachmentCreateCommandExecutor feedbackAttachmentCreateCommandExecutor) {
		this.feedbackAttachmentCreateCommandExecutor = feedbackAttachmentCreateCommandExecutor;
	}
}
