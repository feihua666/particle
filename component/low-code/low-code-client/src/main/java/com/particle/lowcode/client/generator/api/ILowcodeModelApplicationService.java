package com.particle.lowcode.client.generator.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateByModelIdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;

/**
 * <p>
 * 低代码模型 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public interface ILowcodeModelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param lowcodeModelCreateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelVO> create(LowcodeModelCreateCommand lowcodeModelCreateCommand);

	/**
	 * 根据表名装载模型项
	 * @param idCommand
	 * @return
	 */
	Response loadByModelAndDatasource(LowcodeModelItemCreateByModelIdCommand idCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<LowcodeModelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param lowcodeModelUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelVO> update(LowcodeModelUpdateCommand lowcodeModelUpdateCommand);

}
