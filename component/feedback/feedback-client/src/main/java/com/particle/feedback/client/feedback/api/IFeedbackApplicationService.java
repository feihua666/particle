package com.particle.feedback.client.feedback.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackCreateCommand;
import com.particle.feedback.client.feedback.dto.command.FeedbackManualHandleCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 意见反馈 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
public interface IFeedbackApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param feedbackCreateCommand
	 * @return
	 */
	SingleResponse<FeedbackVO> create(FeedbackCreateCommand feedbackCreateCommand);

	/**
	 * 手动处理
	 * @param feedbackManualHandleCommand
	 * @return
	 */
	public SingleResponse<FeedbackVO> manualHandle(FeedbackManualHandleCommand feedbackManualHandleCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FeedbackVO> delete(IdCommand deleteCommand);

}
