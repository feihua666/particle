package com.particle.oauth2authorization.client.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientPageQueryCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;

/**
 * <p>
 * oauth2客户端 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOauth2RegisteredClientRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<Oauth2RegisteredClientVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<Oauth2RegisteredClientVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param oauth2RegisteredClientQueryListCommand
	 * @return
	 */
	MultiResponse<Oauth2RegisteredClientVO> queryList(Oauth2RegisteredClientQueryListCommand oauth2RegisteredClientQueryListCommand);

	/**
	 * 分页查询
	 * @param oauth2RegisteredClientPageQueryCommand
	 * @return
	 */
	PageResponse<Oauth2RegisteredClientVO> pageQuery(Oauth2RegisteredClientPageQueryCommand oauth2RegisteredClientPageQueryCommand);

}
