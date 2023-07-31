package com.particle.oauth2authorization.client.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientCreateCommand;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientUpdateCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;

/**
 * <p>
 * oauth2客户端 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
public interface IOauth2RegisteredClientApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param oauth2RegisteredClientCreateCommand
	 * @return
	 */
	SingleResponse<Oauth2RegisteredClientVO> create(Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<Oauth2RegisteredClientVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param oauth2RegisteredClientUpdateCommand
	 * @return
	 */
	SingleResponse<Oauth2RegisteredClientVO> update(Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand);

}
