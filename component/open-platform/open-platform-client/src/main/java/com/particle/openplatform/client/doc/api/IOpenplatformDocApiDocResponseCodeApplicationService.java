package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;

/**
 * <p>
 * 开放接口文档响应码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
public interface IOpenplatformDocApiDocResponseCodeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocResponseCodeCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocResponseCodeVO> create(OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocResponseCodeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocResponseCodeUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocResponseCodeVO> update(OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand);

}
