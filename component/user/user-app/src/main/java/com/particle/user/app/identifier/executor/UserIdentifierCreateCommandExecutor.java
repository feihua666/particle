package com.particle.user.app.identifier.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
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
 * 用户登录标识 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierGateway userIdentifierGateway;

	private UserIdentifierPwdGateway userIdentifierPwdGateway;
	/**
	 * 执行用户登录标识添加指令
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> execute(@Valid UserIdentifierCreateCommand userIdentifierCreateCommand,@Valid UserIdentifierPwdCommand userIdentifierPwdCommand) {
		UserIdentifier userIdentifier = createByUserIdentifierCreateCommand(userIdentifierCreateCommand);
		userIdentifier.changeIdentityTypeDictIdByValueIfNeccesary(userIdentifierCreateCommand.getIdentityTypeDictValue());
		boolean save = userIdentifierGateway.save(userIdentifier);
		if (save) {
			if (userIdentifierPwdCommand != null) {
				// 添加密码
				UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create(userIdentifier.getUserId(), userIdentifier.getId().getId(),
						userIdentifierPwdCommand.getPwdEncoded(),
						userIdentifierPwdCommand.getPwdEncryptFlag(),
						userIdentifierPwdCommand.getPwdComplexity(),
						userIdentifierPwdCommand.getIsPwdExpired(), userIdentifierPwdCommand.getPwdExpiredReason(), userIdentifierPwdCommand.getPwdExpireAt(),
						userIdentifierPwdCommand.getIsPwdNeedUpdate(), userIdentifierPwdCommand.getPwdNeedUpdateMessage());
				userIdentifierPwdGateway.save(userIdentifierPwd);
			}

			return SingleResponse.of(UserIdentifierAppStructMapping.instance.toUserIdentifierVO(userIdentifier));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 仅绑定登录标识
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> createBind(@Valid UserIdentifierCreateCommand userIdentifierCreateCommand) {
		return execute(userIdentifierCreateCommand, null);
	}

	/**
	 * 根据用户登录标识创建指令创建用户登录标识模型
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	private UserIdentifier createByUserIdentifierCreateCommand(UserIdentifierCreateCommand userIdentifierCreateCommand){
		UserIdentifier userIdentifier = UserIdentifier.create();
		UserIdentifierCreateCommandToUserIdentifierMapping.instance.fillUserIdentifierByUserIdentifierCreateCommand(userIdentifier, userIdentifierCreateCommand);
		return userIdentifier;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  UserIdentifierCreateCommandToUserIdentifierMapping{
		UserIdentifierCreateCommandToUserIdentifierMapping instance = Mappers.getMapper( UserIdentifierCreateCommandToUserIdentifierMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userIdentifier
		 * @param userIdentifierCreateCommand
		 */
		void fillUserIdentifierByUserIdentifierCreateCommand(@MappingTarget UserIdentifier userIdentifier, UserIdentifierCreateCommand userIdentifierCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierGateway
	 */
	@Autowired
	public void setUserIdentifierGateway(UserIdentifierGateway userIdentifierGateway) {
		this.userIdentifierGateway = userIdentifierGateway;
	}
	@Autowired
	public void setUserIdentifierPwdGateway(UserIdentifierPwdGateway userIdentifierPwdGateway) {
		this.userIdentifierPwdGateway = userIdentifierPwdGateway;
	}
}
