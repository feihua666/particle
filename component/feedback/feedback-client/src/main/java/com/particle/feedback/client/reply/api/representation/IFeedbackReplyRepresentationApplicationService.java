package com.particle.feedback.client.reply.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈回复 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFeedbackReplyRepresentationApplicationService extends IBaseApplicationService {
	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param feedbackReplyQueryListCommand
	 * @return
	 */
	MultiResponse<FeedbackReplyVO> queryList(FeedbackReplyQueryListCommand feedbackReplyQueryListCommand);

	/**
	 * 分页查询
	 * @param feedbackReplyPageQueryCommand
	 * @return
	 */
	PageResponse<FeedbackReplyVO> pageQuery(FeedbackReplyPageQueryCommand feedbackReplyPageQueryCommand);

}
