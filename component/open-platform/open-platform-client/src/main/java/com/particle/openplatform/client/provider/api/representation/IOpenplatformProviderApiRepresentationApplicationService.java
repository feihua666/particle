package com.particle.openplatform.client.provider.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;

/**
 * <p>
 * 开放平台供应商接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderApiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderApiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderApiQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderApiVO> queryList(OpenplatformProviderApiQueryListCommand openplatformProviderApiQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderApiPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderApiVO> pageQuery(OpenplatformProviderApiPageQueryCommand openplatformProviderApiPageQueryCommand);

}
