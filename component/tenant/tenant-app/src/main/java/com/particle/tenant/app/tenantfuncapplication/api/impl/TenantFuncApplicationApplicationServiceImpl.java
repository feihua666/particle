package com.particle.tenant.app.tenantfuncapplication.api.impl;

import com.particle.global.dto.response.Response;
import com.particle.tenant.app.tenantfuncapplication.executor.TenantFuncApplicationCommandExecutor;
import com.particle.tenant.app.tenantfuncapplication.executor.TenantFuncApplicationCreateCommandExecutor;
import com.particle.tenant.app.tenantfuncapplication.executor.TenantFuncApplicationDeleteCommandExecutor;
import com.particle.tenant.app.tenantfuncapplication.executor.TenantFuncApplicationUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantAssignFuncApplicationCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationUpdateCommand;
import com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationCreateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
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
 * 租户功能应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Transactional
@Service
@CatchAndLog
public class TenantFuncApplicationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantFuncApplicationApplicationService {

	private TenantFuncApplicationCreateCommandExecutor tenantFuncApplicationCreateCommandExecutor;

	private TenantFuncApplicationDeleteCommandExecutor tenantFuncApplicationDeleteCommandExecutor;

	private TenantFuncApplicationUpdateCommandExecutor tenantFuncApplicationUpdateCommandExecutor;


	private TenantFuncApplicationCommandExecutor tenantFuncApplicationCommandExecutor;

	@Override
	public SingleResponse<TenantFuncApplicationVO> create(TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand) {
		return tenantFuncApplicationCreateCommandExecutor.execute(tenantFuncApplicationCreateCommand);
	}

	@Override
	public SingleResponse<TenantFuncApplicationVO> delete(IdCommand deleteCommand) {
		return tenantFuncApplicationDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantFuncApplicationVO> update(TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand) {
		return tenantFuncApplicationUpdateCommandExecutor.execute(tenantFuncApplicationUpdateCommand);
	}

	@Override
	public Response tenantAssignFuncApplication(TenantAssignFuncApplicationCommand cf) {
		return tenantFuncApplicationCommandExecutor.tenantAssignFuncApplication(cf);
	}

	@Autowired
	public void setTenantFuncApplicationCreateCommandExecutor(TenantFuncApplicationCreateCommandExecutor tenantFuncApplicationCreateCommandExecutor) {
		this.tenantFuncApplicationCreateCommandExecutor = tenantFuncApplicationCreateCommandExecutor;
	}

	@Autowired
	public void setTenantFuncApplicationDeleteCommandExecutor(TenantFuncApplicationDeleteCommandExecutor tenantFuncApplicationDeleteCommandExecutor) {
		this.tenantFuncApplicationDeleteCommandExecutor = tenantFuncApplicationDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantFuncApplicationUpdateCommandExecutor(TenantFuncApplicationUpdateCommandExecutor tenantFuncApplicationUpdateCommandExecutor) {
		this.tenantFuncApplicationUpdateCommandExecutor = tenantFuncApplicationUpdateCommandExecutor;
	}
	@Autowired
	public void setTenantFuncApplicationCommandExecutor(TenantFuncApplicationCommandExecutor tenantFuncApplicationCommandExecutor) {
		this.tenantFuncApplicationCommandExecutor = tenantFuncApplicationCommandExecutor;
	}
}
