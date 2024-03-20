package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;

/**
 * <p>
 * 开放接口文档 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
public interface IOpenplatformDocApiDocApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocVO> create(OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocVO> update(OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand);

}
