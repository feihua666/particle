package com.particle.feedback.app.reply.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.reply.executor.representation.FeedbackReplyQueryCommandExecutor;
import com.particle.feedback.client.reply.api.representation.IFeedbackReplyRepresentationApplicationService;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 意见反馈回复 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Service
@CatchAndLog
public class FeedbackReplyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackReplyRepresentationApplicationService {

    private FeedbackReplyQueryCommandExecutor feedbackReplyQueryCommandExecutor;

    @Override
    public SingleResponse<FeedbackReplyVO> queryDetail(IdCommand detailCommand) {
        return feedbackReplyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<FeedbackReplyVO> pageQuery(FeedbackReplyPageQueryCommand feedbackReplyPageQueryCommand) {
        return feedbackReplyQueryCommandExecutor.execute(feedbackReplyPageQueryCommand);
    }

    @Override
    public MultiResponse<FeedbackReplyVO> queryList(FeedbackReplyQueryListCommand feedbackReplyQueryListCommand) {
        return feedbackReplyQueryCommandExecutor.execute(feedbackReplyQueryListCommand);
    }

    @Autowired
    public void setFeedbackReplyQueryCommandExecutor(FeedbackReplyQueryCommandExecutor feedbackReplyQueryCommandExecutor) {
        this.feedbackReplyQueryCommandExecutor = feedbackReplyQueryCommandExecutor;
    }
}
