package com.particle.role.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IRoleService iRoleService;

	/**
	 * 执行 角色 列表查询指令
	 * @param roleQueryListCommand
	 * @return
	 */
	public MultiResponse<RoleVO> execute(@Valid RoleQueryListCommand roleQueryListCommand) {
		List<RoleDO> roleDO = iRoleService.list(roleQueryListCommand);
		List<RoleVO> roleVOs = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDO);
		return MultiResponse.of(roleVOs);
	}
	/**
	 * 执行 角色 分页查询指令
	 * @param rolePageQueryCommand
	 * @return
	 */
	public PageResponse<RoleVO> execute(@Valid RolePageQueryCommand rolePageQueryCommand) {
		Page<RoleDO> page = iRoleService.listPage(rolePageQueryCommand);
		return RoleAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 角色 展示用详情查询指令
	 * @param roleQueryDetailCommand
	 * @return
	 */
	public SingleResponse<RoleVO> executeDetail(IdCommand roleQueryDetailCommand) {
		RoleDO byId = iRoleService.getById(roleQueryDetailCommand.getId());
		RoleVO roleVO = RoleAppStructMapping.instance.roleDOToRoleVO(byId);
		return SingleResponse.of(roleVO);
	}
	/**
	 * 执行 角色 更新用详情查询指令
	 * @param roleQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleVO> executeDetailForUpdate(IdCommand roleQueryDetailForUpdateCommand) {
		RoleDO byId = iRoleService.getById(roleQueryDetailForUpdateCommand.getId());
		RoleVO roleVO = RoleAppStructMapping.instance.roleDOToRoleVO(byId);
		return SingleResponse.of(roleVO);
	}

	@Autowired
	public void setIRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}
}
