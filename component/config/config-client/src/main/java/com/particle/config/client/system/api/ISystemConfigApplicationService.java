package com.particle.config.client.system.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;

/**
 * <p>
 * 系统参数配置 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
public interface ISystemConfigApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param systemConfigCreateCommand
	 * @return
	 */
	SingleResponse<SystemConfigVO> create(SystemConfigCreateCommand systemConfigCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SystemConfigVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param systemConfigUpdateCommand
	 * @return
	 */
	SingleResponse<SystemConfigVO> update(SystemConfigUpdateCommand systemConfigUpdateCommand);

}
