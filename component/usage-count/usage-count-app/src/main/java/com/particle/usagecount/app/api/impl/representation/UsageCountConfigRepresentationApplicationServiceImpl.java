package com.particle.usagecount.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.representation.UsageCountConfigQueryCommandExecutor;
import com.particle.usagecount.client.api.representation.IUsageCountConfigRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 使用次数配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Service
@CatchAndLog
public class UsageCountConfigRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountConfigRepresentationApplicationService {

    private UsageCountConfigQueryCommandExecutor usageCountConfigQueryCommandExecutor;

    @Override
    public SingleResponse<UsageCountConfigVO> queryDetail(IdCommand detailCommand) {
        return usageCountConfigQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<UsageCountConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return usageCountConfigQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<UsageCountConfigVO> pageQuery(UsageCountConfigPageQueryCommand usageCountConfigPageQueryCommand) {
        return usageCountConfigQueryCommandExecutor.execute(usageCountConfigPageQueryCommand);
    }

    @Override
    public MultiResponse<UsageCountConfigVO> queryList(UsageCountConfigQueryListCommand usageCountConfigQueryListCommand) {
        return usageCountConfigQueryCommandExecutor.execute(usageCountConfigQueryListCommand);
    }

    @Autowired
    public void setUsageCountConfigQueryCommandExecutor(UsageCountConfigQueryCommandExecutor usageCountConfigQueryCommandExecutor) {
        this.usageCountConfigQueryCommandExecutor = usageCountConfigQueryCommandExecutor;
    }
}
