package com.particle.openplatform.client.app.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;

/**
 * <p>
 * 开放平台应用 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformAppRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformAppQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformAppVO> queryList(OpenplatformAppQueryListCommand openplatformAppQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformAppPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformAppVO> pageQuery(OpenplatformAppPageQueryCommand openplatformAppPageQueryCommand);

}
