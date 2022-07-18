package com.particle.test.app.executor;

import com.particle.test.app.structmapping.UserSimpleAppStructMapping;
import com.particle.test.client.dto.command.UserSimpleCreateCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.test.domain.gateway.UserSimpleGateway;
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
 * 简单用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class UserSimpleCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserSimpleGateway userSimpleGateway;

	/**
	 * 执行简单用户添加指令
	 * @param userSimpleCreateCommand
	 * @return
	 */
	public SingleResponse<UserSimpleVO> execute(@Valid UserSimpleCreateCommand userSimpleCreateCommand) {
		UserSimple userSimple = createByUserSimpleCreateCommand(userSimpleCreateCommand);
		boolean save = userSimpleGateway.save(userSimple);
		if (save) {
			userSimple = userSimpleGateway.getById(userSimple.getId());
			return SingleResponse.of(UserSimpleAppStructMapping.instance.toUserSimpleVO(userSimple));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据简单用户创建指令创建简单用户模型
	 * @param userSimpleCreateCommand
	 * @return
	 */
	private UserSimple createByUserSimpleCreateCommand(UserSimpleCreateCommand userSimpleCreateCommand){
		UserSimple userSimple = UserSimple.create();
		UserSimpleCreateCommandToUserSimpleMapping.instance.fillUserSimpleByUserSimpleCreateCommand(userSimple, userSimpleCreateCommand);
		return userSimple;
	}

	@Mapper
	interface  UserSimpleCreateCommandToUserSimpleMapping{
		UserSimpleCreateCommandToUserSimpleMapping instance = Mappers.getMapper( UserSimpleCreateCommandToUserSimpleMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userSimple
		 * @param userSimpleCreateCommand
		 */
		void fillUserSimpleByUserSimpleCreateCommand(@MappingTarget UserSimple userSimple, UserSimpleCreateCommand userSimpleCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userSimpleGateway
	 */
	@Autowired
	public void setUserSimpleGateway(UserSimpleGateway userSimpleGateway) {
		this.userSimpleGateway = userSimpleGateway;
	}
}
