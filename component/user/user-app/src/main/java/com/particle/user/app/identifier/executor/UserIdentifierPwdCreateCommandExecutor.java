package com.particle.user.app.identifier.executor;

import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCreateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
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
 * 用户密码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierPwdCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	/**
	 * 执行用户密码添加指令
	 * @param userIdentifierPwdCreateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand) {
		UserIdentifierPwd userIdentifierPwd = createByUserIdentifierPwdCreateCommand(userIdentifierPwdCreateCommand);
		boolean save = userIdentifierPwdGateway.save(userIdentifierPwd);
		if (save) {
			return SingleResponse.of(UserIdentifierPwdAppStructMapping.instance.toUserIdentifierPwdVO(userIdentifierPwd));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户密码创建指令创建用户密码模型
	 * @param userIdentifierPwdCreateCommand
	 * @return
	 */
	private UserIdentifierPwd createByUserIdentifierPwdCreateCommand(UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand){
		UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create();
		UserIdentifierPwdCreateCommandToUserIdentifierPwdMapping.instance.fillUserIdentifierPwdByUserIdentifierPwdCreateCommand(userIdentifierPwd, userIdentifierPwdCreateCommand);
		return userIdentifierPwd;
	}

	@Mapper
	interface  UserIdentifierPwdCreateCommandToUserIdentifierPwdMapping{
		UserIdentifierPwdCreateCommandToUserIdentifierPwdMapping instance = Mappers.getMapper( UserIdentifierPwdCreateCommandToUserIdentifierPwdMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userIdentifierPwd
		 * @param userIdentifierPwdCreateCommand
		 */
		void fillUserIdentifierPwdByUserIdentifierPwdCreateCommand(@MappingTarget UserIdentifierPwd userIdentifierPwd, UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierPwdGateway
	 */
	@Autowired
	public void setUserIdentifierPwdGateway(UserIdentifierPwdGateway userIdentifierPwdGateway) {
		this.userIdentifierPwdGateway = userIdentifierPwdGateway;
	}
}
