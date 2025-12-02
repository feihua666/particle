package com.particle.data.client.dynamictable.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;

/**
 * <p>
 * 动态数据表格字段 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicTableFieldRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicTableFieldVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicTableFieldQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicTableFieldVO> queryList(DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicTableFieldPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicTableFieldVO> pageQuery(DynamicTableFieldPageQueryCommand dynamicTableFieldPageQueryCommand);

}
