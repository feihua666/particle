package com.particle.openplatform.client.openapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
/**
 * <p>
 * 开放接口批量查询记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
public interface IOpenplatformOpenapiBatchQueryRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiBatchQueryRecordCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> create(OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiBatchQueryRecordUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> update(OpenplatformOpenapiBatchQueryRecordUpdateCommand openplatformOpenapiBatchQueryRecordUpdateCommand);
}
