package com.particle.tenant.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.executor.representation.TenantUserQueryCommandExecutor;
import com.particle.tenant.client.api.representation.ITenantUserRepresentationApplicationService;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户用户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Service
@CatchAndLog
public class TenantUserRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantUserRepresentationApplicationService {

    private TenantUserQueryCommandExecutor tenantUserQueryCommandExecutor;

    @Override
    public SingleResponse<TenantUserVO> queryDetail(IdCommand detailCommand) {
        return tenantUserQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantUserVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantUserQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantUserVO> pageQuery(TenantUserPageQueryCommand tenantUserPageQueryCommand) {
        return tenantUserQueryCommandExecutor.execute(tenantUserPageQueryCommand);
    }

    @Override
    public MultiResponse<TenantUserVO> queryList(TenantUserQueryListCommand tenantUserQueryListCommand) {
        return tenantUserQueryCommandExecutor.execute(tenantUserQueryListCommand);
    }

    @Autowired
    public void setTenantUserQueryCommandExecutor(TenantUserQueryCommandExecutor tenantUserQueryCommandExecutor) {
        this.tenantUserQueryCommandExecutor = tenantUserQueryCommandExecutor;
    }
}
