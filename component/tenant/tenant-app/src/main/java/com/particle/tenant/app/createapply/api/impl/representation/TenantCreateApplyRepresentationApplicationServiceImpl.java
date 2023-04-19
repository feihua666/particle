package com.particle.tenant.app.createapply.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.createapply.executor.representation.TenantCreateApplyQueryCommandExecutor;
import com.particle.tenant.client.createapply.api.representation.ITenantCreateApplyRepresentationApplicationService;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyPageQueryCommand;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyQueryListCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户创建申请 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Service
@CatchAndLog
public class TenantCreateApplyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantCreateApplyRepresentationApplicationService {

    private TenantCreateApplyQueryCommandExecutor tenantCreateApplyQueryCommandExecutor;

    @Override
    public SingleResponse<TenantCreateApplyVO> queryDetail(IdCommand detailCommand) {
        return tenantCreateApplyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantCreateApplyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantCreateApplyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantCreateApplyVO> pageQuery(TenantCreateApplyPageQueryCommand tenantCreateApplyPageQueryCommand) {
        return tenantCreateApplyQueryCommandExecutor.execute(tenantCreateApplyPageQueryCommand);
    }

    @Override
    public MultiResponse<TenantCreateApplyVO> queryList(TenantCreateApplyQueryListCommand tenantCreateApplyQueryListCommand) {
        return tenantCreateApplyQueryCommandExecutor.execute(tenantCreateApplyQueryListCommand);
    }

    @Autowired
    public void setTenantCreateApplyQueryCommandExecutor(TenantCreateApplyQueryCommandExecutor tenantCreateApplyQueryCommandExecutor) {
        this.tenantCreateApplyQueryCommandExecutor = tenantCreateApplyQueryCommandExecutor;
    }
}
