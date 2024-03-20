package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;

/**
 * <p>
 * 开放接口文档模板 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
public interface IOpenplatformDocApiDocTemplateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocTemplateCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateVO> create(OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocTemplateUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateVO> update(OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand);

}
