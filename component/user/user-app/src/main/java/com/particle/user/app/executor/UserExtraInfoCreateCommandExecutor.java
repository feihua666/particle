package com.particle.user.app.executor;

import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.dto.command.UserExtraInfoCreateCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.domain.UserExtraInfo;
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
 * 用户扩展信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
@Validated
public class UserExtraInfoCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserExtraInfoGateway userExtraInfoGateway;

	/**
	 * 执行用户扩展信息添加指令
	 * @param userExtraInfoCreateCommand
	 * @return
	 */
	public SingleResponse<UserExtraInfoVO> execute(@Valid UserExtraInfoCreateCommand userExtraInfoCreateCommand) {
		UserExtraInfo userExtraInfo = createByUserExtraInfoCreateCommand(userExtraInfoCreateCommand);
		userExtraInfo.setAddControl(userExtraInfoCreateCommand);
		boolean save = userExtraInfoGateway.save(userExtraInfo);
		if (save) {
			return SingleResponse.of(UserExtraInfoAppStructMapping.instance.toUserExtraInfoVO(userExtraInfo));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户扩展信息创建指令创建用户扩展信息模型
	 * @param userExtraInfoCreateCommand
	 * @return
	 */
	private UserExtraInfo createByUserExtraInfoCreateCommand(UserExtraInfoCreateCommand userExtraInfoCreateCommand){
		UserExtraInfo userExtraInfo = UserExtraInfo.create();
		UserExtraInfoCreateCommandToUserExtraInfoMapping.instance.fillUserExtraInfoByUserExtraInfoCreateCommand(userExtraInfo, userExtraInfoCreateCommand);
		return userExtraInfo;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  UserExtraInfoCreateCommandToUserExtraInfoMapping{
		UserExtraInfoCreateCommandToUserExtraInfoMapping instance = Mappers.getMapper( UserExtraInfoCreateCommandToUserExtraInfoMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userExtraInfo
		 * @param userExtraInfoCreateCommand
		 */
		void fillUserExtraInfoByUserExtraInfoCreateCommand(@MappingTarget UserExtraInfo userExtraInfo, UserExtraInfoCreateCommand userExtraInfoCreateCommand);
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
