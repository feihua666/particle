package com.particle.data.client.dynamictable.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableDataPageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTablePageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;

import java.util.Map;

/**
 * <p>
 * 动态数据表格 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicTableRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicTableVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicTableQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicTableVO> queryList(DynamicTableQueryListCommand dynamicTableQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicTablePageQueryCommand
	 * @return
	 */
	PageResponse<DynamicTableVO> pageQuery(DynamicTablePageQueryCommand dynamicTablePageQueryCommand);

    /**
     * 数据分页查询
     * @param dynamicTableDataPageQueryCommand
     * @return
     */
    public PageResponse<Map<String, Object>> dataPageQuery(DynamicTableDataPageQueryCommand dynamicTableDataPageQueryCommand);
}
