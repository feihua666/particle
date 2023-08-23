package com.particle.openplatform.client.app.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;

/**
 * <p>
 * 开放平台应用与开放接口配置 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformAppOpenapiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppOpenapiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformAppOpenapiQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformAppOpenapiVO> queryList(OpenplatformAppOpenapiQueryListCommand openplatformAppOpenapiQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformAppOpenapiPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformAppOpenapiVO> pageQuery(OpenplatformAppOpenapiPageQueryCommand openplatformAppOpenapiPageQueryCommand);

}
