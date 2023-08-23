package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;

/**
 * <p>
 * 开放平台开放接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiVO> queryList(OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiVO> pageQuery(OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand);

}
