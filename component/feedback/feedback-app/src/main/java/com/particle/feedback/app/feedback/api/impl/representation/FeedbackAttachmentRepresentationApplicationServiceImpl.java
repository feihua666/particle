package com.particle.feedback.app.feedback.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.executor.representation.FeedbackAttachmentQueryCommandExecutor;
import com.particle.feedback.client.feedback.api.representation.IFeedbackAttachmentRepresentationApplicationService;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 意见反馈附件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Service
@CatchAndLog
public class FeedbackAttachmentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackAttachmentRepresentationApplicationService {

    private FeedbackAttachmentQueryCommandExecutor feedbackAttachmentQueryCommandExecutor;

    @Override
    public SingleResponse<FeedbackAttachmentVO> queryDetail(IdCommand detailCommand) {
        return feedbackAttachmentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<FeedbackAttachmentVO> pageQuery(FeedbackAttachmentPageQueryCommand feedbackAttachmentPageQueryCommand) {
        return feedbackAttachmentQueryCommandExecutor.execute(feedbackAttachmentPageQueryCommand);
    }

    @Override
    public MultiResponse<FeedbackAttachmentVO> queryList(FeedbackAttachmentQueryListCommand feedbackAttachmentQueryListCommand) {
        return feedbackAttachmentQueryCommandExecutor.execute(feedbackAttachmentQueryListCommand);
    }

    @Autowired
    public void setFeedbackAttachmentQueryCommandExecutor(FeedbackAttachmentQueryCommandExecutor feedbackAttachmentQueryCommandExecutor) {
        this.feedbackAttachmentQueryCommandExecutor = feedbackAttachmentQueryCommandExecutor;
    }
}
