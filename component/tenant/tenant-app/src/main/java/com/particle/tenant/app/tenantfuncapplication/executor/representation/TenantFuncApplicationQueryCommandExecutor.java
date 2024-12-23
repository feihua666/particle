package com.particle.tenant.app.tenantfuncapplication.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.tenantfuncapplication.structmapping.TenantFuncApplicationAppStructMapping;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationPageQueryCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationQueryListCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.infrastructure.tenantfuncapplication.service.ITenantFuncApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户功能应用 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Component
@Validated
public class TenantFuncApplicationQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantFuncApplicationService iTenantFuncApplicationService;

	/**
	 * 执行 租户功能应用 列表查询指令
	 * @param tenantFuncApplicationQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantFuncApplicationVO> execute(@Valid TenantFuncApplicationQueryListCommand tenantFuncApplicationQueryListCommand) {
		List<TenantFuncApplicationDO> tenantFuncApplicationDO = iTenantFuncApplicationService.list(tenantFuncApplicationQueryListCommand);
		List<TenantFuncApplicationVO> tenantFuncApplicationVOs = TenantFuncApplicationAppStructMapping.instance.tenantFuncApplicationDOsToTenantFuncApplicationVOs(tenantFuncApplicationDO);
		return MultiResponse.of(tenantFuncApplicationVOs);
	}
	/**
	 * 执行 租户功能应用 分页查询指令
	 * @param tenantFuncApplicationPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantFuncApplicationVO> execute(@Valid TenantFuncApplicationPageQueryCommand tenantFuncApplicationPageQueryCommand) {
		Page<TenantFuncApplicationDO> page = iTenantFuncApplicationService.listPage(tenantFuncApplicationPageQueryCommand);
		return TenantFuncApplicationAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户功能应用 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantFuncApplicationVO> executeDetail(IdCommand detailCommand) {
		TenantFuncApplicationDO byId = iTenantFuncApplicationService.getById(detailCommand.getId());
		TenantFuncApplicationVO tenantFuncApplicationVO = TenantFuncApplicationAppStructMapping.instance.tenantFuncApplicationDOToTenantFuncApplicationVO(byId);
		return SingleResponse.of(tenantFuncApplicationVO);
	}
	/**
	 * 执行 租户功能应用 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncApplicationVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantFuncApplicationDO byId = iTenantFuncApplicationService.getById(detailForUpdateCommand.getId());
		TenantFuncApplicationVO tenantFuncApplicationVO = TenantFuncApplicationAppStructMapping.instance.tenantFuncApplicationDOToTenantFuncApplicationVO(byId);
		return SingleResponse.of(tenantFuncApplicationVO);
	}

	/**
	 * 根据租户ID查询已分配的功能应用id
	 * @param tenantIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncApplicationIdsByTenantId(IdCommand tenantIdCommand) {
		List<TenantFuncApplicationDO> tenantFuncApplicationDOS = iTenantFuncApplicationService.listByColumn(tenantIdCommand.getId(), TenantFuncApplicationDO::getTenantId);
		List<Long> collect = tenantFuncApplicationDOS.stream().map(TenantFuncApplicationDO::getFuncApplicationId).collect(Collectors.toList());
		return MultiResponse.of(collect);
	}


	@Autowired
	public void setITenantFuncApplicationService(ITenantFuncApplicationService iTenantFuncApplicationService) {
		this.iTenantFuncApplicationService = iTenantFuncApplicationService;
	}
}
