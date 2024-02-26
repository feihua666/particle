package com.particle.feedback.client.feedback.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 意见反馈 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFeedbackRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FeedbackVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param feedbackQueryListCommand
	 * @return
	 */
	MultiResponse<FeedbackVO> queryList(FeedbackQueryListCommand feedbackQueryListCommand);

	/**
	 * 分页查询
	 * @param feedbackPageQueryCommand
	 * @return
	 */
	PageResponse<FeedbackVO> pageQuery(FeedbackPageQueryCommand feedbackPageQueryCommand);

}
