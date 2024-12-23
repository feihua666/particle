package com.particle.openplatform.client.openapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
/**
 * <p>
 * 开放接口批量查询记录明细 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
public interface IOpenplatformOpenapiBatchQueryRecordDetailApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiBatchQueryRecordDetailCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> create(OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiBatchQueryRecordDetailUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> update(OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
}
