package com.particle.user.app.executor.representation;

import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.dto.command.representation.UserExtraInfoQueryListCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import com.particle.user.client.dto.command.representation.UserExtraInfoPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户扩展信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
@Validated
public class UserExtraInfoQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserExtraInfoService iUserExtraInfoService;

	/**
	 * 执行 用户扩展信息 列表查询指令
	 * @param userExtraInfoQueryListCommand
	 * @return
	 */
	public MultiResponse<UserExtraInfoVO> execute(@Valid UserExtraInfoQueryListCommand userExtraInfoQueryListCommand) {
		List<UserExtraInfoDO> userExtraInfoDO = iUserExtraInfoService.list(userExtraInfoQueryListCommand);
		List<UserExtraInfoVO> userExtraInfoVOs = UserExtraInfoAppStructMapping.instance.userExtraInfoDOsToUserExtraInfoVOs(userExtraInfoDO);
		return MultiResponse.of(userExtraInfoVOs);
	}
	/**
	 * 执行 用户扩展信息 分页查询指令
	 * @param userExtraInfoPageQueryCommand
	 * @return
	 */
	public PageResponse<UserExtraInfoVO> execute(@Valid UserExtraInfoPageQueryCommand userExtraInfoPageQueryCommand) {
		Page<UserExtraInfoDO> page = iUserExtraInfoService.listPage(userExtraInfoPageQueryCommand);
		return UserExtraInfoAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户扩展信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<UserExtraInfoVO> executeDetail(IdCommand detailCommand) {
		UserExtraInfoDO byId = iUserExtraInfoService.getById(detailCommand.getId());
		UserExtraInfoVO userExtraInfoVO = UserExtraInfoAppStructMapping.instance.userExtraInfoDOToUserExtraInfoVO(byId);
		return SingleResponse.of(userExtraInfoVO);
	}
	/**
	 * 执行 用户扩展信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserExtraInfoVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		UserExtraInfoDO byId = iUserExtraInfoService.getById(detailForUpdateCommand.getId());
		UserExtraInfoVO userExtraInfoVO = UserExtraInfoAppStructMapping.instance.userExtraInfoDOToUserExtraInfoVO(byId);
		return SingleResponse.of(userExtraInfoVO);
	}


	@Autowired
	public void setIUserExtraInfoService(IUserExtraInfoService iUserExtraInfoService) {
		this.iUserExtraInfoService = iUserExtraInfoService;
	}
}
