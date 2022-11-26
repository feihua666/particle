package com.particle.role.app.rolefuncrel.executor.representation;

import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryDetailCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryDetailForUpdateCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

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
	public SingleResponse<RoleFuncRelVO> execute(RoleFuncRelQueryDetailCommand roleFuncRelQueryDetailCommand) {
		RoleFuncRelDO byId = iRoleFuncRelService.getById(roleFuncRelQueryDetailCommand.getId());
		RoleFuncRelVO roleFuncRelVO = RoleFuncRelAppStructMapping.instance.roleFuncRelDOToRoleFuncRelVO(byId);
		return SingleResponse.of(roleFuncRelVO);
	}
	/**
	 * 执行 角色菜单功能关系 更新用详情查询指令
	 * @param roleFuncRelQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleFuncRelVO> execute(RoleFuncRelQueryDetailForUpdateCommand roleFuncRelQueryDetailForUpdateCommand) {
		RoleFuncRelDO byId = iRoleFuncRelService.getById(roleFuncRelQueryDetailForUpdateCommand.getId());
		RoleFuncRelVO roleFuncRelVO = RoleFuncRelAppStructMapping.instance.roleFuncRelDOToRoleFuncRelVO(byId);
		return SingleResponse.of(roleFuncRelVO);
	}

	@Autowired
	public void setIRoleFuncRelService(IRoleFuncRelService iRoleFuncRelService) {
		this.iRoleFuncRelService = iRoleFuncRelService;
	}
}
