package com.particle.role.app.roleuserrel.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色用户关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleUserRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IRoleUserRelService iRoleUserRelService;

	/**
	 * 执行 角色用户关系 列表查询指令
	 * @param roleUserRelQueryListCommand
	 * @return
	 */
	public MultiResponse<RoleUserRelVO> execute(@Valid RoleUserRelQueryListCommand roleUserRelQueryListCommand) {
		List<RoleUserRelDO> roleUserRelDO = iRoleUserRelService.list(roleUserRelQueryListCommand);
		List<RoleUserRelVO> roleUserRelVOs = RoleUserRelAppStructMapping.instance.roleUserRelDOsToRoleUserRelVOs(roleUserRelDO);
		return MultiResponse.of(roleUserRelVOs);
	}
	/**
	 * 执行 角色用户关系 分页查询指令
	 * @param roleUserRelPageQueryCommand
	 * @return
	 */
	public PageResponse<RoleUserRelVO> execute(@Valid RoleUserRelPageQueryCommand roleUserRelPageQueryCommand) {
		Page<RoleUserRelDO> page = iRoleUserRelService.listPage(roleUserRelPageQueryCommand);
		return RoleUserRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 角色用户关系 展示用详情查询指令
	 * @param roleUserRelQueryDetailCommand
	 * @return
	 */
	public SingleResponse<RoleUserRelVO> executeDetail(IdCommand roleUserRelQueryDetailCommand) {
		RoleUserRelDO byId = iRoleUserRelService.getById(roleUserRelQueryDetailCommand.getId());
		RoleUserRelVO roleUserRelVO = RoleUserRelAppStructMapping.instance.roleUserRelDOToRoleUserRelVO(byId);
		return SingleResponse.of(roleUserRelVO);
	}
	/**
	 * 执行 角色用户关系 更新用详情查询指令
	 * @param roleUserRelQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleUserRelVO> executeDetailForUpdate(IdCommand roleUserRelQueryDetailForUpdateCommand) {
		RoleUserRelDO byId = iRoleUserRelService.getById(roleUserRelQueryDetailForUpdateCommand.getId());
		RoleUserRelVO roleUserRelVO = RoleUserRelAppStructMapping.instance.roleUserRelDOToRoleUserRelVO(byId);
		return SingleResponse.of(roleUserRelVO);
	}

	@Autowired
	public void setIRoleUserRelService(IRoleUserRelService iRoleUserRelService) {
		this.iRoleUserRelService = iRoleUserRelService;
	}
}
