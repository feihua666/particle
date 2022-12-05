package com.particle.test.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.client.dto.command.TestCreateCommand;
import com.particle.test.client.dto.command.TestUpdateCommand;
import com.particle.test.client.dto.data.TestVO;

/**
 * <p>
 * 测试 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public interface ITestApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param testCreateCommand
	 * @return
	 */
	SingleResponse<TestVO> create(TestCreateCommand testCreateCommand);

	/**
	 * 删除领域对象
	 * @param testDeleteCommand
	 * @return
	 */
	SingleResponse<TestVO> delete(IdCommand testDeleteCommand);

	/**
	 * 更新领域对象
	 * @param testUpdateCommand
	 * @return
	 */
	SingleResponse<TestVO> update(TestUpdateCommand testUpdateCommand);

}
