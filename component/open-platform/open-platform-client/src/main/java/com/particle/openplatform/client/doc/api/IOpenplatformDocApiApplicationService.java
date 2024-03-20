package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;

/**
 * <p>
 * 开放接口文档接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
public interface IOpenplatformDocApiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiVO> create(OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiVO> update(OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand);

}
