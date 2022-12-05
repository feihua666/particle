package com.particle.test.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.client.dto.command.representation.TestPageQueryCommand;
import com.particle.test.client.dto.command.representation.TestQueryListCommand;
import com.particle.test.client.dto.data.TestVO;

/**
 * <p>
 * 测试 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public interface ITestRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param testQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<TestVO> queryDetailForUpdate(IdCommand testQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param testQueryDetailCommand
	 * @return
	 */
	SingleResponse<TestVO> queryDetail(IdCommand testQueryDetailCommand);

	/**
	 * 列表查询
	 * @param testQueryListCommand
	 * @return
	 */
	MultiResponse<TestVO> queryList(TestQueryListCommand testQueryListCommand);

	/**
	 * 分页查询
	 * @param testPageQueryCommand
	 * @return
	 */
	PageResponse<TestVO> pageQuery(TestPageQueryCommand testPageQueryCommand);

}
