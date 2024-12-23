package com.particle.user.app.identifier.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
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
 * 用户登录标识 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierGateway userIdentifierGateway;

	/**
	 * 执行 用户登录标识 更新指令
	 * @param userIdentifierUpdateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> execute(@Valid UserIdentifierUpdateCommand userIdentifierUpdateCommand) {
		UserIdentifier userIdentifier = createByUserIdentifierUpdateCommand(userIdentifierUpdateCommand);
		boolean save = userIdentifierGateway.save(userIdentifier);
		if (save) {
			return SingleResponse.of(UserIdentifierAppStructMapping.instance.toUserIdentifierVO(userIdentifier));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户登录标识更新指令创建用户登录标识模型
	 * @param userIdentifierUpdateCommand
	 * @return
	 */
	private UserIdentifier createByUserIdentifierUpdateCommand(UserIdentifierUpdateCommand userIdentifierUpdateCommand){
		UserIdentifier userIdentifier = UserIdentifier.create();
		UserIdentifierUpdateCommandToUserIdentifierMapping.instance.fillUserIdentifierByUserIdentifierUpdateCommand(userIdentifier, userIdentifierUpdateCommand);
		return userIdentifier;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface UserIdentifierUpdateCommandToUserIdentifierMapping{
		UserIdentifierUpdateCommandToUserIdentifierMapping instance = Mappers.getMapper(UserIdentifierUpdateCommandToUserIdentifierMapping.class );

		default UserIdentifierId map(Long id){
			if (id == null) {
				return null;
			}
			return UserIdentifierId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userIdentifier
		 * @param userIdentifierUpdateCommand
		 */
		void fillUserIdentifierByUserIdentifierUpdateCommand(@MappingTarget UserIdentifier userIdentifier, UserIdentifierUpdateCommand userIdentifierUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierGateway
	 */
	@Autowired
	public void setUserIdentifierGateway(UserIdentifierGateway userIdentifierGateway) {
		this.userIdentifierGateway = userIdentifierGateway;
	}
}
