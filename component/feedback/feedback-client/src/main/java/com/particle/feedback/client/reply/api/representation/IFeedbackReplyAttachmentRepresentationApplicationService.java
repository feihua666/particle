package com.particle.feedback.client.reply.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈回复附件 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFeedbackReplyAttachmentRepresentationApplicationService extends IBaseApplicationService {
	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FeedbackReplyAttachmentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param feedbackReplyAttachmentQueryListCommand
	 * @return
	 */
	MultiResponse<FeedbackReplyAttachmentVO> queryList(FeedbackReplyAttachmentQueryListCommand feedbackReplyAttachmentQueryListCommand);

	/**
	 * 分页查询
	 * @param feedbackReplyAttachmentPageQueryCommand
	 * @return
	 */
	PageResponse<FeedbackReplyAttachmentVO> pageQuery(FeedbackReplyAttachmentPageQueryCommand feedbackReplyAttachmentPageQueryCommand);

}
