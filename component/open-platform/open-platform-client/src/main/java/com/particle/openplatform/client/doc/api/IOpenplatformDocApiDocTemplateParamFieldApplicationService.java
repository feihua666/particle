package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;

/**
 * <p>
 * 开放接口文档模板参数字段 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
public interface IOpenplatformDocApiDocTemplateParamFieldApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> create(OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocTemplateParamFieldUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> update(OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand);
	/**
	 * 解析并创建
	 * @param command
	 * @return
	 */
	Response parseAndCreate(OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand command);
}
