package com.particle.feedback.app.feedback.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.executor.FeedbackCreateCommandExecutor;
import com.particle.feedback.app.feedback.executor.FeedbackDeleteCommandExecutor;
import com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import com.particle.feedback.client.feedback.dto.command.FeedbackCreateCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackManualHandleCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 意见反馈 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Transactional
@Service
@CatchAndLog
public class FeedbackApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackApplicationService {

	private FeedbackCreateCommandExecutor feedbackCreateCommandExecutor;

	private FeedbackDeleteCommandExecutor feedbackDeleteCommandExecutor;

	@Override
	public SingleResponse<FeedbackVO> create(FeedbackCreateCommand feedbackCreateCommand) {
		return feedbackCreateCommandExecutor.execute(feedbackCreateCommand);
	}

	@Override
	public SingleResponse<FeedbackVO> manualHandle(FeedbackManualHandleCommand feedbackManualHandleCommand) {
		return feedbackCreateCommandExecutor.manualHandle(feedbackManualHandleCommand);
	}

	@Override
	public SingleResponse<FeedbackVO> delete(IdCommand deleteCommand) {
		return feedbackDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setFeedbackCreateCommandExecutor(FeedbackCreateCommandExecutor feedbackCreateCommandExecutor) {
		this.feedbackCreateCommandExecutor = feedbackCreateCommandExecutor;
	}

	@Autowired
	public void setFeedbackDeleteCommandExecutor(FeedbackDeleteCommandExecutor feedbackDeleteCommandExecutor) {
		this.feedbackDeleteCommandExecutor = feedbackDeleteCommandExecutor;
	}

}
