package com.particle.data.client.dynamicdata.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorWithDynamicTableFieldVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;

/**
 * <p>
 * 动态数据指标 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicDataIndicatorRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicDataIndicatorQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicDataIndicatorVO> queryList(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand);
    /**
	 * 列表查询
	 * @param dynamicDataIndicatorQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicDataIndicatorWithDynamicTableFieldVO> queryListWithDynamicTableField(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicDataIndicatorPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicDataIndicatorVO> pageQuery(DynamicDataIndicatorPageQueryCommand dynamicDataIndicatorPageQueryCommand);

}
