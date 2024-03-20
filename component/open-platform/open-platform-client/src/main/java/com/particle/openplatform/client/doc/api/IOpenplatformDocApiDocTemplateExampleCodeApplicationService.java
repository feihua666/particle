package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;

/**
 * <p>
 * 开放接口文档模板示例代码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
public interface IOpenplatformDocApiDocTemplateExampleCodeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocTemplateExampleCodeCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> create(OpenplatformDocApiDocTemplateExampleCodeCreateCommand openplatformDocApiDocTemplateExampleCodeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocTemplateExampleCodeUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> update(OpenplatformDocApiDocTemplateExampleCodeUpdateCommand openplatformDocApiDocTemplateExampleCodeUpdateCommand);

}
