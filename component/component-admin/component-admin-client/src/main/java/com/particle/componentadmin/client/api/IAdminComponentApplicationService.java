package com.particle.componentadmin.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.componentadmin.client.dto.command.AdminComponentCreateCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentUpdateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
/**
 * <p>
 * 组件 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
public interface IAdminComponentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param adminComponentCreateCommand
	 * @return
	 */
	SingleResponse<AdminComponentVO> create(AdminComponentCreateCommand adminComponentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AdminComponentVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param adminComponentUpdateCommand
	 * @return
	 */
	SingleResponse<AdminComponentVO> update(AdminComponentUpdateCommand adminComponentUpdateCommand);
}
