package com.particle.usagecount.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;

/**
 * <p>
 * 使用次数记录明细 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
public interface IUsageCountRecordDetailApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<UsageCountRecordDetailVO> delete(IdCommand deleteCommand);

}
