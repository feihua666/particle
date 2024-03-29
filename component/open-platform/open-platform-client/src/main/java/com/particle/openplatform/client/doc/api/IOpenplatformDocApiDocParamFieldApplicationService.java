package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;

/**
 * <p>
 * 开放接口文档参数字段 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
public interface IOpenplatformDocApiDocParamFieldApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocParamFieldCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocParamFieldVO> create(OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocParamFieldVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocParamFieldUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocParamFieldVO> update(OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand);

	/**
	 * 解析并创建
	 * @param command
	 * @return
	 */
	Response parseAndCreate(OpenplatformDocApiDocParamFieldParseAndCreateCommand command);

}
