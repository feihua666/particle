package com.particle.feedback.client.reply.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.reply.dto.command.FeedbackReplyAttachmentCreateCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈回复附件 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
public interface IFeedbackReplyAttachmentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param feedbackReplyAttachmentCreateCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyAttachmentVO> create(FeedbackReplyAttachmentCreateCommand feedbackReplyAttachmentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyAttachmentVO> delete(IdCommand deleteCommand);

}
