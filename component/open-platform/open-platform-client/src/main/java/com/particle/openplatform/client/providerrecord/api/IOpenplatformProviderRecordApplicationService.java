package com.particle.openplatform.client.providerrecord.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
public interface IOpenplatformProviderRecordApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordVO> delete(IdCommand deleteCommand);

}
