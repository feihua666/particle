package com.particle.tenant.app.userinvite.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.userinvite.executor.representation.TenantUserInviteUserRecordQueryCommandExecutor;
import com.particle.tenant.client.userinvite.api.representation.ITenantUserInviteUserRecordRepresentationApplicationService;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordPageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户用户邀请记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Service
@CatchAndLog
public class TenantUserInviteUserRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserInviteUserRecordRepresentationApplicationService {

    private TenantUserInviteUserRecordQueryCommandExecutor tenantUserInviteUserRecordQueryCommandExecutor;

    @Override
    public SingleResponse<TenantUserInviteUserRecordVO> queryDetail(IdCommand detailCommand) {
        return tenantUserInviteUserRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantUserInviteUserRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantUserInviteUserRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantUserInviteUserRecordVO> pageQuery(TenantUserInviteUserRecordPageQueryCommand tenantUserInviteUserRecordPageQueryCommand) {
        return tenantUserInviteUserRecordQueryCommandExecutor.execute(tenantUserInviteUserRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<TenantUserInviteUserRecordVO> queryList(TenantUserInviteUserRecordQueryListCommand tenantUserInviteUserRecordQueryListCommand) {
        return tenantUserInviteUserRecordQueryCommandExecutor.execute(tenantUserInviteUserRecordQueryListCommand);
    }

    @Autowired
    public void setTenantUserInviteUserRecordQueryCommandExecutor(TenantUserInviteUserRecordQueryCommandExecutor tenantUserInviteUserRecordQueryCommandExecutor) {
        this.tenantUserInviteUserRecordQueryCommandExecutor = tenantUserInviteUserRecordQueryCommandExecutor;
    }
}
