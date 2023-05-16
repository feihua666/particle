package com.particle.tenant.app.api.impl;

import com.particle.tenant.app.createapply.executor.TenantCreateApplyCreateCommandExecutor;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyUpdateCommandExecutor;
import com.particle.tenant.app.executor.TenantCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantDeleteCommandExecutor;
import com.particle.tenant.app.executor.TenantUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditPassCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantCreateApplyAuditStatus;
import com.particle.tenant.domain.TenantId;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import com.particle.tenant.domain.gateway.TenantGateway;
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

	private TenantCreateApplyCreateCommandExecutor tenantCreateApplyCreateCommandExecutor;
	private TenantCreateApplyUpdateCommandExecutor tenantCreateApplyUpdateCommandExecutor;

	private TenantGateway tenantGateway;
	private TenantCreateApplyGateway tenantCreateApplyGateway;
	private TenantDictGateway tenantDictGateway;

	@Override
	public SingleResponse<TenantVO> create(TenantCreateCommand tenantCreateCommand) {
		return tenantCreateCommandExecutor.execute(tenantCreateCommand);
	}

	@Override
	public SingleResponse<TenantVO> oneClickCreate(TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand, TenantCreateApplyAuditPassCommand tenantCreateApplyAuditPassCommand) {
		// 创建申请
		SingleResponse<TenantCreateApplyVO> execute = tenantCreateApplyCreateCommandExecutor.execute(tenantCreateApplyCreateCommand);

		TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand = new TenantCreateApplyAuditCommand();
		tenantCreateApplyAuditCommand.setAuditStatusComment(tenantCreateApplyAuditPassCommand.getAuditStatusComment());
		tenantCreateApplyAuditCommand.setAuditUserId(tenantCreateApplyAuditPassCommand.getAuditUserId());
		tenantCreateApplyAuditCommand.setTenantSuperAdminRoleCode(tenantCreateApplyAuditPassCommand.getTenantSuperAdminRoleCode());

		// 获取审批通过创建的租户数据
		TenantCreateApply tenantCreateApply = tenantCreateApplyGateway.getById(TenantCreateApplyId.of(execute.getData().getId()));
		// 审批通过
		tenantCreateApplyAuditCommand.setId(tenantCreateApply.getId().getId());
		tenantCreateApplyAuditCommand.setVersion(tenantCreateApply.getVersion());
		Long tenantCreateApplyAuditStatusPass = tenantDictGateway.getDictIdByGroupCodeAndItemValue(TenantCreateApplyAuditStatus.Group.tenant_create_apply_audit_status.groupCode(), TenantCreateApplyAuditStatus.audit_pass.itemValue());
		tenantCreateApplyAuditCommand.setAuditStatusDictId(tenantCreateApplyAuditStatusPass);

		SingleResponse<TenantCreateApplyVO> audit = tenantCreateApplyUpdateCommandExecutor.audit(tenantCreateApplyAuditCommand);
		if (tenantCreateApply.checkIsAuditPass()) {
			// 返回租户信息
			Tenant tenant = tenantGateway.getById(TenantId.of(tenantCreateApply.getAppliedTenantId()));
			return SingleResponse.of(TenantAppStructMapping.instance.toTenantVO(tenant));
		}
		return SingleResponse.buildSuccess();
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
	@Autowired
	public void setTenantCreateApplyCreateCommandExecutor(TenantCreateApplyCreateCommandExecutor tenantCreateApplyCreateCommandExecutor) {
		this.tenantCreateApplyCreateCommandExecutor = tenantCreateApplyCreateCommandExecutor;
	}
	@Autowired
	public void setTenantCreateApplyUpdateCommandExecutor(TenantCreateApplyUpdateCommandExecutor tenantCreateApplyUpdateCommandExecutor) {
		this.tenantCreateApplyUpdateCommandExecutor = tenantCreateApplyUpdateCommandExecutor;
	}
	@Autowired
	public void setTenantGateway(TenantGateway tenantGateway) {
		this.tenantGateway = tenantGateway;
	}
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}
	@Autowired
	public void setTenantDictGateway(TenantDictGateway tenantDictGateway) {
		this.tenantDictGateway = tenantDictGateway;
	}
}
