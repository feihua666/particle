package com.particle.tenant.app.userinvite.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.userinvite.executor.representation.TenantUserInviteQueryCommandExecutor;
import com.particle.tenant.client.userinvite.api.representation.ITenantUserInviteRepresentationApplicationService;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInvitePageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户用户邀请 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Service
@CatchAndLog
public class TenantUserInviteRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserInviteRepresentationApplicationService {

    private TenantUserInviteQueryCommandExecutor tenantUserInviteQueryCommandExecutor;

    @Override
    public SingleResponse<TenantUserInviteVO> queryDetail(IdCommand detailCommand) {
        return tenantUserInviteQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantUserInviteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantUserInviteQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantUserInviteVO> pageQuery(TenantUserInvitePageQueryCommand tenantUserInvitePageQueryCommand) {
        return tenantUserInviteQueryCommandExecutor.execute(tenantUserInvitePageQueryCommand);
    }

    @Override
    public MultiResponse<TenantUserInviteVO> queryList(TenantUserInviteQueryListCommand tenantUserInviteQueryListCommand) {
        return tenantUserInviteQueryCommandExecutor.execute(tenantUserInviteQueryListCommand);
    }

    @Autowired
    public void setTenantUserInviteQueryCommandExecutor(TenantUserInviteQueryCommandExecutor tenantUserInviteQueryCommandExecutor) {
        this.tenantUserInviteQueryCommandExecutor = tenantUserInviteQueryCommandExecutor;
    }
}
