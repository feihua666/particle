package com.particle.area.client.api.representation;

import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 区域 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
public interface IAreaRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param areaQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<AreaVO> queryDetailForUpdate(IdCommand areaQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param areaQueryDetailCommand
	 * @return
	 */
	SingleResponse<AreaVO> queryDetail(IdCommand areaQueryDetailCommand);

	/**
	 * 列表查询
	 * @param areaQueryListCommand
	 * @return
	 */
	MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand);

	/**
	 * 分页查询
	 * @param areaPageQueryCommand
	 * @return
	 */
	PageResponse<AreaVO> pageQuery(AreaPageQueryCommand areaPageQueryCommand);

}
