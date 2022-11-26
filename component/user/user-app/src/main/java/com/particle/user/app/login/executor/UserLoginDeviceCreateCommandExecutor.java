package com.particle.user.app.login.executor;

import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginDeviceCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.gateway.UserLoginDeviceGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 用户登录设备 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginDeviceCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginDeviceGateway userLoginDeviceGateway;

	/**
	 * 执行用户登录设备添加指令
	 * @param userLoginDeviceCreateCommand
	 * @return
	 */
	public SingleResponse<UserLoginDeviceVO> execute(@Valid UserLoginDeviceCreateCommand userLoginDeviceCreateCommand) {
		UserLoginDevice userLoginDevice = createByUserLoginDeviceCreateCommand(userLoginDeviceCreateCommand);
		boolean save = userLoginDeviceGateway.save(userLoginDevice);
		if (save) {
			return SingleResponse.of(UserLoginDeviceAppStructMapping.instance.toUserLoginDeviceVO(userLoginDevice));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户登录设备创建指令创建用户登录设备模型
	 * @param userLoginDeviceCreateCommand
	 * @return
	 */
	private UserLoginDevice createByUserLoginDeviceCreateCommand(UserLoginDeviceCreateCommand userLoginDeviceCreateCommand){
		UserLoginDevice userLoginDevice = UserLoginDevice.create();
		UserLoginDeviceCreateCommandToUserLoginDeviceMapping.instance.fillUserLoginDeviceByUserLoginDeviceCreateCommand(userLoginDevice, userLoginDeviceCreateCommand);
		return userLoginDevice;
	}

	@Mapper
	interface  UserLoginDeviceCreateCommandToUserLoginDeviceMapping{
		UserLoginDeviceCreateCommandToUserLoginDeviceMapping instance = Mappers.getMapper( UserLoginDeviceCreateCommandToUserLoginDeviceMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userLoginDevice
		 * @param userLoginDeviceCreateCommand
		 */
		void fillUserLoginDeviceByUserLoginDeviceCreateCommand(@MappingTarget UserLoginDevice userLoginDevice, UserLoginDeviceCreateCommand userLoginDeviceCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userLoginDeviceGateway
	 */
	@Autowired
	public void setUserLoginDeviceGateway(UserLoginDeviceGateway userLoginDeviceGateway) {
		this.userLoginDeviceGateway = userLoginDeviceGateway;
	}
}
