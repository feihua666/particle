package com.particle.func.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能组 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
public interface IFuncGroupApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param funcGroupCreateCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> create(FuncGroupCreateCommand funcGroupCreateCommand);

	/**
	 * 删除领域对象
	 * @param funcGroupDeleteCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> delete(IdCommand funcGroupDeleteCommand);

	/**
	 * 更新领域对象
	 * @param funcGroupUpdateCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> update(FuncGroupUpdateCommand funcGroupUpdateCommand);

}
