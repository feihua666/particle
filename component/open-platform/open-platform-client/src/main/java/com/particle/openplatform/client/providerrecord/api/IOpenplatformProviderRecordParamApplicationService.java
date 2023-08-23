package com.particle.openplatform.client.providerrecord.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
public interface IOpenplatformProviderRecordParamApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordParamVO> delete(IdCommand deleteCommand);

}
