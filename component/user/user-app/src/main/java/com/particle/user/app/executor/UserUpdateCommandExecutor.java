package com.particle.user.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.UserUpdateInfoCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.user.domain.gateway.UserGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserGateway userGateway;

	/**
	 * 执行 用户 更新指令
	 * @param userUpdateCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(@Valid UserUpdateCommand userUpdateCommand) {
		User user = createByUserUpdateCommand(userUpdateCommand);
		user.setUpdateControl(userUpdateCommand);
		boolean save = userGateway.save(user);
		if (save) {
			return SingleResponse.of(UserAppStructMapping.instance.toUserVO(user));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 单纯更新用户信息
	 * @param userUpdateInfoCommand
	 * @return
	 */
	public Response updateUserInfo(@Valid UserUpdateInfoCommand userUpdateInfoCommand) {
		User user = createByUserUpdateInfoCommand(userUpdateInfoCommand);
		user.setUpdateControl(userUpdateInfoCommand);
		boolean save = userGateway.save(user);
		if (save) {
			return Response.buildSuccess();
		}
		return Response.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户简单更新指令创建用户模型
	 * @param userUpdateInfoCommand
	 * @return
	 */
	private User createByUserUpdateInfoCommand(UserUpdateInfoCommand userUpdateInfoCommand){
		User user = User.create();
		UserUpdateCommandToUserMapping.instance.fillUserByUserUpdateInfoCommand(user, userUpdateInfoCommand);
		return user;
	}
	/**
	 * 根据用户更新指令创建用户模型
	 * @param userUpdateCommand
	 * @return
	 */
	private User createByUserUpdateCommand(UserUpdateCommand userUpdateCommand){
		User user = User.create();
		UserUpdateCommandToUserMapping.instance.fillUserByUserUpdateCommand(user, userUpdateCommand);
		return user;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface UserUpdateCommandToUserMapping{
		UserUpdateCommandToUserMapping instance = Mappers.getMapper(UserUpdateCommandToUserMapping.class );

		default UserId map(Long id){
			if (id == null) {
				return null;
			}
			return UserId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param user
		 * @param userUpdateCommand
		 */
		void fillUserByUserUpdateCommand(@MappingTarget User user, UserUpdateCommand userUpdateCommand);
		void fillUserByUserUpdateInfoCommand(@MappingTarget User user, UserUpdateInfoCommand userUpdateInfoCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userGateway
	 */
	@Autowired
	public void setUserGateway(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
}
