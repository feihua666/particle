package com.particle.tenant.app.tenantfunc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.tenantfunc.executor.representation.TenantFuncQueryCommandExecutor;
import com.particle.tenant.client.tenantfunc.api.representation.ITenantFuncRepresentationApplicationService;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncPageQueryCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryFuncIdsByTenantIdCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户功能菜单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Service
@CatchAndLog
public class TenantFuncRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantFuncRepresentationApplicationService {

    private TenantFuncQueryCommandExecutor tenantFuncQueryCommandExecutor;

    @Override
    public SingleResponse<TenantFuncVO> queryDetail(IdCommand detailCommand) {
        return tenantFuncQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantFuncVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantFuncQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantFuncVO> pageQuery(TenantFuncPageQueryCommand tenantFuncPageQueryCommand) {
        return tenantFuncQueryCommandExecutor.execute(tenantFuncPageQueryCommand);
    }

    @Override
    public MultiResponse<Long> queryFuncIdsByTenantId(TenantFuncQueryFuncIdsByTenantIdCommand funcIdCommand) {
        return tenantFuncQueryCommandExecutor.queryFuncIdsByTenantId(funcIdCommand);
    }

    @Override
    public MultiResponse<TenantFuncVO> queryList(TenantFuncQueryListCommand tenantFuncQueryListCommand) {
        return tenantFuncQueryCommandExecutor.execute(tenantFuncQueryListCommand);
    }

    @Autowired
    public void setTenantFuncQueryCommandExecutor(TenantFuncQueryCommandExecutor tenantFuncQueryCommandExecutor) {
        this.tenantFuncQueryCommandExecutor = tenantFuncQueryCommandExecutor;
    }
}
