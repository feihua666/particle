package com.particle.tenant.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantUserDeleteCommandExecutor;
import com.particle.tenant.app.executor.TenantUserUpdateCommandExecutor;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserUpdateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 租户用户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Transactional
@Service
@CatchAndLog
public class TenantUserApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserApplicationService {

	private TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor;

	private TenantUserDeleteCommandExecutor tenantUserDeleteCommandExecutor;

	private TenantUserUpdateCommandExecutor tenantUserUpdateCommandExecutor;


	@Override
	public SingleResponse<TenantUserVO> create(TenantUserCreateCommand tenantUserCreateCommand) {
		return tenantUserCreateCommandExecutor.execute(tenantUserCreateCommand);
	}

	@Override
	public SingleResponse<TenantUserVO> delete(IdCommand deleteCommand) {
		return tenantUserDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantUserVO> update(TenantUserUpdateCommand tenantUserUpdateCommand) {
		return tenantUserUpdateCommandExecutor.execute(tenantUserUpdateCommand);
	}

	@Autowired
	public void setTenantUserCreateCommandExecutor(TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor) {
		this.tenantUserCreateCommandExecutor = tenantUserCreateCommandExecutor;
	}

	@Autowired
	public void setTenantUserDeleteCommandExecutor(TenantUserDeleteCommandExecutor tenantUserDeleteCommandExecutor) {
		this.tenantUserDeleteCommandExecutor = tenantUserDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantUserUpdateCommandExecutor(TenantUserUpdateCommandExecutor tenantUserUpdateCommandExecutor) {
		this.tenantUserUpdateCommandExecutor = tenantUserUpdateCommandExecutor;
	}

}
