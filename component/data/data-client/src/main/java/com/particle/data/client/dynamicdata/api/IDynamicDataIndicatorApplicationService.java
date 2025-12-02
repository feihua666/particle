package com.particle.data.client.dynamicdata.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCreateCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
/**
 * <p>
 * 动态数据指标 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
public interface IDynamicDataIndicatorApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicDataIndicatorCreateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorVO> create(DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dynamicDataIndicatorUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorVO> update(DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand);
}
