package com.particle.user.app.login.executor;

import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginRecordUpdateCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
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
 * 用户登录记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginRecordGateway userLoginRecordGateway;

	/**
	 * 执行 用户登录记录 更新指令
	 * @param userLoginRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<UserLoginRecordVO> execute(@Valid UserLoginRecordUpdateCommand userLoginRecordUpdateCommand) {
		UserLoginRecord userLoginRecord = createByUserLoginRecordUpdateCommand(userLoginRecordUpdateCommand);
		boolean save = userLoginRecordGateway.save(userLoginRecord);
		if (save) {
			return SingleResponse.of(UserLoginRecordAppStructMapping.instance.toUserLoginRecordVO(userLoginRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param userLoginRecordUpdateCommand
	 * @return
	 */
	private UserLoginRecord createByUserLoginRecordUpdateCommand(UserLoginRecordUpdateCommand userLoginRecordUpdateCommand){
		UserLoginRecord userLoginRecord = UserLoginRecord.create();
		UserLoginRecordUpdateCommandToUserLoginRecordMapping.instance.fillUserLoginRecordByUserLoginRecordUpdateCommand(userLoginRecord, userLoginRecordUpdateCommand);
		return userLoginRecord;
	}

	@Mapper
	interface UserLoginRecordUpdateCommandToUserLoginRecordMapping{
		UserLoginRecordUpdateCommandToUserLoginRecordMapping instance = Mappers.getMapper(UserLoginRecordUpdateCommandToUserLoginRecordMapping.class );

		default UserLoginRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return UserLoginRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userLoginRecord
		 * @param userLoginRecordUpdateCommand
		 */
		void fillUserLoginRecordByUserLoginRecordUpdateCommand(@MappingTarget UserLoginRecord userLoginRecord, UserLoginRecordUpdateCommand userLoginRecordUpdateCommand);
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
