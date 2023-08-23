package com.particle.openplatform.client.provider.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderCreateCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderUpdateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;

/**
 * <p>
 * 开放平台开放接口供应商 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
public interface IOpenplatformProviderApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformProviderCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderVO> create(OpenplatformProviderCreateCommand openplatformProviderCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformProviderUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderVO> update(OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand);

}
