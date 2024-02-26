package com.particle.feedback.app.feedback.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.executor.FeedbackAttachmentCreateCommandExecutor;
import com.particle.feedback.app.feedback.executor.FeedbackAttachmentDeleteCommandExecutor;
import com.particle.feedback.client.feedback.api.IFeedbackAttachmentApplicationService;
import com.particle.feedback.client.feedback.dto.command.FeedbackAttachmentCreateCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 意见反馈附件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Transactional
@Service
@CatchAndLog
public class FeedbackAttachmentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackAttachmentApplicationService {

	private FeedbackAttachmentCreateCommandExecutor feedbackAttachmentCreateCommandExecutor;

	private FeedbackAttachmentDeleteCommandExecutor feedbackAttachmentDeleteCommandExecutor;


	@Override
	public SingleResponse<FeedbackAttachmentVO> create(FeedbackAttachmentCreateCommand feedbackAttachmentCreateCommand) {
		return feedbackAttachmentCreateCommandExecutor.execute(feedbackAttachmentCreateCommand);
	}

	@Override
	public SingleResponse<FeedbackAttachmentVO> delete(IdCommand deleteCommand) {
		return feedbackAttachmentDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setFeedbackAttachmentCreateCommandExecutor(FeedbackAttachmentCreateCommandExecutor feedbackAttachmentCreateCommandExecutor) {
		this.feedbackAttachmentCreateCommandExecutor = feedbackAttachmentCreateCommandExecutor;
	}

	@Autowired
	public void setFeedbackAttachmentDeleteCommandExecutor(FeedbackAttachmentDeleteCommandExecutor feedbackAttachmentDeleteCommandExecutor) {
		this.feedbackAttachmentDeleteCommandExecutor = feedbackAttachmentDeleteCommandExecutor;
	}

}
