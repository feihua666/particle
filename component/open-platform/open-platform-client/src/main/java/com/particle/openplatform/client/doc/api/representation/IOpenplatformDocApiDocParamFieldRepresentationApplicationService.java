package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;

/**
 * <p>
 * 开放接口文档参数字段 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocParamFieldRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocParamFieldQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocParamFieldVO> queryList(OpenplatformDocApiDocParamFieldQueryListCommand openplatformDocApiDocParamFieldQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocParamFieldPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocParamFieldVO> pageQuery(OpenplatformDocApiDocParamFieldPageQueryCommand openplatformDocApiDocParamFieldPageQueryCommand);

}
