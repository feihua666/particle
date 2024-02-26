package com.particle.feedback.app.reply.api.impl;

import com.particle.feedback.app.reply.executor.FeedbackReplyCreateCommandExecutor;
import com.particle.feedback.app.reply.executor.FeedbackReplyDeleteCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.reply.api.IFeedbackReplyApplicationService;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 意见反馈回复 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Transactional
@Service
@CatchAndLog
public class FeedbackReplyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackReplyApplicationService {

	private FeedbackReplyCreateCommandExecutor feedbackReplyCreateCommandExecutor;

	private FeedbackReplyDeleteCommandExecutor feedbackReplyDeleteCommandExecutor;


	@Override
	public SingleResponse<FeedbackReplyVO> create(FeedbackReplyCreateCommand feedbackReplyCreateCommand) {
		return feedbackReplyCreateCommandExecutor.execute(feedbackReplyCreateCommand);
	}

	@Override
	public SingleResponse<FeedbackReplyVO> delete(IdCommand deleteCommand) {
		return feedbackReplyDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setFeedbackReplyCreateCommandExecutor(FeedbackReplyCreateCommandExecutor feedbackReplyCreateCommandExecutor) {
		this.feedbackReplyCreateCommandExecutor = feedbackReplyCreateCommandExecutor;
	}

	@Autowired
	public void setFeedbackReplyDeleteCommandExecutor(FeedbackReplyDeleteCommandExecutor feedbackReplyDeleteCommandExecutor) {
		this.feedbackReplyDeleteCommandExecutor = feedbackReplyDeleteCommandExecutor;
	}
}
