package com.particle.componentadmin.app.executor.representation;

import com.particle.componentadmin.app.structmapping.AdminComponentAppStructMapping;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.componentadmin.infrastructure.service.IAdminComponentService;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentPageQueryCommand;
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
 * 组件 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Component
@Validated
public class AdminComponentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAdminComponentService iAdminComponentService;

	/**
	 * 执行 组件 列表查询指令
	 * @param adminComponentQueryListCommand
	 * @return
	 */
	public MultiResponse<AdminComponentVO> execute(@Valid AdminComponentQueryListCommand adminComponentQueryListCommand) {
		List<AdminComponentDO> adminComponentDO = iAdminComponentService.list(adminComponentQueryListCommand);
		List<AdminComponentVO> adminComponentVOs = AdminComponentAppStructMapping.instance.adminComponentDOsToAdminComponentVOs(adminComponentDO);
		return MultiResponse.of(adminComponentVOs);
	}
	/**
	 * 执行 组件 分页查询指令
	 * @param adminComponentPageQueryCommand
	 * @return
	 */
	public PageResponse<AdminComponentVO> execute(@Valid AdminComponentPageQueryCommand adminComponentPageQueryCommand) {
		Page<AdminComponentDO> page = iAdminComponentService.listPage(adminComponentPageQueryCommand);
		return AdminComponentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 组件 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AdminComponentVO> executeDetail(IdCommand detailCommand) {
		AdminComponentDO byId = iAdminComponentService.getById(detailCommand.getId());
		AdminComponentVO adminComponentVO = AdminComponentAppStructMapping.instance.adminComponentDOToAdminComponentVO(byId);
		return SingleResponse.of(adminComponentVO);
	}
	/**
	 * 执行 组件 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AdminComponentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AdminComponentDO byId = iAdminComponentService.getById(detailForUpdateCommand.getId());
		AdminComponentVO adminComponentVO = AdminComponentAppStructMapping.instance.adminComponentDOToAdminComponentVO(byId);
		return SingleResponse.of(adminComponentVO);
	}


	@Autowired
	public void setIAdminComponentService(IAdminComponentService iAdminComponentService) {
		this.iAdminComponentService = iAdminComponentService;
	}
}
