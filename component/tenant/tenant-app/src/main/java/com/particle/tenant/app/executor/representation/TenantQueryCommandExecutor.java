package com.particle.tenant.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 租户 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Component
@Validated
public class TenantQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantService iTenantService;

	/**
	 * 执行 租户 列表查询指令
	 * @param tenantQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantVO> execute(@Valid TenantQueryListCommand tenantQueryListCommand) {
		List<TenantDO> tenantDO = iTenantService.list(tenantQueryListCommand);
		List<TenantVO> tenantVOs = TenantAppStructMapping.instance.tenantDOsToTenantVOs(tenantDO);
		return MultiResponse.of(tenantVOs);
	}
	/**
	 * 执行 租户 分页查询指令
	 * @param tenantPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantVO> execute(@Valid TenantPageQueryCommand tenantPageQueryCommand) {
		Page<TenantDO> page = iTenantService.listPage(tenantPageQueryCommand);
		return TenantAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantVO> executeDetail(IdCommand detailCommand) {
		TenantDO byId = iTenantService.getById(detailCommand.getId());
		TenantVO tenantVO = TenantAppStructMapping.instance.tenantDOToTenantVO(byId);
		return SingleResponse.of(tenantVO);
	}
	/**
	 * 执行 租户 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantDO byId = iTenantService.getById(detailForUpdateCommand.getId());
		TenantVO tenantVO = TenantAppStructMapping.instance.tenantDOToTenantVO(byId);
		return SingleResponse.of(tenantVO);
	}

	@Autowired
	public void setITenantService(ITenantService iTenantService) {
		this.iTenantService = iTenantService;
	}
}
