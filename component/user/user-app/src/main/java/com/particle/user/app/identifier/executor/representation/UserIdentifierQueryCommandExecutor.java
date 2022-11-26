package com.particle.user.app.identifier.executor.representation;

import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailForUpdateCommand;
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
 * 用户登录标识 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserIdentifierService iUserIdentifierService;

	/**
	 * 执行 用户登录标识 列表查询指令
	 * @param userIdentifierQueryListCommand
	 * @return
	 */
	public MultiResponse<UserIdentifierVO> execute(@Valid UserIdentifierQueryListCommand userIdentifierQueryListCommand) {
		List<UserIdentifierDO> userIdentifierDO = iUserIdentifierService.list(userIdentifierQueryListCommand);
		List<UserIdentifierVO> userIdentifierVOs = UserIdentifierAppStructMapping.instance.userIdentifierDOsToUserIdentifierVOs(userIdentifierDO);
		return MultiResponse.of(userIdentifierVOs);
	}
	/**
	 * 执行 用户登录标识 分页查询指令
	 * @param userIdentifierPageQueryCommand
	 * @return
	 */
	public PageResponse<UserIdentifierVO> execute(@Valid UserIdentifierPageQueryCommand userIdentifierPageQueryCommand) {
		Page<UserIdentifierDO> page = iUserIdentifierService.listPage(userIdentifierPageQueryCommand);
		return UserIdentifierAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户登录标识 展示用详情查询指令
	 * @param userIdentifierQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> execute(UserIdentifierQueryDetailCommand userIdentifierQueryDetailCommand) {
		UserIdentifierDO byId = iUserIdentifierService.getById(userIdentifierQueryDetailCommand.getId());
		UserIdentifierVO userIdentifierVO = UserIdentifierAppStructMapping.instance.userIdentifierDOToUserIdentifierVO(byId);
		return SingleResponse.of(userIdentifierVO);
	}
	/**
	 * 执行 用户登录标识 更新用详情查询指令
	 * @param userIdentifierQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> execute(UserIdentifierQueryDetailForUpdateCommand userIdentifierQueryDetailForUpdateCommand) {
		UserIdentifierDO byId = iUserIdentifierService.getById(userIdentifierQueryDetailForUpdateCommand.getId());
		UserIdentifierVO userIdentifierVO = UserIdentifierAppStructMapping.instance.userIdentifierDOToUserIdentifierVO(byId);
		return SingleResponse.of(userIdentifierVO);
	}

	@Autowired
	public void setIUserIdentifierService(IUserIdentifierService iUserIdentifierService) {
		this.iUserIdentifierService = iUserIdentifierService;
	}
}
