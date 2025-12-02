package com.particle.data.client.dynamicdata.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;

/**
 * <p>
 * 动态数据分类 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicDataCategoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicDataCategoryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicDataCategoryQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicDataCategoryVO> queryList(DynamicDataCategoryQueryListCommand dynamicDataCategoryQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicDataCategoryPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicDataCategoryVO> pageQuery(DynamicDataCategoryPageQueryCommand dynamicDataCategoryPageQueryCommand);

}
