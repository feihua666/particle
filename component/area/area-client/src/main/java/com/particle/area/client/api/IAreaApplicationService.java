package com.particle.area.client.api;

import com.particle.area.client.dto.command.CreateAreaCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

import javax.validation.Valid;

/**
 * <p>
 * 区域 应用门面服务类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-27 19:52
 */
public interface IAreaApplicationService extends IBaseApplicationService {

	/**
	 * 添加/创建一个领域对象
	 * @param createAreaCommand
	 * @return
	 */
	SingleResponse<AreaVO> create(CreateAreaCommand createAreaCommand);

}
