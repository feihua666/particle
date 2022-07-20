package com.particle.user.app.executor;

import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.domain.User;
import com.particle.user.domain.gateway.UserGateway;
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
 * 后台管理用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class UserCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserGateway userGateway;

	/**
	 * 执行后台管理用户添加指令
	 * @param userCreateCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(@Valid UserCreateCommand userCreateCommand) {
		User user = createByUserCreateCommand(userCreateCommand);
		boolean save = userGateway.save(user);
		if (save) {
			return SingleResponse.of(UserAppStructMapping.instance.toUserVO(user));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据后台管理用户创建指令创建后台管理用户模型
	 * @param userCreateCommand
	 * @return
	 */
	private User createByUserCreateCommand(UserCreateCommand userCreateCommand){
		User user = User.create();
		UserCreateCommandToUserMapping.instance.fillUserByUserCreateCommand(user, userCreateCommand);
		return user;
	}

	@Mapper
	interface  UserCreateCommandToUserMapping{
		UserCreateCommandToUserMapping instance = Mappers.getMapper( UserCreateCommandToUserMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param user
		 * @param userCreateCommand
		 */
		void fillUserByUserCreateCommand(@MappingTarget User user, UserCreateCommand userCreateCommand);
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
