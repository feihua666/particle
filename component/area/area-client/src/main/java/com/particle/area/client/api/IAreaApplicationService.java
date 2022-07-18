package com.particle.area.client.api;

import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaQueryDetailForUpdateCommand;
import com.particle.area.client.dto.command.AreaQueryDetailCommand;
import com.particle.area.client.dto.command.AreaDeleteCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.command.AreaPageQueryCommand;
import com.particle.area.client.dto.command.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 区域 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
public interface IAreaApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param areaCreateCommand
	 * @return
	 */
	SingleResponse<AreaVO> create(AreaCreateCommand areaCreateCommand);

	/**
	 * 删除领域对象
	 * @param areaDeleteCommand
	 * @return
	 */
	SingleResponse<AreaVO> delete(AreaDeleteCommand areaDeleteCommand);

	/**
	 * 更新领域对象
	 * @param areaUpdateCommand
	 * @return
	 */
	SingleResponse<AreaVO> update(AreaUpdateCommand areaUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param areaQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<AreaVO> queryDetailForUpdate(AreaQueryDetailForUpdateCommand areaQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param areaQueryDetailCommand
	 * @return
	 */
	SingleResponse<AreaVO> queryDetail(AreaQueryDetailCommand areaQueryDetailCommand);

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
