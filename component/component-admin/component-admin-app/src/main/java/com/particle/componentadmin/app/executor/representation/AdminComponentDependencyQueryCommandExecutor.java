package com.particle.componentadmin.app.executor.representation;

import com.particle.componentadmin.app.structmapping.AdminComponentDependencyAppStructMapping;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.infrastructure.service.IAdminComponentDependencyService;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组件依赖关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Component
@Validated
public class AdminComponentDependencyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAdminComponentDependencyService iAdminComponentDependencyService;

	/**
	 * 执行 组件依赖关系 列表查询指令
	 * @param adminComponentDependencyQueryListCommand
	 * @return
	 */
	public MultiResponse<AdminComponentDependencyVO> execute(@Valid AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand) {
		List<AdminComponentDependencyDO> adminComponentDependencyDO = iAdminComponentDependencyService.list(adminComponentDependencyQueryListCommand);
		List<AdminComponentDependencyVO> adminComponentDependencyVOs = AdminComponentDependencyAppStructMapping.instance.adminComponentDependencyDOsToAdminComponentDependencyVOs(adminComponentDependencyDO);
		return MultiResponse.of(adminComponentDependencyVOs);
	}
	/**
	 * 执行 组件依赖关系 分页查询指令
	 * @param adminComponentDependencyPageQueryCommand
	 * @return
	 */
	public PageResponse<AdminComponentDependencyVO> execute(@Valid AdminComponentDependencyPageQueryCommand adminComponentDependencyPageQueryCommand) {
		Page<AdminComponentDependencyDO> page = iAdminComponentDependencyService.listPage(adminComponentDependencyPageQueryCommand);
		return AdminComponentDependencyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 组件依赖关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AdminComponentDependencyVO> executeDetail(IdCommand detailCommand) {
		AdminComponentDependencyDO byId = iAdminComponentDependencyService.getById(detailCommand.getId());
		AdminComponentDependencyVO adminComponentDependencyVO = AdminComponentDependencyAppStructMapping.instance.adminComponentDependencyDOToAdminComponentDependencyVO(byId);
		return SingleResponse.of(adminComponentDependencyVO);
	}
	/**
	 * 执行 组件依赖关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentDependencyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AdminComponentDependencyDO byId = iAdminComponentDependencyService.getById(detailForUpdateCommand.getId());
		AdminComponentDependencyVO adminComponentDependencyVO = AdminComponentDependencyAppStructMapping.instance.adminComponentDependencyDOToAdminComponentDependencyVO(byId);
		return SingleResponse.of(adminComponentDependencyVO);
	}


	/**
	 * 查询源组件已分配的依赖组件ids
	 * @param componentIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryDependComponentIdsByComponentId(@Valid IdCommand componentIdCommand) {

		AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand = new AdminComponentDependencyQueryListCommand();
		adminComponentDependencyQueryListCommand.setComponentId(componentIdCommand.getId());
		MultiResponse<AdminComponentDependencyVO> adminComponentDependencyVOMultiResponse = execute(adminComponentDependencyQueryListCommand);
		if(adminComponentDependencyVOMultiResponse.isNotEmpty()){
			List<Long> collect = adminComponentDependencyVOMultiResponse.getData().stream().map(AdminComponentDependencyVO::getDependComponentId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询依赖组件已分配的源组件ids
	 * @param dependComponentIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryComponentIdsByDependComponentId(@Valid IdCommand dependComponentIdCommand) {

		AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand = new AdminComponentDependencyQueryListCommand();
		adminComponentDependencyQueryListCommand.setDependComponentId(dependComponentIdCommand.getId());
		MultiResponse<AdminComponentDependencyVO> adminComponentDependencyVOMultiResponse = execute(adminComponentDependencyQueryListCommand);
		if(adminComponentDependencyVOMultiResponse.isNotEmpty()){
			List<Long> collect = adminComponentDependencyVOMultiResponse.getData().stream().map(AdminComponentDependencyVO::getComponentId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setIAdminComponentDependencyService(IAdminComponentDependencyService iAdminComponentDependencyService) {
		this.iAdminComponentDependencyService = iAdminComponentDependencyService;
	}
}
