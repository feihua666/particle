package com.particle.usagecount.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.executor.representation.UsageCountRecordDetailQueryCommandExecutor;
import com.particle.usagecount.client.api.representation.IUsageCountRecordDetailRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 使用次数记录明细 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Service
@CatchAndLog
public class UsageCountRecordDetailRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUsageCountRecordDetailRepresentationApplicationService {

    private UsageCountRecordDetailQueryCommandExecutor usageCountRecordDetailQueryCommandExecutor;

    @Override
    public SingleResponse<UsageCountRecordDetailVO> queryDetail(IdCommand detailCommand) {
        return usageCountRecordDetailQueryCommandExecutor.executeDetail(detailCommand);
    }


    @Override
    public PageResponse<UsageCountRecordDetailVO> pageQuery(UsageCountRecordDetailPageQueryCommand usageCountRecordDetailPageQueryCommand) {
        return usageCountRecordDetailQueryCommandExecutor.execute(usageCountRecordDetailPageQueryCommand);
    }

    @Override
    public MultiResponse<UsageCountRecordDetailVO> queryList(UsageCountRecordDetailQueryListCommand usageCountRecordDetailQueryListCommand) {
        return usageCountRecordDetailQueryCommandExecutor.execute(usageCountRecordDetailQueryListCommand);
    }

    @Autowired
    public void setUsageCountRecordDetailQueryCommandExecutor(UsageCountRecordDetailQueryCommandExecutor usageCountRecordDetailQueryCommandExecutor) {
        this.usageCountRecordDetailQueryCommandExecutor = usageCountRecordDetailQueryCommandExecutor;
    }
}
