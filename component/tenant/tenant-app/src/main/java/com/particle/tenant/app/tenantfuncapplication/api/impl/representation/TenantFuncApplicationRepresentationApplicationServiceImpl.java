package com.particle.tenant.app.tenantfuncapplication.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.tenantfuncapplication.executor.representation.TenantFuncApplicationQueryCommandExecutor;
import com.particle.tenant.client.tenantfuncapplication.api.representation.ITenantFuncApplicationRepresentationApplicationService;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationPageQueryCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationQueryListCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户功能应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Service
@CatchAndLog
public class TenantFuncApplicationRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITenantFuncApplicationRepresentationApplicationService {

    private TenantFuncApplicationQueryCommandExecutor tenantFuncApplicationQueryCommandExecutor;

    @Override
    public SingleResponse<TenantFuncApplicationVO> queryDetail(IdCommand detailCommand) {
        return tenantFuncApplicationQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TenantFuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return tenantFuncApplicationQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TenantFuncApplicationVO> pageQuery(TenantFuncApplicationPageQueryCommand tenantFuncApplicationPageQueryCommand) {
        return tenantFuncApplicationQueryCommandExecutor.execute(tenantFuncApplicationPageQueryCommand);
    }

    @Override
    public MultiResponse<Long> queryFuncApplicationIdsByTenantId(IdCommand tenantIdCommand) {
        return tenantFuncApplicationQueryCommandExecutor.queryFuncApplicationIdsByTenantId(tenantIdCommand);
    }

    @Override
    public MultiResponse<TenantFuncApplicationVO> queryList(TenantFuncApplicationQueryListCommand tenantFuncApplicationQueryListCommand) {
        return tenantFuncApplicationQueryCommandExecutor.execute(tenantFuncApplicationQueryListCommand);
    }

    @Autowired
    public void setTenantFuncApplicationQueryCommandExecutor(TenantFuncApplicationQueryCommandExecutor tenantFuncApplicationQueryCommandExecutor) {
        this.tenantFuncApplicationQueryCommandExecutor = tenantFuncApplicationQueryCommandExecutor;
    }
}
