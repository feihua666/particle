package com.particle.user.app.identifier.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 用户密码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierPwdQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserIdentifierPwdService iUserIdentifierPwdService;

	/**
	 * 执行 用户密码 列表查询指令
	 * @param userIdentifierPwdQueryListCommand
	 * @return
	 */
	public MultiResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand) {
		List<UserIdentifierPwdDO> userIdentifierPwdDO = iUserIdentifierPwdService.list(userIdentifierPwdQueryListCommand);
		List<UserIdentifierPwdVO> userIdentifierPwdVOs = UserIdentifierPwdAppStructMapping.instance.userIdentifierPwdDOsToUserIdentifierPwdVOs(userIdentifierPwdDO);
		return MultiResponse.of(userIdentifierPwdVOs);
	}
	/**
	 * 执行 用户密码 分页查询指令
	 * @param userIdentifierPwdPageQueryCommand
	 * @return
	 */
	public PageResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand) {
		Page<UserIdentifierPwdDO> page = iUserIdentifierPwdService.listPage(userIdentifierPwdPageQueryCommand);
		return UserIdentifierPwdAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户密码 展示用详情查询指令
	 * @param userIdentifierPwdQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> executeDetail(IdCommand userIdentifierPwdQueryDetailCommand) {
		UserIdentifierPwdDO byId = iUserIdentifierPwdService.getById(userIdentifierPwdQueryDetailCommand.getId());
		UserIdentifierPwdVO userIdentifierPwdVO = UserIdentifierPwdAppStructMapping.instance.userIdentifierPwdDOToUserIdentifierPwdVO(byId);
		return SingleResponse.of(userIdentifierPwdVO);
	}
	/**
	 * 执行 用户密码 更新用详情查询指令
	 * @param userIdentifierPwdQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> executeDetailForUpdate(IdCommand userIdentifierPwdQueryDetailForUpdateCommand) {
		UserIdentifierPwdDO byId = iUserIdentifierPwdService.getById(userIdentifierPwdQueryDetailForUpdateCommand.getId());
		UserIdentifierPwdVO userIdentifierPwdVO = UserIdentifierPwdAppStructMapping.instance.userIdentifierPwdDOToUserIdentifierPwdVO(byId);
		return SingleResponse.of(userIdentifierPwdVO);
	}

	@Autowired
	public void setIUserIdentifierPwdService(IUserIdentifierPwdService iUserIdentifierPwdService) {
		this.iUserIdentifierPwdService = iUserIdentifierPwdService;
	}
}
