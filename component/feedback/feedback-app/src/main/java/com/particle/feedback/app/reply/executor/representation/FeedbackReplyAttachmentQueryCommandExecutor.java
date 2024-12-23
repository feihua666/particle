package com.particle.feedback.app.reply.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.reply.structmapping.FeedbackReplyAttachmentAppStructMapping;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyAttachmentService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 意见反馈回复附件 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Component
@Validated
public class FeedbackReplyAttachmentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFeedbackReplyAttachmentService iFeedbackReplyAttachmentService;

	/**
	 * 执行 意见反馈回复附件 列表查询指令
	 * @param feedbackReplyAttachmentQueryListCommand
	 * @return
	 */
	public MultiResponse<FeedbackReplyAttachmentVO> execute(@Valid FeedbackReplyAttachmentQueryListCommand feedbackReplyAttachmentQueryListCommand) {
		List<FeedbackReplyAttachmentDO> feedbackReplyAttachmentDO = iFeedbackReplyAttachmentService.list(feedbackReplyAttachmentQueryListCommand);
		List<FeedbackReplyAttachmentVO> feedbackReplyAttachmentVOs = FeedbackReplyAttachmentAppStructMapping.instance.feedbackReplyAttachmentDOsToFeedbackReplyAttachmentVOs(feedbackReplyAttachmentDO);
		return MultiResponse.of(feedbackReplyAttachmentVOs);
	}
	/**
	 * 执行 意见反馈回复附件 分页查询指令
	 * @param feedbackReplyAttachmentPageQueryCommand
	 * @return
	 */
	public PageResponse<FeedbackReplyAttachmentVO> execute(@Valid FeedbackReplyAttachmentPageQueryCommand feedbackReplyAttachmentPageQueryCommand) {
		Page<FeedbackReplyAttachmentDO> page = iFeedbackReplyAttachmentService.listPage(feedbackReplyAttachmentPageQueryCommand);
		return FeedbackReplyAttachmentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 意见反馈回复附件 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyAttachmentVO> executeDetail(IdCommand detailCommand) {
		FeedbackReplyAttachmentDO byId = iFeedbackReplyAttachmentService.getById(detailCommand.getId());
		FeedbackReplyAttachmentVO feedbackReplyAttachmentVO = FeedbackReplyAttachmentAppStructMapping.instance.feedbackReplyAttachmentDOToFeedbackReplyAttachmentVO(byId);
		return SingleResponse.of(feedbackReplyAttachmentVO);
	}
	/**
	 * 执行 意见反馈回复附件 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyAttachmentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		FeedbackReplyAttachmentDO byId = iFeedbackReplyAttachmentService.getById(detailForUpdateCommand.getId());
		FeedbackReplyAttachmentVO feedbackReplyAttachmentVO = FeedbackReplyAttachmentAppStructMapping.instance.feedbackReplyAttachmentDOToFeedbackReplyAttachmentVO(byId);
		return SingleResponse.of(feedbackReplyAttachmentVO);
	}

	@Autowired
	public void setIFeedbackReplyAttachmentService(IFeedbackReplyAttachmentService iFeedbackReplyAttachmentService) {
		this.iFeedbackReplyAttachmentService = iFeedbackReplyAttachmentService;
	}
}
