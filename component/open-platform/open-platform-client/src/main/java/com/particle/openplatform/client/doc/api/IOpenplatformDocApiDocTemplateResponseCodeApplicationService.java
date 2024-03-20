package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;

/**
 * <p>
 * 开放接口文档模板响应码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
public interface IOpenplatformDocApiDocTemplateResponseCodeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocTemplateResponseCodeCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> create(OpenplatformDocApiDocTemplateResponseCodeCreateCommand openplatformDocApiDocTemplateResponseCodeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocTemplateResponseCodeUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> update(OpenplatformDocApiDocTemplateResponseCodeUpdateCommand openplatformDocApiDocTemplateResponseCodeUpdateCommand);

}
