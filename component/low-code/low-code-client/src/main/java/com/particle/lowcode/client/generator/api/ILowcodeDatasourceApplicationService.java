package com.particle.lowcode.client.generator.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;

/**
 * <p>
 * 低代码数据源 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ILowcodeDatasourceApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param lowcodeDatasourceCreateCommand
	 * @return
	 */
	SingleResponse<LowcodeDatasourceVO> create(LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<LowcodeDatasourceVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param lowcodeDatasourceUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeDatasourceVO> update(LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand);

}
