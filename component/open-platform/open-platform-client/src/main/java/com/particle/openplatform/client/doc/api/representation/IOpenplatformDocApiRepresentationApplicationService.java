package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDetailVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;

/**
 * <p>
 * 开放接口文档接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 查询详情，包含完整接口文档
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDetailVO> queryAllDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiVO> queryList(OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiVO> pageQuery(OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand);

}
