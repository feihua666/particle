package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;

/**
 * <p>
 * 开放接口文档接口与目录关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
public interface IOpenplatformDocApiDirRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDirRelCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDirRelVO> create(OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDirRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDirRelUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDirRelVO> update(OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand);

}
