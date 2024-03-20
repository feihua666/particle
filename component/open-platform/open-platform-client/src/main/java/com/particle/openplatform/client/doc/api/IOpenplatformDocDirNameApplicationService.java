package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;

/**
 * <p>
 * 开放接口目录名称 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
public interface IOpenplatformDocDirNameApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocDirNameCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirNameVO> create(OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirNameVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocDirNameUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirNameVO> update(OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand);

}
