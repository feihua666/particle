package com.particle.user.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.exception.ExceptionFactory;
import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierSimpleCreateCommand;
import com.particle.user.domain.User;
import com.particle.user.domain.gateway.UserGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserGateway userGateway;
	private UserIdentifierGateway userIdentifierGateway;
	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	private IUserIdentifierService userIdentifierService;

	/**
	 * 执行用户添加指令
	 * @param userCreateCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(@Valid UserCreateCommand userCreateCommand,@Valid UserIdentifierPwdCommand userIdentifierPwdCommand) {
		// 校验，登录标识是否存在
		if (CollectionUtil.isNotEmpty(userCreateCommand.getIdentifiers())) {
			List<String> identifiers = userCreateCommand.getIdentifiers().stream().map(UserIdentifierSimpleCreateCommand::getIdentifier).distinct().collect(Collectors.toList());
			List<UserIdentifierDO> byIdentifiers = userIdentifierService.getByIdentifiers(identifiers);
			if (CollectionUtil.isNotEmpty(byIdentifiers)) {
				throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, StrUtil.format("登录标识 {} 已存在", byIdentifiers.iterator().next().getIdentifier()));
			}
		}

		User user = createByUserCreateCommand(userCreateCommand);
		user.setAddControl(userCreateCommand);
		boolean save = userGateway.save(user);
		if (save) {
			// 添加成功添加账号
			UserIdentifier userIdentifier = null;

			for (UserIdentifierSimpleCreateCommand identifier : userCreateCommand.getIdentifiers()) {
				userIdentifier = UserIdentifier.create(
						user.getId().getId(),
						identifier.getIdentifier(),
						identifier.getIdentityTypeDictId(),
						userCreateCommand.getGroupFlag()
				);
				userIdentifier.changeIdentityTypeDictIdByValueIfNeccesary(identifier.getIdentityTypeDictValue());
			}
			boolean save1 = userIdentifierGateway.save(userIdentifier);
			if (save1) {
				// 添加密码
				UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create(user.getId().getId(), userIdentifier.getId().getId(),
						userIdentifierPwdCommand.getPwdEncoded(),
						userIdentifierPwdCommand.getPwdEncryptFlag(),
						userIdentifierPwdCommand.getPwdComplexity(),
						userIdentifierPwdCommand.getIsPwdExpired(), userIdentifierPwdCommand.getPwdExpiredReason(), userIdentifierPwdCommand.getPwdExpireAt(),
						userIdentifierPwdCommand.getIsPwdNeedUpdate(), userIdentifierPwdCommand.getPwdNeedUpdateMessage());
				userIdentifierPwdGateway.save(userIdentifierPwd);
			}
			return SingleResponse.of(UserAppStructMapping.instance.toUserVO(user));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户创建指令创建用户模型
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

	@Autowired
	public void setUserIdentifierGateway(UserIdentifierGateway userIdentifierGateway) {
		this.userIdentifierGateway = userIdentifierGateway;
	}
	@Autowired
	public void setUserIdentifierPwdGateway(UserIdentifierPwdGateway userIdentifierPwdGateway) {
		this.userIdentifierPwdGateway = userIdentifierPwdGateway;
	}
	@Autowired
	public void setUserIdentifierService(IUserIdentifierService userIdentifierService) {
		this.userIdentifierService = userIdentifierService;
	}
}
