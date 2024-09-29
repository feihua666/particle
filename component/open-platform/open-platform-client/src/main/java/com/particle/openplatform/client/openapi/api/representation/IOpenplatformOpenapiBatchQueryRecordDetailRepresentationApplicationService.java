package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;

/**
 * <p>
 * 开放接口批量查询记录明细 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiBatchQueryRecordDetailQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryList(OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand openplatformOpenapiBatchQueryRecordDetailQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiBatchQueryRecordDetailPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> pageQuery(OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand openplatformOpenapiBatchQueryRecordDetailPageQueryCommand);

}
