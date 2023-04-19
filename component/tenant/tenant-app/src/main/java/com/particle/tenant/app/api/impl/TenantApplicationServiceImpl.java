package com.particle.tenant.app.api.impl;

import com.particle.tenant.app.executor.TenantCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantDeleteCommandExecutor;
import com.particle.tenant.app.executor.TenantUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
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
 * 租户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Transactional
@Service
@CatchAndLog
public class TenantApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantApplicationService {

	private TenantCreateCommandExecutor tenantCreateCommandExecutor;

	private TenantDeleteCommandExecutor tenantDeleteCommandExecutor;

	private TenantUpdateCommandExecutor tenantUpdateCommandExecutor;


	@Override
	public SingleResponse<TenantVO> create(TenantCreateCommand tenantCreateCommand) {
		return tenantCreateCommandExecutor.execute(tenantCreateCommand);
	}

	@Override
	public SingleResponse<TenantVO> delete(IdCommand deleteCommand) {
		return tenantDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantVO> update(TenantUpdateCommand tenantUpdateCommand) {
		return tenantUpdateCommandExecutor.execute(tenantUpdateCommand);
	}

	@Autowired
	public void setTenantCreateCommandExecutor(TenantCreateCommandExecutor tenantCreateCommandExecutor) {
		this.tenantCreateCommandExecutor = tenantCreateCommandExecutor;
	}

	@Autowired
	public void setTenantDeleteCommandExecutor(TenantDeleteCommandExecutor tenantDeleteCommandExecutor) {
		this.tenantDeleteCommandExecutor = tenantDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantUpdateCommandExecutor(TenantUpdateCommandExecutor tenantUpdateCommandExecutor) {
		this.tenantUpdateCommandExecutor = tenantUpdateCommandExecutor;
	}

}
