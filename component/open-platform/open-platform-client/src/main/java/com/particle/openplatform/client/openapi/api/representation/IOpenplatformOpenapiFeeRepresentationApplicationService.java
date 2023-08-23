package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeeQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;

/**
 * <p>
 * 开放平台开放接口费用 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiFeeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiFeeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiFeeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiFeeQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiFeeVO> queryList(OpenplatformOpenapiFeeQueryListCommand openplatformOpenapiFeeQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiFeePageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiFeeVO> pageQuery(OpenplatformOpenapiFeePageQueryCommand openplatformOpenapiFeePageQueryCommand);

}
