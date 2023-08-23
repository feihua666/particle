package com.particle.openplatform.client.openapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;

/**
 * <p>
 * 开放平台开放接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
public interface IOpenplatformOpenapiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> create(OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> update(OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand);

}
