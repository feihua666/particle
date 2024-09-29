package com.particle.openplatform.client.provider.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiCreateCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiUpdateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
/**
 * <p>
 * 开放平台供应商接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
public interface IOpenplatformProviderApiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformProviderApiCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderApiVO> create(OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderApiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformProviderApiUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderApiVO> update(OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand);
}
