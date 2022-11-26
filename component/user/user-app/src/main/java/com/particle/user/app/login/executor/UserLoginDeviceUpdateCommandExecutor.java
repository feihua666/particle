package com.particle.user.app.login.executor;

import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginDeviceUpdateCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
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
 * 用户登录设备 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginDeviceUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginDeviceGateway userLoginDeviceGateway;

	/**
	 * 执行 用户登录设备 更新指令
	 * @param userLoginDeviceUpdateCommand
	 * @return
	 */
	public SingleResponse<UserLoginDeviceVO> execute(@Valid UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand) {
		UserLoginDevice userLoginDevice = createByUserLoginDeviceUpdateCommand(userLoginDeviceUpdateCommand);
		boolean save = userLoginDeviceGateway.save(userLoginDevice);
		if (save) {
			return SingleResponse.of(UserLoginDeviceAppStructMapping.instance.toUserLoginDeviceVO(userLoginDevice));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param userLoginDeviceUpdateCommand
	 * @return
	 */
	private UserLoginDevice createByUserLoginDeviceUpdateCommand(UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand){
		UserLoginDevice userLoginDevice = UserLoginDevice.create();
		UserLoginDeviceUpdateCommandToUserLoginDeviceMapping.instance.fillUserLoginDeviceByUserLoginDeviceUpdateCommand(userLoginDevice, userLoginDeviceUpdateCommand);
		return userLoginDevice;
	}

	@Mapper
	interface UserLoginDeviceUpdateCommandToUserLoginDeviceMapping{
		UserLoginDeviceUpdateCommandToUserLoginDeviceMapping instance = Mappers.getMapper(UserLoginDeviceUpdateCommandToUserLoginDeviceMapping.class );

		default UserLoginDeviceId map(Long id){
			if (id == null) {
				return null;
			}
			return UserLoginDeviceId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userLoginDevice
		 * @param userLoginDeviceUpdateCommand
		 */
		void fillUserLoginDeviceByUserLoginDeviceUpdateCommand(@MappingTarget UserLoginDevice userLoginDevice, UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand);
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
