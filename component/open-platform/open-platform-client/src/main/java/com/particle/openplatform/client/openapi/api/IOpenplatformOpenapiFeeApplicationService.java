package com.particle.openplatform.client.openapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;

/**
 * <p>
 * 开放平台开放接口费用 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
public interface IOpenplatformOpenapiFeeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiFeeCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiFeeVO> create(OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiFeeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiFeeUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiFeeVO> update(OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand);

}
