package com.particle.user.app.executor;

import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.dto.command.UserExtraInfoUpdateCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.domain.UserExtraInfo;
import com.particle.user.domain.UserExtraInfoId;
import com.particle.user.domain.gateway.UserExtraInfoGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 用户扩展信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class UserExtraInfoUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserExtraInfoGateway userExtraInfoGateway;

	/**
	 * 执行 用户扩展信息 更新指令
	 * @param userExtraInfoUpdateCommand
	 * @return
	 */
	public SingleResponse<UserExtraInfoVO> execute(@Valid UserExtraInfoUpdateCommand userExtraInfoUpdateCommand) {
		UserExtraInfo userExtraInfo = createByUserExtraInfoUpdateCommand(userExtraInfoUpdateCommand);
		userExtraInfo.setUpdateControl(userExtraInfoUpdateCommand);
		boolean save = userExtraInfoGateway.save(userExtraInfo);
		if (save) {
			return SingleResponse.of(UserExtraInfoAppStructMapping.instance.toUserExtraInfoVO(userExtraInfo));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户扩展信息更新指令创建用户扩展信息模型
	 * @param userExtraInfoUpdateCommand
	 * @return
	 */
	private UserExtraInfo createByUserExtraInfoUpdateCommand(UserExtraInfoUpdateCommand userExtraInfoUpdateCommand){
		UserExtraInfo userExtraInfo = UserExtraInfo.create();
		UserExtraInfoUpdateCommandToUserExtraInfoMapping.instance.fillUserExtraInfoByUserExtraInfoUpdateCommand(userExtraInfo, userExtraInfoUpdateCommand);
		return userExtraInfo;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface UserExtraInfoUpdateCommandToUserExtraInfoMapping{
		UserExtraInfoUpdateCommandToUserExtraInfoMapping instance = Mappers.getMapper(UserExtraInfoUpdateCommandToUserExtraInfoMapping.class );

		default UserExtraInfoId map(Long id){
			if (id == null) {
				return null;
			}
			return UserExtraInfoId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userExtraInfo
		 * @param userExtraInfoUpdateCommand
		 */
		void fillUserExtraInfoByUserExtraInfoUpdateCommand(@MappingTarget UserExtraInfo userExtraInfo, UserExtraInfoUpdateCommand userExtraInfoUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userExtraInfoGateway
	 */
	@Autowired
	public void setUserExtraInfoGateway(UserExtraInfoGateway userExtraInfoGateway) {
		this.userExtraInfoGateway = userExtraInfoGateway;
	}
}
