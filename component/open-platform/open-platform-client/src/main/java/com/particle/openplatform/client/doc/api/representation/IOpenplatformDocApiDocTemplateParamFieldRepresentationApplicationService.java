package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;

/**
 * <p>
 * 开放接口文档模板参数字段 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocTemplateParamFieldQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryList(OpenplatformDocApiDocTemplateParamFieldQueryListCommand openplatformDocApiDocTemplateParamFieldQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocTemplateParamFieldPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocTemplateParamFieldVO> pageQuery(OpenplatformDocApiDocTemplateParamFieldPageQueryCommand openplatformDocApiDocTemplateParamFieldPageQueryCommand);

}
