package com.particle.feedback.client.reply.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈回复 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
public interface IFeedbackReplyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param feedbackReplyCreateCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyVO> create(FeedbackReplyCreateCommand feedbackReplyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyVO> delete(IdCommand deleteCommand);

}
