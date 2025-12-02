package com.particle.data.client.dynamictable.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
/**
 * <p>
 * 动态数据表格字段 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
public interface IDynamicTableFieldApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicTableFieldCreateCommand
	 * @return
	 */
	SingleResponse<DynamicTableFieldVO> create(DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicTableFieldVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dynamicTableFieldUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableFieldVO> update(DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand);
}
