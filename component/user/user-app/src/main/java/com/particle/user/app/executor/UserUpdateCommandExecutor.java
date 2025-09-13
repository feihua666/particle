package com.particle.user.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.dto.command.*;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierSimpleCreateCommand;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.user.domain.gateway.UserGateway;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

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
	private IUserExtraInfoService userExtraInfoService;
	private UserExtraInfoCreateCommandExecutor userExtraInfoCreateCommandExecutor;
	private UserExtraInfoUpdateCommandExecutor userExtraInfoUpdateCommandExecutor;

	private IUserIdentifierService userIdentifierService;
	private UserIdentifierGateway userIdentifierGateway;
	/**
	 * 执行 用户 更新指令
	 * @param userUpdateCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(@Valid UserUpdateCommand userUpdateCommand) {
		List<UserIdentifierDO> byIdentifiers = null;
		// 校验，登录标识是否存在
		if (CollectionUtil.isNotEmpty(userUpdateCommand.getIdentifiers())) {
			List<String> identifiers = userUpdateCommand.getIdentifiers().stream().map(UserIdentifierSimpleCreateCommand::getIdentifier).distinct().collect(Collectors.toList());
			if (identifiers.size() < userUpdateCommand.getIdentifiers().size()) {
				throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, StrUtil.format("登录标识存在重复"));
			}
			byIdentifiers = userIdentifierService.getByIdentifiers(identifiers);
			if (CollectionUtil.isNotEmpty(byIdentifiers)) {
				long otherUserCount = byIdentifiers.stream().filter(userIdentifierDO -> !userIdentifierDO.getUserId().equals(userUpdateCommand.getId())).count();
				if (otherUserCount > 0) {
					throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, StrUtil.format("登录标识 {} 已存在", byIdentifiers.iterator().next().getIdentifier()));
				}
			}
		}
		User user = createByUserUpdateCommand(userUpdateCommand);
		user.setUpdateControl(userUpdateCommand);
		boolean save = userGateway.save(user);
		if (save) {
			// 修改成功，添加或更新账号
			if (CollectionUtil.isNotEmpty(userUpdateCommand.getIdentifiers())) {
				for (UserIdentifierSimpleCreateCommand identifier : userUpdateCommand.getIdentifiers()) {
					UserIdentifier userIdentifierTemp = UserIdentifier.create(
							user.getId().getId(),
							identifier.getIdentifier(),
							identifier.getIdentityTypeDictId(),
							userUpdateCommand.getGroupFlag()
					);
					if (CollectionUtil.isNotEmpty(byIdentifiers)) {
						for (UserIdentifierDO byIdentifier : byIdentifiers) {
                            if (byIdentifier.getIdentifier().equals(identifier.getIdentifier())) {
								userIdentifierTemp.changeId(byIdentifier.getId());
								userIdentifierTemp.changeVersionTo(byIdentifier.getVersion());
                            }
						}
					}

					userIdentifierTemp.changeIdentityTypeDictIdByValueIfNeccesary(identifier.getIdentityTypeDictValue());
					userIdentifierGateway.save(userIdentifierTemp);
				}
			}


			// 更新扩展信息
			UserExtraInfoCommand userExtraInfoCommand = userUpdateCommand.getUserExtraInfo();
			if (userExtraInfoCommand != null) {
				UserExtraInfoDO userExtraInfoDO = userExtraInfoService.getByUserId(user.getId().getId());
				// 如果不存在的话
				if (userExtraInfoDO == null) {
					// 添加扩展信息

					UserExtraInfoCreateCommand userExtraInfoCreateCommand = UserExtraInfoCreateCommand.createByUserExtraInfoCommand(userExtraInfoCommand, user.getId().getId());
					userExtraInfoCreateCommandExecutor.execute(userExtraInfoCreateCommand);
				}else{
					UserExtraInfoUpdateCommand userExtraInfoUpdateCommand = UserExtraInfoUpdateCommand.createByUserExtraInfoCommand(userExtraInfoCommand,
							userExtraInfoDO.getUserId(), userExtraInfoDO.getId(), userExtraInfoDO.getVersion());
					userExtraInfoUpdateCommandExecutor.execute(userExtraInfoUpdateCommand);
				}

			}
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
	@Autowired
	public void setUserExtraInfoService(IUserExtraInfoService userExtraInfoService) {
		this.userExtraInfoService = userExtraInfoService;
	}
	@Autowired
	public void setUserExtraInfoCreateCommandExecutor(UserExtraInfoCreateCommandExecutor userExtraInfoCreateCommandExecutor) {
		this.userExtraInfoCreateCommandExecutor = userExtraInfoCreateCommandExecutor;
	}
	@Autowired
	public void setUserExtraInfoUpdateCommandExecutor(UserExtraInfoUpdateCommandExecutor userExtraInfoUpdateCommandExecutor) {
		this.userExtraInfoUpdateCommandExecutor = userExtraInfoUpdateCommandExecutor;
	}
	@Autowired
	public void setUserIdentifierService(IUserIdentifierService userIdentifierService) {
		this.userIdentifierService = userIdentifierService;
	}
	@Autowired
	public void setUserIdentifierGateway(UserIdentifierGateway userIdentifierGateway) {
		this.userIdentifierGateway = userIdentifierGateway;
	}
}
