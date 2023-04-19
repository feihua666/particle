package com.particle.tenant.app.userinvite.executor.representation;

import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteService;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInvitePageQueryCommand;
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
 * 租户用户邀请 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Component
@Validated
public class TenantUserInviteQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantUserInviteService iTenantUserInviteService;

	/**
	 * 执行 租户用户邀请 列表查询指令
	 * @param tenantUserInviteQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantUserInviteVO> execute(@Valid TenantUserInviteQueryListCommand tenantUserInviteQueryListCommand) {
		List<TenantUserInviteDO> tenantUserInviteDO = iTenantUserInviteService.list(tenantUserInviteQueryListCommand);
		List<TenantUserInviteVO> tenantUserInviteVOs = TenantUserInviteAppStructMapping.instance.tenantUserInviteDOsToTenantUserInviteVOs(tenantUserInviteDO);
		return MultiResponse.of(tenantUserInviteVOs);
	}
	/**
	 * 执行 租户用户邀请 分页查询指令
	 * @param tenantUserInvitePageQueryCommand
	 * @return
	 */
	public PageResponse<TenantUserInviteVO> execute(@Valid TenantUserInvitePageQueryCommand tenantUserInvitePageQueryCommand) {
		Page<TenantUserInviteDO> page = iTenantUserInviteService.listPage(tenantUserInvitePageQueryCommand);
		return TenantUserInviteAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户用户邀请 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteVO> executeDetail(IdCommand detailCommand) {
		TenantUserInviteDO byId = iTenantUserInviteService.getById(detailCommand.getId());
		TenantUserInviteVO tenantUserInviteVO = TenantUserInviteAppStructMapping.instance.tenantUserInviteDOToTenantUserInviteVO(byId);
		return SingleResponse.of(tenantUserInviteVO);
	}
	/**
	 * 执行 租户用户邀请 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantUserInviteDO byId = iTenantUserInviteService.getById(detailForUpdateCommand.getId());
		TenantUserInviteVO tenantUserInviteVO = TenantUserInviteAppStructMapping.instance.tenantUserInviteDOToTenantUserInviteVO(byId);
		return SingleResponse.of(tenantUserInviteVO);
	}

	@Autowired
	public void setITenantUserInviteService(ITenantUserInviteService iTenantUserInviteService) {
		this.iTenantUserInviteService = iTenantUserInviteService;
	}
}
