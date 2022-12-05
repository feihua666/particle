package com.particle.area.client.api;

import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;

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
	SingleResponse<AreaVO> delete(IdCommand areaDeleteCommand);

	/**
	 * 更新领域对象
	 * @param areaUpdateCommand
	 * @return
	 */
	SingleResponse<AreaVO> update(AreaUpdateCommand areaUpdateCommand);

}
