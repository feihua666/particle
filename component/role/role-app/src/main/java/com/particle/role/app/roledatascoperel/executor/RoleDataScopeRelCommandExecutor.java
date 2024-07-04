package com.particle.role.app.roledatascoperel.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.particle.role.domain.roledatascoperel.gateway.RoleDataScopeRelGateway;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.client.roledatascoperel.dto.command.RoleAssignDataScopeCommand;
import com.particle.role.client.roledatascoperel.dto.command.DataScopeAssignRoleCommand;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 角色数据范围关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
@Validated
public class RoleDataScopeRelCommandExecutor  extends AbstractBaseExecutor {

	private RoleDataScopeRelGateway roleDataScopeRelGateway;
	private IRoleDataScopeRelService iRoleDataScopeRelService;
	/**
	 * 角色分配数据范围
	 * @param roleAssignDataScopeCommand
	 * @return
	 */
	public Response roleAssignDataScope(@Valid RoleAssignDataScopeCommand roleAssignDataScopeCommand) {
		// 每一个数据对象只能选择一个数据范围
		Map<Long, Long> checkedDataScopeIdAgainstDataObjectIds = roleAssignDataScopeCommand.getCheckedDataScopeIdAgainstDataObjectIds();
		List<Long> checkedDataScopeIds = roleAssignDataScopeCommand.getCheckedDataScopeIds();
		if (CollectionUtil.isNotEmpty(checkedDataScopeIdAgainstDataObjectIds) && CollectionUtil.isNotEmpty(checkedDataScopeIds)) {
			Set<Long> dataObjectIds = new HashSet<>();
			for (Long checkedDataScopeId : checkedDataScopeIds) {
				Long dataObjectId = roleAssignDataScopeCommand.fetchDataObjectIdByDataScopeId(checkedDataScopeId);

				Assert.isFalse(dataObjectIds.contains(dataObjectId),"每一个数据对象只能选择一个数据范围");

				dataObjectIds.add(dataObjectId);

			}
		}

		boolean result = iRoleDataScopeRelService.removeAndAssignRel(roleAssignDataScopeCommand.getRoleId(),
				checkedDataScopeIds,roleAssignDataScopeCommand.getUncheckedDataScopeIds(),
				roleAssignDataScopeCommand.getIsLazyLoad(), RoleDataScopeRelDO::getRoleId,RoleDataScopeRelDO::getDataScopeId,
				(relDto)->
						new RoleDataScopeRelDO()
								.setRoleId(relDto.getMainId())
								.setDataScopeId(relDto.getOtherId())
								.setDataObjectId(roleAssignDataScopeCommand.fetchDataObjectIdByDataScopeId(relDto.getOtherId()))
		);
		return Response.buildSuccess();
	}

	/**
	 * 数据范围分配角色
	 * @param dataScopeAssignRoleCommand
	 * @return
	 */
	public Response dataScopeAssignRole(@Valid DataScopeAssignRoleCommand dataScopeAssignRoleCommand) {
		boolean result = iRoleDataScopeRelService.removeAndAssignRel(dataScopeAssignRoleCommand.getDataScopeId(),
				dataScopeAssignRoleCommand.getCheckedRoleIds(),dataScopeAssignRoleCommand.getUncheckedRoleIds(),
				dataScopeAssignRoleCommand.getIsLazyLoad(), RoleDataScopeRelDO::getDataScopeId,RoleDataScopeRelDO::getRoleId,
				(relDto)->new RoleDataScopeRelDO().setDataScopeId(relDto.getMainId()).setRoleId(relDto.getOtherId()).setDataObjectId(dataScopeAssignRoleCommand.getDataObjectId()));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param roleDataScopeRelGateway
	 */
	@Autowired
	public void setRoleDataScopeRelGateway(RoleDataScopeRelGateway roleDataScopeRelGateway) {
		this.roleDataScopeRelGateway = roleDataScopeRelGateway;
	}
	@Autowired
	public void setIRoleDataScopeRelService(IRoleDataScopeRelService iRoleDataScopeRelService) {
		this.iRoleDataScopeRelService = iRoleDataScopeRelService;
	}
}
