package com.particle.tenant.app.tenantfunc.api.impl;

import com.particle.global.dto.response.Response;
import com.particle.tenant.app.tenantfunc.executor.TenantFuncCommandExecutor;
import com.particle.tenant.app.tenantfunc.executor.TenantFuncCreateCommandExecutor;
import com.particle.tenant.app.tenantfunc.executor.TenantFuncDeleteCommandExecutor;
import com.particle.tenant.app.tenantfunc.executor.TenantFuncUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncUpdateCommand;
import com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncCreateCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
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
 * 租户功能菜单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Transactional
@Service
@CatchAndLog
public class TenantFuncApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantFuncApplicationService {

	private TenantFuncCreateCommandExecutor tenantFuncCreateCommandExecutor;

	private TenantFuncDeleteCommandExecutor tenantFuncDeleteCommandExecutor;

	private TenantFuncUpdateCommandExecutor tenantFuncUpdateCommandExecutor;

	private TenantFuncCommandExecutor tenantFuncCommandExecutor;


	@Override
	public SingleResponse<TenantFuncVO> create(TenantFuncCreateCommand tenantFuncCreateCommand) {
		return tenantFuncCreateCommandExecutor.execute(tenantFuncCreateCommand);
	}

	@Override
	public SingleResponse<TenantFuncVO> delete(IdCommand deleteCommand) {
		return tenantFuncDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TenantFuncVO> update(TenantFuncUpdateCommand tenantFuncUpdateCommand) {
		return tenantFuncUpdateCommandExecutor.execute(tenantFuncUpdateCommand);
	}

	@Override
	public Response tenantAssignFunc(TenantAssignFuncCommand cf) {
		return tenantFuncCommandExecutor.tenantAssignFunc(cf);
	}

	@Autowired
	public void setTenantFuncCreateCommandExecutor(TenantFuncCreateCommandExecutor tenantFuncCreateCommandExecutor) {
		this.tenantFuncCreateCommandExecutor = tenantFuncCreateCommandExecutor;
	}

	@Autowired
	public void setTenantFuncDeleteCommandExecutor(TenantFuncDeleteCommandExecutor tenantFuncDeleteCommandExecutor) {
		this.tenantFuncDeleteCommandExecutor = tenantFuncDeleteCommandExecutor;
	}
	@Autowired
	public void setTenantFuncUpdateCommandExecutor(TenantFuncUpdateCommandExecutor tenantFuncUpdateCommandExecutor) {
		this.tenantFuncUpdateCommandExecutor = tenantFuncUpdateCommandExecutor;
	}

	@Autowired
	public void setTenantFuncCommandExecutor(TenantFuncCommandExecutor tenantFuncCommandExecutor) {
		this.tenantFuncCommandExecutor = tenantFuncCommandExecutor;
	}
}
