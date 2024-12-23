package com.particle.lowcode.client.generator.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;

/**
 * <p>
 * 低代码模型项目 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public interface ILowcodeModelItemApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param lowcodeModelItemCreateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelItemVO> create(LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<LowcodeModelItemVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param lowcodeModelItemUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelItemVO> update(LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand);

}
