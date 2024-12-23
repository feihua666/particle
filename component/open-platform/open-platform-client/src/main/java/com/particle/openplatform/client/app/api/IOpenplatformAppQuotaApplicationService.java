package com.particle.openplatform.client.app.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaCreateCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
/**
 * <p>
 * 开放平台应用额度 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
public interface IOpenplatformAppQuotaApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformAppQuotaCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppQuotaVO> create(OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppQuotaVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformAppQuotaUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppQuotaVO> update(OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand);
}
