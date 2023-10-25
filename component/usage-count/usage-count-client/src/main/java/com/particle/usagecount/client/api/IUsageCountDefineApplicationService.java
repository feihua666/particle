package com.particle.usagecount.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.UsageCountDefineCreateCommand;
import com.particle.usagecount.client.dto.command.UsageCountDefineUpdateCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;

/**
 * <p>
 * 使用次数定义 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
public interface IUsageCountDefineApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param usageCountDefineCreateCommand
	 * @return
	 */
	SingleResponse<UsageCountDefineVO> create(UsageCountDefineCreateCommand usageCountDefineCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<UsageCountDefineVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param usageCountDefineUpdateCommand
	 * @return
	 */
	SingleResponse<UsageCountDefineVO> update(UsageCountDefineUpdateCommand usageCountDefineUpdateCommand);

}
