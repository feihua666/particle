package com.particle.test.app.executor;

import com.particle.test.app.structmapping.UserSimpleAppStructMapping;
import com.particle.test.client.dto.command.UserSimpleDeleteCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.test.domain.gateway.UserSimpleGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
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
public class UserSimpleDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserSimpleGateway userSimpleGateway;

	/**
	 * 执行 简单用户 删除指令
	 * @param userSimpleDeleteCommand
	 * @return
	 */
	public SingleResponse<UserSimpleVO> execute(@Valid UserSimpleDeleteCommand userSimpleDeleteCommand) {
		UserSimpleId userSimpleId = UserSimpleId.of(userSimpleDeleteCommand.getId());
		UserSimple byId = userSimpleGateway.getById(userSimpleId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userSimpleGateway.delete(userSimpleId);
		if (delete) {
			return SingleResponse.of(UserSimpleAppStructMapping.instance.toUserSimpleVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
