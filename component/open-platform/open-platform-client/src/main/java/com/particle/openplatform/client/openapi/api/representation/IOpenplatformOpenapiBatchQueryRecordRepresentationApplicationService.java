package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;

/**
 * <p>
 * 开放接口批量查询记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiBatchQueryRecordQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiBatchQueryRecordVO> queryList(OpenplatformOpenapiBatchQueryRecordQueryListCommand openplatformOpenapiBatchQueryRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiBatchQueryRecordPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiBatchQueryRecordVO> pageQuery(OpenplatformOpenapiBatchQueryRecordPageQueryCommand openplatformOpenapiBatchQueryRecordPageQueryCommand);

}
