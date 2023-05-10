package com.particle.tracking.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;

/**
 * <p>
 * 页面埋点记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
public interface ITrackingPageRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param trackingPageRecordCreateCommand
	 * @return
	 */
	SingleResponse<AbstractBaseIdVO> create(TrackingPageRecordCreateCommand trackingPageRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TrackingPageRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param trackingPageRecordUpdateCommand
	 * @return
	 */
	SingleResponse<TrackingPageRecordVO> update(TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand);

}
