package com.particle.test.client.api;

import com.particle.test.client.dto.command.UserSimpleCreateCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailForUpdateCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailCommand;
import com.particle.test.client.dto.command.UserSimpleDeleteCommand;
import com.particle.test.client.dto.command.UserSimpleUpdateCommand;
import com.particle.test.client.dto.command.UserSimplePageQueryCommand;
import com.particle.test.client.dto.command.UserSimpleQueryListCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 简单用户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public interface IUserSimpleApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userSimpleCreateCommand
	 * @return
	 */
	SingleResponse<UserSimpleVO> create(UserSimpleCreateCommand userSimpleCreateCommand);

	/**
	 * 删除领域对象
	 * @param userSimpleDeleteCommand
	 * @return
	 */
	SingleResponse<UserSimpleVO> delete(UserSimpleDeleteCommand userSimpleDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userSimpleUpdateCommand
	 * @return
	 */
	SingleResponse<UserSimpleVO> update(UserSimpleUpdateCommand userSimpleUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param userSimpleQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserSimpleVO> queryDetailForUpdate(UserSimpleQueryDetailForUpdateCommand userSimpleQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userSimpleQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserSimpleVO> queryDetail(UserSimpleQueryDetailCommand userSimpleQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userSimpleQueryListCommand
	 * @return
	 */
	MultiResponse<UserSimpleVO> queryList(UserSimpleQueryListCommand userSimpleQueryListCommand);

	/**
	 * 分页查询
	 * @param userSimplePageQueryCommand
	 * @return
	 */
	PageResponse<UserSimpleVO> pageQuery(UserSimplePageQueryCommand userSimplePageQueryCommand);

}
