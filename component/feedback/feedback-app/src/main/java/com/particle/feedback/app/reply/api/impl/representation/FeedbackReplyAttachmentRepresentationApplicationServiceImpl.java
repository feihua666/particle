package com.particle.feedback.app.reply.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.reply.executor.representation.FeedbackReplyAttachmentQueryCommandExecutor;
import com.particle.feedback.client.reply.api.representation.IFeedbackReplyAttachmentRepresentationApplicationService;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 意见反馈回复附件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Service
@CatchAndLog
public class FeedbackReplyAttachmentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackReplyAttachmentRepresentationApplicationService {

    private FeedbackReplyAttachmentQueryCommandExecutor feedbackReplyAttachmentQueryCommandExecutor;

    @Override
    public SingleResponse<FeedbackReplyAttachmentVO> queryDetail(IdCommand detailCommand) {
        return feedbackReplyAttachmentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<FeedbackReplyAttachmentVO> pageQuery(FeedbackReplyAttachmentPageQueryCommand feedbackReplyAttachmentPageQueryCommand) {
        return feedbackReplyAttachmentQueryCommandExecutor.execute(feedbackReplyAttachmentPageQueryCommand);
    }

    @Override
    public MultiResponse<FeedbackReplyAttachmentVO> queryList(FeedbackReplyAttachmentQueryListCommand feedbackReplyAttachmentQueryListCommand) {
        return feedbackReplyAttachmentQueryCommandExecutor.execute(feedbackReplyAttachmentQueryListCommand);
    }

    @Autowired
    public void setFeedbackReplyAttachmentQueryCommandExecutor(FeedbackReplyAttachmentQueryCommandExecutor feedbackReplyAttachmentQueryCommandExecutor) {
        this.feedbackReplyAttachmentQueryCommandExecutor = feedbackReplyAttachmentQueryCommandExecutor;
    }
}
