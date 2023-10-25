package com.particle.usagecount.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.UsageCountConfigCreateCommand;
import com.particle.usagecount.client.dto.command.UsageCountConfigUpdateCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;

/**
 * <p>
 * 使用次数配置 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
public interface IUsageCountConfigApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param usageCountConfigCreateCommand
	 * @return
	 */
	SingleResponse<UsageCountConfigVO> create(UsageCountConfigCreateCommand usageCountConfigCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<UsageCountConfigVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param usageCountConfigUpdateCommand
	 * @return
	 */
	SingleResponse<UsageCountConfigVO> update(UsageCountConfigUpdateCommand usageCountConfigUpdateCommand);

}
