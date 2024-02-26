package com.particle.feedback.app.reply.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.reply.executor.FeedbackReplyAttachmentCreateCommandExecutor;
import com.particle.feedback.app.reply.executor.FeedbackReplyAttachmentDeleteCommandExecutor;
import com.particle.feedback.client.reply.api.IFeedbackReplyAttachmentApplicationService;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyAttachmentCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 意见反馈回复附件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Transactional
@Service
@CatchAndLog
public class FeedbackReplyAttachmentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackReplyAttachmentApplicationService {

	private FeedbackReplyAttachmentCreateCommandExecutor feedbackReplyAttachmentCreateCommandExecutor;

	private FeedbackReplyAttachmentDeleteCommandExecutor feedbackReplyAttachmentDeleteCommandExecutor;


	@Override
	public SingleResponse<FeedbackReplyAttachmentVO> create(FeedbackReplyAttachmentCreateCommand feedbackReplyAttachmentCreateCommand) {
		return feedbackReplyAttachmentCreateCommandExecutor.execute(feedbackReplyAttachmentCreateCommand);
	}

	@Override
	public SingleResponse<FeedbackReplyAttachmentVO> delete(IdCommand deleteCommand) {
		return feedbackReplyAttachmentDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setFeedbackReplyAttachmentCreateCommandExecutor(FeedbackReplyAttachmentCreateCommandExecutor feedbackReplyAttachmentCreateCommandExecutor) {
		this.feedbackReplyAttachmentCreateCommandExecutor = feedbackReplyAttachmentCreateCommandExecutor;
	}

	@Autowired
	public void setFeedbackReplyAttachmentDeleteCommandExecutor(FeedbackReplyAttachmentDeleteCommandExecutor feedbackReplyAttachmentDeleteCommandExecutor) {
		this.feedbackReplyAttachmentDeleteCommandExecutor = feedbackReplyAttachmentDeleteCommandExecutor;
	}

}
