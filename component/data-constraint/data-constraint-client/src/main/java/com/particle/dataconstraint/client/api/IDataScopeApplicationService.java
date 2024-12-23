package com.particle.dataconstraint.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据范围 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
public interface IDataScopeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataScopeCreateCommand
	 * @return
	 */
	SingleResponse<DataScopeVO> create(DataScopeCreateCommand dataScopeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataScopeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataScopeUpdateCommand
	 * @return
	 */
	SingleResponse<DataScopeVO> update(DataScopeUpdateCommand dataScopeUpdateCommand);

}
