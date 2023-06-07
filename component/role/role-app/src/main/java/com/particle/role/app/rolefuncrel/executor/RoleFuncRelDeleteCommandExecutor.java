package com.particle.role.app.rolefuncrel.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.dto.response.Response;
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
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

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

	private IRoleFuncRelService iRoleFuncRelService;
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
	 * 根据 roleId 删除
	 * @param roleIdCommand
	 * @return
	 */
	public Response deleteByRoleId(@Valid IdCommand roleIdCommand) {
		boolean result = iRoleFuncRelService.deleteByColumn(roleIdCommand.getId(), RoleFuncRelDO::getRoleId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 funcId 删除
	 * @param funcIdCommand
	 * @return
	 */
	public Response deleteByFuncId(@Valid IdCommand funcIdCommand) {
		boolean result = iRoleFuncRelService.deleteByColumn(funcIdCommand.getId(), RoleFuncRelDO::getFuncId);
		return Response.buildSuccess();
	}

	/**
	 * 删除范围外的数据
	 * @param scopedFuncIds
	 * @return
	 */
	public Response deleteOutOfScopeByScopedFuncIds(List<Long> scopedFuncIds,Long tenantId) {
		if (CollectionUtil.isEmpty(scopedFuncIds)) {
			iRoleFuncRelService.remove(Wrappers.emptyWrapper());
			return Response.buildSuccess();
		}
		iRoleFuncRelService.remove(Wrappers.<RoleFuncRelDO>lambdaQuery().notIn(RoleFuncRelDO::getFuncId, scopedFuncIds).eq(RoleFuncRelDO::getTenantId,tenantId));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param roleFuncRelGateway
	 */
	@Autowired
	public void setRoleFuncRelGateway(RoleFuncRelGateway roleFuncRelGateway) {
		this.roleFuncRelGateway = roleFuncRelGateway;
	}

	@Autowired
	public void setiRoleFuncRelService(IRoleFuncRelService iRoleFuncRelService) {
		this.iRoleFuncRelService = iRoleFuncRelService;
	}
}
