package com.particle.feedback.app.reply.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.reply.structmapping.FeedbackReplyAppStructMapping;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyService;
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
 * 意见反馈回复 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Component
@Validated
public class FeedbackReplyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFeedbackReplyService iFeedbackReplyService;

	/**
	 * 执行 意见反馈回复 列表查询指令
	 * @param feedbackReplyQueryListCommand
	 * @return
	 */
	public MultiResponse<FeedbackReplyVO> execute(@Valid FeedbackReplyQueryListCommand feedbackReplyQueryListCommand) {
		List<FeedbackReplyDO> feedbackReplyDO = iFeedbackReplyService.list(feedbackReplyQueryListCommand);
		List<FeedbackReplyVO> feedbackReplyVOs = FeedbackReplyAppStructMapping.instance.feedbackReplyDOsToFeedbackReplyVOs(feedbackReplyDO);
		return MultiResponse.of(feedbackReplyVOs);
	}
	/**
	 * 执行 意见反馈回复 分页查询指令
	 * @param feedbackReplyPageQueryCommand
	 * @return
	 */
	public PageResponse<FeedbackReplyVO> execute(@Valid FeedbackReplyPageQueryCommand feedbackReplyPageQueryCommand) {
		Page<FeedbackReplyDO> page = iFeedbackReplyService.listPage(feedbackReplyPageQueryCommand);
		return FeedbackReplyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 意见反馈回复 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FeedbackReplyVO> executeDetail(IdCommand detailCommand) {
		FeedbackReplyDO byId = iFeedbackReplyService.getById(detailCommand.getId());
		FeedbackReplyVO feedbackReplyVO = FeedbackReplyAppStructMapping.instance.feedbackReplyDOToFeedbackReplyVO(byId);
		return SingleResponse.of(feedbackReplyVO);
	}

	@Autowired
	public void setIFeedbackReplyService(IFeedbackReplyService iFeedbackReplyService) {
		this.iFeedbackReplyService = iFeedbackReplyService;
	}
}
