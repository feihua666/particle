package com.particle.user.app.login.executor;

import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginRecordCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.gateway.UserLoginRecordGateway;
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
 * 用户登录记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginRecordGateway userLoginRecordGateway;

	/**
	 * 执行用户登录记录添加指令
	 * @param userLoginRecordCreateCommand
	 * @return
	 */
	public SingleResponse<UserLoginRecordVO> execute(@Valid UserLoginRecordCreateCommand userLoginRecordCreateCommand) {
		UserLoginRecord userLoginRecord = createByUserLoginRecordCreateCommand(userLoginRecordCreateCommand);
		boolean save = userLoginRecordGateway.save(userLoginRecord);
		if (save) {
			return SingleResponse.of(UserLoginRecordAppStructMapping.instance.toUserLoginRecordVO(userLoginRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户登录记录创建指令创建用户登录记录模型
	 * @param userLoginRecordCreateCommand
	 * @return
	 */
	private UserLoginRecord createByUserLoginRecordCreateCommand(UserLoginRecordCreateCommand userLoginRecordCreateCommand){
		UserLoginRecord userLoginRecord = UserLoginRecord.create();
		UserLoginRecordCreateCommandToUserLoginRecordMapping.instance.fillUserLoginRecordByUserLoginRecordCreateCommand(userLoginRecord, userLoginRecordCreateCommand);
		return userLoginRecord;
	}

	@Mapper
	interface  UserLoginRecordCreateCommandToUserLoginRecordMapping{
		UserLoginRecordCreateCommandToUserLoginRecordMapping instance = Mappers.getMapper( UserLoginRecordCreateCommandToUserLoginRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userLoginRecord
		 * @param userLoginRecordCreateCommand
		 */
		void fillUserLoginRecordByUserLoginRecordCreateCommand(@MappingTarget UserLoginRecord userLoginRecord, UserLoginRecordCreateCommand userLoginRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userLoginRecordGateway
	 */
	@Autowired
	public void setUserLoginRecordGateway(UserLoginRecordGateway userLoginRecordGateway) {
		this.userLoginRecordGateway = userLoginRecordGateway;
	}
}
