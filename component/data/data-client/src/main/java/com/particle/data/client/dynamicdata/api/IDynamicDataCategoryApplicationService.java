package com.particle.data.client.dynamicdata.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
/**
 * <p>
 * 动态数据分类 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
public interface IDynamicDataCategoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicDataCategoryCreateCommand
	 * @return
	 */
	SingleResponse<DynamicDataCategoryVO> create(DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicDataCategoryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dynamicDataCategoryUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataCategoryVO> update(DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand);
}
