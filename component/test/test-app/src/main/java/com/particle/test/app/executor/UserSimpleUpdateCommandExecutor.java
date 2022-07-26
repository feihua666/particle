package com.particle.test.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.test.app.structmapping.UserSimpleAppStructMapping;
import com.particle.test.client.dto.command.UserSimpleUpdateCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.test.domain.gateway.UserSimpleGateway;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 简单用户 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class UserSimpleUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserSimpleGateway userSimpleGateway;

	/**
	 * 执行 简单用户 更新指令
	 * @param userSimpleUpdateCommand
	 * @return
	 */
	public SingleResponse<UserSimpleVO> execute(@Valid UserSimpleUpdateCommand userSimpleUpdateCommand) {
		UserSimple userSimple = createByUserSimpleUpdateCommand(userSimpleUpdateCommand);
		boolean save = userSimpleGateway.save(userSimple);
		if (save) {
			return SingleResponse.of(UserSimpleAppStructMapping.instance.toUserSimpleVO(userSimple));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param userSimpleUpdateCommand
	 * @return
	 */
	private UserSimple createByUserSimpleUpdateCommand(UserSimpleUpdateCommand userSimpleUpdateCommand){
		UserSimple userSimple = UserSimple.create();
		UserSimpleUpdateCommandToUserSimpleMapping.instance.fillUserSimpleByUserSimpleUpdateCommand(userSimple, userSimpleUpdateCommand);
		return userSimple;
	}

	@Mapper
	interface UserSimpleUpdateCommandToUserSimpleMapping{
		UserSimpleUpdateCommandToUserSimpleMapping instance = Mappers.getMapper(UserSimpleUpdateCommandToUserSimpleMapping.class );

		default UserSimpleId map(Long id){
			if (id == null) {
				return null;
			}
			return UserSimpleId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userSimple
		 * @param userSimpleUpdateCommand
		 */
		void fillUserSimpleByUserSimpleUpdateCommand(@MappingTarget UserSimple userSimple, UserSimpleUpdateCommand userSimpleUpdateCommand);
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
