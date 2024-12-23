package com.particle.tenant.app.createapply.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyAuditCommandExecutor;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyCreateCommandExecutor;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyDeleteCommandExecutor;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyUpdateCommandExecutor;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 租户创建申请 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Transactional
@Service
@CatchAndLog
public class TenantCreateApplyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantCreateApplyApplicationService {

	private TenantCreateApplyCreateCommandExecutor tenantCreateApplyCreateCommandExecutor;

	private TenantCreateApplyDeleteCommandExecutor tenantCreateApplyDeleteCommandExecutor;

	private TenantCreateApplyUpdateCommandExecutor tenantCreateApplyUpdateCommandExecutor;

	private TenantCreateApplyAuditCommandExecutor tenantCreateApplyAuditCommandExecutor;

	@Override
	public SingleResponse<TenantCreateApplyVO> create(TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand) {
		return tenantCreateApplyCreateCommandExecutor.execute(tenantCreateApplyCreateCommand);
	}

	@Override
	public SingleResponse<TenantCreateApplyVO> delete(IdCommand deleteCommand) {
		return tenantCreateApplyDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantCreateApplyVO> update(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		return tenantCreateApplyUpdateCommandExecutor.execute(tenantCreateApplyUpdateCommand);
	}

	@Override
	public SingleResponse<TenantCreateApplyVO> audit(TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		return tenantCreateApplyAuditCommandExecutor.audit(tenantCreateApplyAuditCommand);
	}

	@Autowired
	public void setTenantCreateApplyCreateCommandExecutor(TenantCreateApplyCreateCommandExecutor tenantCreateApplyCreateCommandExecutor) {
		this.tenantCreateApplyCreateCommandExecutor = tenantCreateApplyCreateCommandExecutor;
	}

	@Autowired
	public void setTenantCreateApplyDeleteCommandExecutor(TenantCreateApplyDeleteCommandExecutor tenantCreateApplyDeleteCommandExecutor) {
		this.tenantCreateApplyDeleteCommandExecutor = tenantCreateApplyDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantCreateApplyUpdateCommandExecutor(TenantCreateApplyUpdateCommandExecutor tenantCreateApplyUpdateCommandExecutor) {
		this.tenantCreateApplyUpdateCommandExecutor = tenantCreateApplyUpdateCommandExecutor;
	}
	@Autowired
	public void setTenantCreateApplyAuditCommandExecutor(TenantCreateApplyAuditCommandExecutor tenantCreateApplyAuditCommandExecutor) {
		this.tenantCreateApplyAuditCommandExecutor = tenantCreateApplyAuditCommandExecutor;
	}
}
