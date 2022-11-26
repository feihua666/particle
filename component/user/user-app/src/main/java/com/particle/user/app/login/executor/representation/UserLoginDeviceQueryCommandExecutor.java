package com.particle.user.app.login.executor.representation;

import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailForUpdateCommand;
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
 * 用户登录设备 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginDeviceQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserLoginDeviceService iUserLoginDeviceService;

	/**
	 * 执行 用户登录设备 列表查询指令
	 * @param userLoginDeviceQueryListCommand
	 * @return
	 */
	public MultiResponse<UserLoginDeviceVO> execute(@Valid UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand) {
		List<UserLoginDeviceDO> userLoginDeviceDO = iUserLoginDeviceService.list(userLoginDeviceQueryListCommand);
		List<UserLoginDeviceVO> userLoginDeviceVOs = UserLoginDeviceAppStructMapping.instance.userLoginDeviceDOsToUserLoginDeviceVOs(userLoginDeviceDO);
		return MultiResponse.of(userLoginDeviceVOs);
	}
	/**
	 * 执行 用户登录设备 分页查询指令
	 * @param userLoginDevicePageQueryCommand
	 * @return
	 */
	public PageResponse<UserLoginDeviceVO> execute(@Valid UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand) {
		Page<UserLoginDeviceDO> page = iUserLoginDeviceService.listPage(userLoginDevicePageQueryCommand);
		return UserLoginDeviceAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户登录设备 展示用详情查询指令
	 * @param userLoginDeviceQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserLoginDeviceVO> execute(UserLoginDeviceQueryDetailCommand userLoginDeviceQueryDetailCommand) {
		UserLoginDeviceDO byId = iUserLoginDeviceService.getById(userLoginDeviceQueryDetailCommand.getId());
		UserLoginDeviceVO userLoginDeviceVO = UserLoginDeviceAppStructMapping.instance.userLoginDeviceDOToUserLoginDeviceVO(byId);
		return SingleResponse.of(userLoginDeviceVO);
	}
	/**
	 * 执行 用户登录设备 更新用详情查询指令
	 * @param userLoginDeviceQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserLoginDeviceVO> execute(UserLoginDeviceQueryDetailForUpdateCommand userLoginDeviceQueryDetailForUpdateCommand) {
		UserLoginDeviceDO byId = iUserLoginDeviceService.getById(userLoginDeviceQueryDetailForUpdateCommand.getId());
		UserLoginDeviceVO userLoginDeviceVO = UserLoginDeviceAppStructMapping.instance.userLoginDeviceDOToUserLoginDeviceVO(byId);
		return SingleResponse.of(userLoginDeviceVO);
	}

	@Autowired
	public void setIUserLoginDeviceService(IUserLoginDeviceService iUserLoginDeviceService) {
		this.iUserLoginDeviceService = iUserLoginDeviceService;
	}
}
