package com.particle.user.app.identifier.executor;

import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdUpdateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
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
 * 用户密码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierPwdUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	/**
	 * 执行 用户密码 更新指令
	 * @param userIdentifierPwdUpdateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand) {
		UserIdentifierPwd userIdentifierPwd = createByUserIdentifierPwdUpdateCommand(userIdentifierPwdUpdateCommand);
		boolean save = userIdentifierPwdGateway.save(userIdentifierPwd);
		if (save) {
			return SingleResponse.of(UserIdentifierPwdAppStructMapping.instance.toUserIdentifierPwdVO(userIdentifierPwd));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param userIdentifierPwdUpdateCommand
	 * @return
	 */
	private UserIdentifierPwd createByUserIdentifierPwdUpdateCommand(UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand){
		UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create();
		UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping.instance.fillUserIdentifierPwdByUserIdentifierPwdUpdateCommand(userIdentifierPwd, userIdentifierPwdUpdateCommand);
		return userIdentifierPwd;
	}

	@Mapper
	interface UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping{
		UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping instance = Mappers.getMapper(UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping.class );

		default UserIdentifierPwdId map(Long id){
			if (id == null) {
				return null;
			}
			return UserIdentifierPwdId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userIdentifierPwd
		 * @param userIdentifierPwdUpdateCommand
		 */
		void fillUserIdentifierPwdByUserIdentifierPwdUpdateCommand(@MappingTarget UserIdentifierPwd userIdentifierPwd, UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand);
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
