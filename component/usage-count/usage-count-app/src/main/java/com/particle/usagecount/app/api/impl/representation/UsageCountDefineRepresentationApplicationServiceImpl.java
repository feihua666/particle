package com.particle.usagecount.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.representation.UsageCountDefineQueryCommandExecutor;
import com.particle.usagecount.client.api.representation.IUsageCountDefineRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefinePageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefineQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 使用次数定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Service
@CatchAndLog
public class UsageCountDefineRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountDefineRepresentationApplicationService {

    private UsageCountDefineQueryCommandExecutor usageCountDefineQueryCommandExecutor;

    @Override
    public SingleResponse<UsageCountDefineVO> queryDetail(IdCommand detailCommand) {
        return usageCountDefineQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<UsageCountDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return usageCountDefineQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<UsageCountDefineVO> pageQuery(UsageCountDefinePageQueryCommand usageCountDefinePageQueryCommand) {
        return usageCountDefineQueryCommandExecutor.execute(usageCountDefinePageQueryCommand);
    }

    @Override
    public MultiResponse<UsageCountDefineVO> queryList(UsageCountDefineQueryListCommand usageCountDefineQueryListCommand) {
        return usageCountDefineQueryCommandExecutor.execute(usageCountDefineQueryListCommand);
    }

    @Autowired
    public void setUsageCountDefineQueryCommandExecutor(UsageCountDefineQueryCommandExecutor usageCountDefineQueryCommandExecutor) {
        this.usageCountDefineQueryCommandExecutor = usageCountDefineQueryCommandExecutor;
    }
}
