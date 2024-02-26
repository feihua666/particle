package com.particle.feedback.client.feedback.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackAttachmentCreateCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈附件 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
public interface IFeedbackAttachmentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param feedbackAttachmentCreateCommand
	 * @return
	 */
	SingleResponse<FeedbackAttachmentVO> create(FeedbackAttachmentCreateCommand feedbackAttachmentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FeedbackAttachmentVO> delete(IdCommand deleteCommand);

}
