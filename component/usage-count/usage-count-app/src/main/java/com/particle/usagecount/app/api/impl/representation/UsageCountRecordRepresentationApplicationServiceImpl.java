package com.particle.usagecount.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.representation.UsageCountRecordQueryCommandExecutor;
import com.particle.usagecount.client.api.representation.IUsageCountRecordRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 使用次数记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Service
@CatchAndLog
public class UsageCountRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountRecordRepresentationApplicationService {

    private UsageCountRecordQueryCommandExecutor usageCountRecordQueryCommandExecutor;

    @Override
    public SingleResponse<UsageCountRecordVO> queryDetail(IdCommand detailCommand) {
        return usageCountRecordQueryCommandExecutor.executeDetail(detailCommand);
    }


    @Override
    public PageResponse<UsageCountRecordVO> pageQuery(UsageCountRecordPageQueryCommand usageCountRecordPageQueryCommand) {
        return usageCountRecordQueryCommandExecutor.execute(usageCountRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<UsageCountRecordVO> queryList(UsageCountRecordQueryListCommand usageCountRecordQueryListCommand) {
        return usageCountRecordQueryCommandExecutor.execute(usageCountRecordQueryListCommand);
    }

    @Autowired
    public void setUsageCountRecordQueryCommandExecutor(UsageCountRecordQueryCommandExecutor usageCountRecordQueryCommandExecutor) {
        this.usageCountRecordQueryCommandExecutor = usageCountRecordQueryCommandExecutor;
    }
}
