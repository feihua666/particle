package com.particle.openplatform.client.provider.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;

/**
 * <p>
 * 开放平台开放接口供应商 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderVO> queryList(OpenplatformProviderQueryListCommand openplatformProviderQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderVO> pageQuery(OpenplatformProviderPageQueryCommand openplatformProviderPageQueryCommand);

}
