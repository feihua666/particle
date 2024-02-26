package com.particle.feedback.client.feedback.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈附件 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFeedbackAttachmentRepresentationApplicationService extends IBaseApplicationService {
	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FeedbackAttachmentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param feedbackAttachmentQueryListCommand
	 * @return
	 */
	MultiResponse<FeedbackAttachmentVO> queryList(FeedbackAttachmentQueryListCommand feedbackAttachmentQueryListCommand);

	/**
	 * 分页查询
	 * @param feedbackAttachmentPageQueryCommand
	 * @return
	 */
	PageResponse<FeedbackAttachmentVO> pageQuery(FeedbackAttachmentPageQueryCommand feedbackAttachmentPageQueryCommand);

}
