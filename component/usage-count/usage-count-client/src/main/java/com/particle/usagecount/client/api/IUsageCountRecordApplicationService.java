package com.particle.usagecount.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;

/**
 * <p>
 * 使用次数记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
public interface IUsageCountRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param usageCountRecordMarkCommand
	 * @return
	 */
	SingleResponse<UsageCountRecordMarkVO> mark(UsageCountRecordMarkCommand usageCountRecordMarkCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<UsageCountRecordVO> delete(IdCommand deleteCommand);

}
