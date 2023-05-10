package com.particle.tracking.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.client.dto.command.TrackingPageCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;

/**
 * <p>
 * 埋点页面 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
public interface ITrackingPageApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param trackingPageCreateCommand
	 * @return
	 */
	SingleResponse<TrackingPageVO> create(TrackingPageCreateCommand trackingPageCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TrackingPageVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param trackingPageUpdateCommand
	 * @return
	 */
	SingleResponse<TrackingPageVO> update(TrackingPageUpdateCommand trackingPageUpdateCommand);

}
