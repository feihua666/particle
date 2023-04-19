package com.particle.tenant.app.userinvite.api.impl;

import com.particle.tenant.app.userinvite.executor.TenantUserInviteUserRecordCreateCommandExecutor;
import com.particle.tenant.app.userinvite.executor.TenantUserInviteUserRecordDeleteCommandExecutor;
import com.particle.tenant.app.userinvite.executor.TenantUserInviteUserRecordUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordUpdateCommand;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteUserRecordApplicationService;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordCreateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 租户用户邀请记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Transactional
@Service
@CatchAndLog
public class TenantUserInviteUserRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserInviteUserRecordApplicationService {

	private TenantUserInviteUserRecordCreateCommandExecutor tenantUserInviteUserRecordCreateCommandExecutor;

	private TenantUserInviteUserRecordDeleteCommandExecutor tenantUserInviteUserRecordDeleteCommandExecutor;

	private TenantUserInviteUserRecordUpdateCommandExecutor tenantUserInviteUserRecordUpdateCommandExecutor;


	@Override
	public SingleResponse<TenantUserInviteUserRecordVO> create(TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand) {
		return tenantUserInviteUserRecordCreateCommandExecutor.execute(tenantUserInviteUserRecordCreateCommand);
	}

	@Override
	public SingleResponse<TenantUserInviteUserRecordVO> delete(IdCommand deleteCommand) {
		return tenantUserInviteUserRecordDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantUserInviteUserRecordVO> update(TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand) {
		return tenantUserInviteUserRecordUpdateCommandExecutor.execute(tenantUserInviteUserRecordUpdateCommand);
	}

	@Autowired
	public void setTenantUserInviteUserRecordCreateCommandExecutor(TenantUserInviteUserRecordCreateCommandExecutor tenantUserInviteUserRecordCreateCommandExecutor) {
		this.tenantUserInviteUserRecordCreateCommandExecutor = tenantUserInviteUserRecordCreateCommandExecutor;
	}

	@Autowired
	public void setTenantUserInviteUserRecordDeleteCommandExecutor(TenantUserInviteUserRecordDeleteCommandExecutor tenantUserInviteUserRecordDeleteCommandExecutor) {
		this.tenantUserInviteUserRecordDeleteCommandExecutor = tenantUserInviteUserRecordDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantUserInviteUserRecordUpdateCommandExecutor(TenantUserInviteUserRecordUpdateCommandExecutor tenantUserInviteUserRecordUpdateCommandExecutor) {
		this.tenantUserInviteUserRecordUpdateCommandExecutor = tenantUserInviteUserRecordUpdateCommandExecutor;
	}

}
