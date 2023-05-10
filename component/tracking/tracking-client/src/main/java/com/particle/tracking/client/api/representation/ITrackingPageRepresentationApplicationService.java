package com.particle.tracking.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.client.dto.command.representation.TrackingPagePageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;

/**
 * <p>
 * 埋点页面 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITrackingPageRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TrackingPageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TrackingPageVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param trackingPageQueryListCommand
	 * @return
	 */
	MultiResponse<TrackingPageVO> queryList(TrackingPageQueryListCommand trackingPageQueryListCommand);

	/**
	 * 分页查询
	 * @param trackingPagePageQueryCommand
	 * @return
	 */
	PageResponse<TrackingPageVO> pageQuery(TrackingPagePageQueryCommand trackingPagePageQueryCommand);

}
