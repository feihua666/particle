package com.particle.feedback.app.feedback.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.structmapping.FeedbackAttachmentAppStructMapping;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackAttachmentService;
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
 * 意见反馈附件 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Component
@Validated
public class FeedbackAttachmentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFeedbackAttachmentService iFeedbackAttachmentService;

	/**
	 * 执行 意见反馈附件 列表查询指令
	 * @param feedbackAttachmentQueryListCommand
	 * @return
	 */
	public MultiResponse<FeedbackAttachmentVO> execute(@Valid FeedbackAttachmentQueryListCommand feedbackAttachmentQueryListCommand) {
		List<FeedbackAttachmentDO> feedbackAttachmentDO = iFeedbackAttachmentService.list(feedbackAttachmentQueryListCommand);
		List<FeedbackAttachmentVO> feedbackAttachmentVOs = FeedbackAttachmentAppStructMapping.instance.feedbackAttachmentDOsToFeedbackAttachmentVOs(feedbackAttachmentDO);
		return MultiResponse.of(feedbackAttachmentVOs);
	}
	/**
	 * 执行 意见反馈附件 分页查询指令
	 * @param feedbackAttachmentPageQueryCommand
	 * @return
	 */
	public PageResponse<FeedbackAttachmentVO> execute(@Valid FeedbackAttachmentPageQueryCommand feedbackAttachmentPageQueryCommand) {
		Page<FeedbackAttachmentDO> page = iFeedbackAttachmentService.listPage(feedbackAttachmentPageQueryCommand);
		return FeedbackAttachmentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 意见反馈附件 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FeedbackAttachmentVO> executeDetail(IdCommand detailCommand) {
		FeedbackAttachmentDO byId = iFeedbackAttachmentService.getById(detailCommand.getId());
		FeedbackAttachmentVO feedbackAttachmentVO = FeedbackAttachmentAppStructMapping.instance.feedbackAttachmentDOToFeedbackAttachmentVO(byId);
		return SingleResponse.of(feedbackAttachmentVO);
	}

	@Autowired
	public void setIFeedbackAttachmentService(IFeedbackAttachmentService iFeedbackAttachmentService) {
		this.iFeedbackAttachmentService = iFeedbackAttachmentService;
	}
}
