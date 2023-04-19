package com.particle.tenant.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.executor.representation.TenantQueryCommandExecutor;
import com.particle.tenant.client.api.representation.ITenantRepresentationApplicationService;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Service
@CatchAndLog
public class TenantRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantRepresentationApplicationService {

    private TenantQueryCommandExecutor tenantQueryCommandExecutor;

    @Override
    public SingleResponse<TenantVO> queryDetail(IdCommand detailCommand) {
        return tenantQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantVO> pageQuery(TenantPageQueryCommand tenantPageQueryCommand) {
        return tenantQueryCommandExecutor.execute(tenantPageQueryCommand);
    }

    @Override
    public MultiResponse<TenantVO> queryList(TenantQueryListCommand tenantQueryListCommand) {
        return tenantQueryCommandExecutor.execute(tenantQueryListCommand);
    }

    @Autowired
    public void setTenantQueryCommandExecutor(TenantQueryCommandExecutor tenantQueryCommandExecutor) {
        this.tenantQueryCommandExecutor = tenantQueryCommandExecutor;
    }
}
