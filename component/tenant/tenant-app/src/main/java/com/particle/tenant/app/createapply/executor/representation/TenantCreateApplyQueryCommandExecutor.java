package com.particle.tenant.app.createapply.executor.representation;

import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyQueryListCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.tenant.infrastructure.createapply.service.ITenantCreateApplyService;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 租户创建申请 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Component
@Validated
public class TenantCreateApplyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantCreateApplyService iTenantCreateApplyService;

	/**
	 * 执行 租户创建申请 列表查询指令
	 * @param tenantCreateApplyQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyQueryListCommand tenantCreateApplyQueryListCommand) {
		List<TenantCreateApplyDO> tenantCreateApplyDO = iTenantCreateApplyService.list(tenantCreateApplyQueryListCommand);
		List<TenantCreateApplyVO> tenantCreateApplyVOs = TenantCreateApplyAppStructMapping.instance.tenantCreateApplyDOsToTenantCreateApplyVOs(tenantCreateApplyDO);
		return MultiResponse.of(tenantCreateApplyVOs);
	}
	/**
	 * 执行 租户创建申请 分页查询指令
	 * @param tenantCreateApplyPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyPageQueryCommand tenantCreateApplyPageQueryCommand) {
		Page<TenantCreateApplyDO> page = iTenantCreateApplyService.listPage(tenantCreateApplyPageQueryCommand);
		return TenantCreateApplyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户创建申请 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> executeDetail(IdCommand detailCommand) {
		TenantCreateApplyDO byId = iTenantCreateApplyService.getById(detailCommand.getId());
		TenantCreateApplyVO tenantCreateApplyVO = TenantCreateApplyAppStructMapping.instance.tenantCreateApplyDOToTenantCreateApplyVO(byId);
		return SingleResponse.of(tenantCreateApplyVO);
	}
	/**
	 * 执行 租户创建申请 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantCreateApplyDO byId = iTenantCreateApplyService.getById(detailForUpdateCommand.getId());
		TenantCreateApplyVO tenantCreateApplyVO = TenantCreateApplyAppStructMapping.instance.tenantCreateApplyDOToTenantCreateApplyVO(byId);
		return SingleResponse.of(tenantCreateApplyVO);
	}

	@Autowired
	public void setITenantCreateApplyService(ITenantCreateApplyService iTenantCreateApplyService) {
		this.iTenantCreateApplyService = iTenantCreateApplyService;
	}
}
