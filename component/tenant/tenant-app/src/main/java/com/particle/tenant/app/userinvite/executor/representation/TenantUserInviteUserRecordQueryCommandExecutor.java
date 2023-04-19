package com.particle.tenant.app.userinvite.executor.representation;

import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteUserRecordAppStructMapping;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteUserRecordService;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordPageQueryCommand;
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
 * 租户用户邀请记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Component
@Validated
public class TenantUserInviteUserRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantUserInviteUserRecordService iTenantUserInviteUserRecordService;

	/**
	 * 执行 租户用户邀请记录 列表查询指令
	 * @param tenantUserInviteUserRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantUserInviteUserRecordVO> execute(@Valid TenantUserInviteUserRecordQueryListCommand tenantUserInviteUserRecordQueryListCommand) {
		List<TenantUserInviteUserRecordDO> tenantUserInviteUserRecordDO = iTenantUserInviteUserRecordService.list(tenantUserInviteUserRecordQueryListCommand);
		List<TenantUserInviteUserRecordVO> tenantUserInviteUserRecordVOs = TenantUserInviteUserRecordAppStructMapping.instance.tenantUserInviteUserRecordDOsToTenantUserInviteUserRecordVOs(tenantUserInviteUserRecordDO);
		return MultiResponse.of(tenantUserInviteUserRecordVOs);
	}
	/**
	 * 执行 租户用户邀请记录 分页查询指令
	 * @param tenantUserInviteUserRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantUserInviteUserRecordVO> execute(@Valid TenantUserInviteUserRecordPageQueryCommand tenantUserInviteUserRecordPageQueryCommand) {
		Page<TenantUserInviteUserRecordDO> page = iTenantUserInviteUserRecordService.listPage(tenantUserInviteUserRecordPageQueryCommand);
		return TenantUserInviteUserRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户用户邀请记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteUserRecordVO> executeDetail(IdCommand detailCommand) {
		TenantUserInviteUserRecordDO byId = iTenantUserInviteUserRecordService.getById(detailCommand.getId());
		TenantUserInviteUserRecordVO tenantUserInviteUserRecordVO = TenantUserInviteUserRecordAppStructMapping.instance.tenantUserInviteUserRecordDOToTenantUserInviteUserRecordVO(byId);
		return SingleResponse.of(tenantUserInviteUserRecordVO);
	}
	/**
	 * 执行 租户用户邀请记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteUserRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantUserInviteUserRecordDO byId = iTenantUserInviteUserRecordService.getById(detailForUpdateCommand.getId());
		TenantUserInviteUserRecordVO tenantUserInviteUserRecordVO = TenantUserInviteUserRecordAppStructMapping.instance.tenantUserInviteUserRecordDOToTenantUserInviteUserRecordVO(byId);
		return SingleResponse.of(tenantUserInviteUserRecordVO);
	}

	@Autowired
	public void setITenantUserInviteUserRecordService(ITenantUserInviteUserRecordService iTenantUserInviteUserRecordService) {
		this.iTenantUserInviteUserRecordService = iTenantUserInviteUserRecordService;
	}
}
