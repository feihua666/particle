package com.particle.openplatform.client.app.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiCreateCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;

/**
 * <p>
 * 开放平台应用与开放接口配置 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
public interface IOpenplatformAppOpenapiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformAppOpenapiCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppOpenapiVO> create(OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppOpenapiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformAppOpenapiUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppOpenapiVO> update(OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand);

}
