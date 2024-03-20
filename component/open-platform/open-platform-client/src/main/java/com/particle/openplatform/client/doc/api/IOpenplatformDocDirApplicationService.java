package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;

/**
 * <p>
 * 开放接口目录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
public interface IOpenplatformDocDirApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocDirCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirVO> create(OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocDirUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirVO> update(OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand);

}
