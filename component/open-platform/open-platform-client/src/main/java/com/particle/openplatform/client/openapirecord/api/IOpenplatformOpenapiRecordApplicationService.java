package com.particle.openplatform.client.openapirecord.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;

/**
 * <p>
 * 开放平台开放接口调用记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
public interface IOpenplatformOpenapiRecordApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordVO> delete(IdCommand deleteCommand);

}
