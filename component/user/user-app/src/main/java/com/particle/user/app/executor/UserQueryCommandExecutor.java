package com.particle.user.app.executor;

import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.dto.command.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.service.IUserService;
import com.particle.user.client.dto.command.UserPageQueryCommand;
import com.particle.user.client.dto.command.UserQueryDetailCommand;
import com.particle.user.client.dto.command.UserQueryDetailForUpdateCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 后台管理用户 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class UserQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserService iUserService;

	/**
	 * 执行 后台管理用户 列表查询指令
	 * @param userQueryListCommand
	 * @return
	 */
	public MultiResponse<UserVO> execute(@Valid UserQueryListCommand userQueryListCommand) {
		List<UserDO> userDO = iUserService.list(userQueryListCommand);
		List<UserVO> userVOs = UserAppStructMapping.instance.userDOsToUserVOs(userDO);
		return MultiResponse.of(userVOs);
	}
	/**
	 * 执行 后台管理用户 分页查询指令
	 * @param userPageQueryCommand
	 * @return
	 */
	public PageResponse<UserVO> execute(@Valid UserPageQueryCommand userPageQueryCommand) {
		Page<UserDO> page = iUserService.listPage(userPageQueryCommand);
		return UserAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 后台管理用户 展示用详情查询指令
	 * @param userQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(UserQueryDetailCommand userQueryDetailCommand) {
		UserDO byId = iUserService.getById(userQueryDetailCommand.getId());
		UserVO userVO = UserAppStructMapping.instance.userDOToUserVO(byId);
		return SingleResponse.of(userVO);
	}
	/**
	 * 执行 后台管理用户 更新用详情查询指令
	 * @param userQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(UserQueryDetailForUpdateCommand userQueryDetailForUpdateCommand) {
		UserDO byId = iUserService.getById(userQueryDetailForUpdateCommand.getId());
		UserVO userVO = UserAppStructMapping.instance.userDOToUserVO(byId);
		return SingleResponse.of(userVO);
	}

	@Autowired
	public void setIUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}
}
