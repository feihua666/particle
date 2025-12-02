package com.particle.data.client.dynamicdata.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryDataPageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;

import java.util.Map;

/**
 * <p>
 * 动态数据指标分类 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicDataIndicatorCategoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicDataIndicatorCategoryQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicDataIndicatorCategoryVO> queryList(DynamicDataIndicatorCategoryQueryListCommand dynamicDataIndicatorCategoryQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicDataIndicatorCategoryPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicDataIndicatorCategoryVO> pageQuery(DynamicDataIndicatorCategoryPageQueryCommand dynamicDataIndicatorCategoryPageQueryCommand);


    /**
     * 数据分页查询
     * @param dynamicDataIndicatorCategoryDataPageQueryCommand
     * @return
     */
    public PageResponse<Map<String, Object>> dataPageQuery(DynamicDataIndicatorCategoryDataPageQueryCommand dynamicDataIndicatorCategoryDataPageQueryCommand);
}
