package com.particle.role.app.rolefuncrel.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单功能关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleFuncRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IRoleFuncRelService iRoleFuncRelService;

	/**
	 * 执行 角色菜单功能关系 列表查询指令
	 * @param roleFuncRelQueryListCommand
	 * @return
	 */
	public MultiResponse<RoleFuncRelVO> execute(@Valid RoleFuncRelQueryListCommand roleFuncRelQueryListCommand) {
		List<RoleFuncRelDO> roleFuncRelDO = iRoleFuncRelService.list(roleFuncRelQueryListCommand);
		List<RoleFuncRelVO> roleFuncRelVOs = RoleFuncRelAppStructMapping.instance.roleFuncRelDOsToRoleFuncRelVOs(roleFuncRelDO);
		return MultiResponse.of(roleFuncRelVOs);
	}
	/**
	 * 执行 角色菜单功能关系 分页查询指令
	 * @param roleFuncRelPageQueryCommand
	 * @return
	 */
	public PageResponse<RoleFuncRelVO> execute(@Valid RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand) {
		Page<RoleFuncRelDO> page = iRoleFuncRelService.listPage(roleFuncRelPageQueryCommand);
		return RoleFuncRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 角色菜单功能关系 展示用详情查询指令
	 * @param roleFuncRelQueryDetailCommand
	 * @return
	 */
	public SingleResponse<RoleFuncRelVO> executeDetail(IdCommand roleFuncRelQueryDetailCommand) {
		RoleFuncRelDO byId = iRoleFuncRelService.getById(roleFuncRelQueryDetailCommand.getId());
		RoleFuncRelVO roleFuncRelVO = RoleFuncRelAppStructMapping.instance.roleFuncRelDOToRoleFuncRelVO(byId);
		return SingleResponse.of(roleFuncRelVO);
	}

	/**
	 * 查询角色已分配的功能菜单ids
	 * @param roleIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByRoleId(@Valid IdCommand roleIdCommand) {

		RoleFuncRelQueryListCommand roleUserRelQueryListCommand = new RoleFuncRelQueryListCommand();
		roleUserRelQueryListCommand.setRoleId(roleIdCommand.getId());
		MultiResponse<RoleFuncRelVO> roleUserRelVOMultiResponse = execute(roleUserRelQueryListCommand);
		if(roleUserRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = roleUserRelVOMultiResponse.getData().stream().map(RoleFuncRelVO::getFuncId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询功能已分配的角色ids
	 * @param funcIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryRoleIdsByFuncId(@Valid IdCommand funcIdCommand) {

		RoleFuncRelQueryListCommand roleUserRelQueryListCommand = new RoleFuncRelQueryListCommand();
		roleUserRelQueryListCommand.setFuncId(funcIdCommand.getId());
		MultiResponse<RoleFuncRelVO> roleUserRelVOMultiResponse = execute(roleUserRelQueryListCommand);
		if(roleUserRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = roleUserRelVOMultiResponse.getData().stream().map(RoleFuncRelVO::getRoleId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	@Autowired
	public void setIRoleFuncRelService(IRoleFuncRelService iRoleFuncRelService) {
		this.iRoleFuncRelService = iRoleFuncRelService;
	}
}
