package com.particle.tracking.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordPageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;

/**
 * <p>
 * 页面埋点记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITrackingPageRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TrackingPageRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param trackingPageRecordQueryListCommand
	 * @return
	 */
	MultiResponse<TrackingPageRecordVO> queryList(TrackingPageRecordQueryListCommand trackingPageRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param trackingPageRecordPageQueryCommand
	 * @return
	 */
	PageResponse<TrackingPageRecordVO> pageQuery(TrackingPageRecordPageQueryCommand trackingPageRecordPageQueryCommand);

}
