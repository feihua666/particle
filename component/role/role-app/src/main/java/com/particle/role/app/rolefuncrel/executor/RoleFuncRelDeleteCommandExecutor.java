package com.particle.role.app.rolefuncrel.executor;

import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.RoleFuncRelId;
import com.particle.role.domain.rolefuncrel.gateway.RoleFuncRelGateway;
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
 * 角色菜单功能关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleFuncRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private RoleFuncRelGateway roleFuncRelGateway;

	/**
	 * 执行 角色菜单功能关系 删除指令
	 * @param roleFuncRelDeleteCommand
	 * @return
	 */
	public SingleResponse<RoleFuncRelVO> execute(@Valid IdCommand roleFuncRelDeleteCommand) {
		RoleFuncRelId roleFuncRelId = RoleFuncRelId.of(roleFuncRelDeleteCommand.getId());
		RoleFuncRel byId = roleFuncRelGateway.getById(roleFuncRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = roleFuncRelGateway.delete(roleFuncRelId);
		if (delete) {
			return SingleResponse.of(RoleFuncRelAppStructMapping.instance.toRoleFuncRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param roleFuncRelGateway
	 */
	@Autowired
	public void setRoleFuncRelGateway(RoleFuncRelGateway roleFuncRelGateway) {
		this.roleFuncRelGateway = roleFuncRelGateway;
	}
}
