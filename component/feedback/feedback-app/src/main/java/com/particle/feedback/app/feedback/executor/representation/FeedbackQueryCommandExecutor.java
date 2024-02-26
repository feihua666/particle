package com.particle.feedback.app.feedback.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.structmapping.FeedbackAppStructMapping;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 意见反馈 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Component
@Validated
public class FeedbackQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFeedbackService iFeedbackService;

	/**
	 * 执行 意见反馈 列表查询指令
	 * @param feedbackQueryListCommand
	 * @return
	 */
	public MultiResponse<FeedbackVO> execute(@Valid FeedbackQueryListCommand feedbackQueryListCommand) {
		List<FeedbackDO> feedbackDO = iFeedbackService.list(feedbackQueryListCommand);
		List<FeedbackVO> feedbackVOs = FeedbackAppStructMapping.instance.feedbackDOsToFeedbackVOs(feedbackDO);
		return MultiResponse.of(feedbackVOs);
	}
	/**
	 * 执行 意见反馈 分页查询指令
	 * @param feedbackPageQueryCommand
	 * @return
	 */
	public PageResponse<FeedbackVO> execute(@Valid FeedbackPageQueryCommand feedbackPageQueryCommand) {
		Page<FeedbackDO> page = iFeedbackService.listPage(feedbackPageQueryCommand);
		return FeedbackAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 意见反馈 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FeedbackVO> executeDetail(IdCommand detailCommand) {
		FeedbackDO byId = iFeedbackService.getById(detailCommand.getId());
		FeedbackVO feedbackVO = FeedbackAppStructMapping.instance.feedbackDOToFeedbackVO(byId);
		return SingleResponse.of(feedbackVO);
	}

	@Autowired
	public void setIFeedbackService(IFeedbackService iFeedbackService) {
		this.iFeedbackService = iFeedbackService;
	}
}
