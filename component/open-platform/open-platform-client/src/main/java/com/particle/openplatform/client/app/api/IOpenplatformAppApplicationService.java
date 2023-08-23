package com.particle.openplatform.client.app.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppCreateCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;

/**
 * <p>
 * 开放平台应用 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
public interface IOpenplatformAppApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformAppCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppVO> create(OpenplatformAppCreateCommand openplatformAppCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformAppUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppVO> update(OpenplatformAppUpdateCommand openplatformAppUpdateCommand);

}
