package com.particle.dataconstraint.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataObjectCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataObjectUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据对象 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
public interface IDataObjectApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataObjectCreateCommand
	 * @return
	 */
	SingleResponse<DataObjectVO> create(DataObjectCreateCommand dataObjectCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataObjectVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataObjectUpdateCommand
	 * @return
	 */
	SingleResponse<DataObjectVO> update(DataObjectUpdateCommand dataObjectUpdateCommand);

}
