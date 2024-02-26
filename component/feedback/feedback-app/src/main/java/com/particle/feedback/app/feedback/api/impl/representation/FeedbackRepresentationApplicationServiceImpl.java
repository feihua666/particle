package com.particle.feedback.app.feedback.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.app.feedback.executor.representation.FeedbackQueryCommandExecutor;
import com.particle.feedback.client.feedback.api.representation.IFeedbackRepresentationApplicationService;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 意见反馈 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Service
@CatchAndLog
public class FeedbackRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFeedbackRepresentationApplicationService {

    private FeedbackQueryCommandExecutor feedbackQueryCommandExecutor;

    @Override
    public SingleResponse<FeedbackVO> queryDetail(IdCommand detailCommand) {
        return feedbackQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<FeedbackVO> pageQuery(FeedbackPageQueryCommand feedbackPageQueryCommand) {
        return feedbackQueryCommandExecutor.execute(feedbackPageQueryCommand);
    }

    @Override
    public MultiResponse<FeedbackVO> queryList(FeedbackQueryListCommand feedbackQueryListCommand) {
        return feedbackQueryCommandExecutor.execute(feedbackQueryListCommand);
    }

    @Autowired
    public void setFeedbackQueryCommandExecutor(FeedbackQueryCommandExecutor feedbackQueryCommandExecutor) {
        this.feedbackQueryCommandExecutor = feedbackQueryCommandExecutor;
    }
}
