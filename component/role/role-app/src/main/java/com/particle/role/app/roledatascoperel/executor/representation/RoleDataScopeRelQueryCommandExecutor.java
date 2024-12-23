package com.particle.role.app.roledatascoperel.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roledatascoperel.structmapping.RoleDataScopeRelAppStructMapping;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelPageQueryCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelQueryListCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色数据范围关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
@Validated
public class RoleDataScopeRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IRoleDataScopeRelService iRoleDataScopeRelService;

	/**
	 * 执行 角色数据范围关系 列表查询指令
	 * @param roleDataScopeRelQueryListCommand
	 * @return
	 */
	public MultiResponse<RoleDataScopeRelVO> execute(@Valid RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand) {
		List<RoleDataScopeRelDO> roleDataScopeRelDO = iRoleDataScopeRelService.list(roleDataScopeRelQueryListCommand);
		List<RoleDataScopeRelVO> roleDataScopeRelVOs = RoleDataScopeRelAppStructMapping.instance.roleDataScopeRelDOsToRoleDataScopeRelVOs(roleDataScopeRelDO);
		return MultiResponse.of(roleDataScopeRelVOs);
	}
	/**
	 * 执行 角色数据范围关系 分页查询指令
	 * @param roleDataScopeRelPageQueryCommand
	 * @return
	 */
	public PageResponse<RoleDataScopeRelVO> execute(@Valid RoleDataScopeRelPageQueryCommand roleDataScopeRelPageQueryCommand) {
		Page<RoleDataScopeRelDO> page = iRoleDataScopeRelService.listPage(roleDataScopeRelPageQueryCommand);
		return RoleDataScopeRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 角色数据范围关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<RoleDataScopeRelVO> executeDetail(IdCommand detailCommand) {
		RoleDataScopeRelDO byId = iRoleDataScopeRelService.getById(detailCommand.getId());
		RoleDataScopeRelVO roleDataScopeRelVO = RoleDataScopeRelAppStructMapping.instance.roleDataScopeRelDOToRoleDataScopeRelVO(byId);
		return SingleResponse.of(roleDataScopeRelVO);
	}
	/**
	 * 执行 角色数据范围关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleDataScopeRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		RoleDataScopeRelDO byId = iRoleDataScopeRelService.getById(detailForUpdateCommand.getId());
		RoleDataScopeRelVO roleDataScopeRelVO = RoleDataScopeRelAppStructMapping.instance.roleDataScopeRelDOToRoleDataScopeRelVO(byId);
		return SingleResponse.of(roleDataScopeRelVO);
	}


	/**
	 * 查询角色已分配的数据范围ids
	 * @param roleIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryDataScopeIdsByRoleId(@Valid IdCommand roleIdCommand) {

		RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand = new RoleDataScopeRelQueryListCommand();
		roleDataScopeRelQueryListCommand.setRoleId(roleIdCommand.getId());
		MultiResponse<RoleDataScopeRelVO> roleDataScopeRelVOMultiResponse = execute(roleDataScopeRelQueryListCommand);
		if(roleDataScopeRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = roleDataScopeRelVOMultiResponse.getData().stream().map(RoleDataScopeRelVO::getDataScopeId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询数据范围已分配的角色ids
	 * @param dataScopeIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryRoleIdsByDataScopeId(@Valid IdCommand dataScopeIdCommand) {

		RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand = new RoleDataScopeRelQueryListCommand();
		roleDataScopeRelQueryListCommand.setDataScopeId(dataScopeIdCommand.getId());
		MultiResponse<RoleDataScopeRelVO> roleDataScopeRelVOMultiResponse = execute(roleDataScopeRelQueryListCommand);
		if(roleDataScopeRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = roleDataScopeRelVOMultiResponse.getData().stream().map(RoleDataScopeRelVO::getRoleId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setIRoleDataScopeRelService(IRoleDataScopeRelService iRoleDataScopeRelService) {
		this.iRoleDataScopeRelService = iRoleDataScopeRelService;
	}
}
