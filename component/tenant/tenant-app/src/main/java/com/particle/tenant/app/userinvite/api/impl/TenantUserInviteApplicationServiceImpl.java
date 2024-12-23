package com.particle.tenant.app.userinvite.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.userinvite.executor.TenantUserInviteCreateCommandExecutor;
import com.particle.tenant.app.userinvite.executor.TenantUserInviteDeleteCommandExecutor;
import com.particle.tenant.app.userinvite.executor.TenantUserInviteUpdateCommandExecutor;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteCreateCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUpdateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 租户用户邀请 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Transactional
@Service
@CatchAndLog
public class TenantUserInviteApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserInviteApplicationService {

	private TenantUserInviteCreateCommandExecutor tenantUserInviteCreateCommandExecutor;

	private TenantUserInviteDeleteCommandExecutor tenantUserInviteDeleteCommandExecutor;

	private TenantUserInviteUpdateCommandExecutor tenantUserInviteUpdateCommandExecutor;


	@Override
	public SingleResponse<TenantUserInviteVO> create(TenantUserInviteCreateCommand tenantUserInviteCreateCommand) {
		return tenantUserInviteCreateCommandExecutor.execute(tenantUserInviteCreateCommand);
	}

	@Override
	public SingleResponse<TenantUserInviteVO> delete(IdCommand deleteCommand) {
		return tenantUserInviteDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantUserInviteVO> update(TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand) {
		return tenantUserInviteUpdateCommandExecutor.execute(tenantUserInviteUpdateCommand);
	}

	@Autowired
	public void setTenantUserInviteCreateCommandExecutor(TenantUserInviteCreateCommandExecutor tenantUserInviteCreateCommandExecutor) {
		this.tenantUserInviteCreateCommandExecutor = tenantUserInviteCreateCommandExecutor;
	}

	@Autowired
	public void setTenantUserInviteDeleteCommandExecutor(TenantUserInviteDeleteCommandExecutor tenantUserInviteDeleteCommandExecutor) {
		this.tenantUserInviteDeleteCommandExecutor = tenantUserInviteDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantUserInviteUpdateCommandExecutor(TenantUserInviteUpdateCommandExecutor tenantUserInviteUpdateCommandExecutor) {
		this.tenantUserInviteUpdateCommandExecutor = tenantUserInviteUpdateCommandExecutor;
	}

}
