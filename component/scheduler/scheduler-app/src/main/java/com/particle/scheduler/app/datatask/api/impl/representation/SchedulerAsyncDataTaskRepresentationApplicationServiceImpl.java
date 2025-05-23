package com.particle.scheduler.app.datatask.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.datatask.executor.representation.SchedulerAsyncDataTaskQueryCommandExecutor;
import com.particle.scheduler.client.datatask.api.representation.ISchedulerAsyncDataTaskRepresentationApplicationService;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划异步任务数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Service
@CatchAndLog
public class SchedulerAsyncDataTaskRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerAsyncDataTaskRepresentationApplicationService {

    private SchedulerAsyncDataTaskQueryCommandExecutor schedulerAsyncDataTaskQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerAsyncDataTaskVO> queryDetail(IdCommand detailCommand) {
        return schedulerAsyncDataTaskQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerAsyncDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerAsyncDataTaskQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerAsyncDataTaskVO> pageQuery(SchedulerAsyncDataTaskPageQueryCommand schedulerAsyncDataTaskPageQueryCommand) {
        return schedulerAsyncDataTaskQueryCommandExecutor.execute(schedulerAsyncDataTaskPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerAsyncDataTaskVO> queryList(SchedulerAsyncDataTaskQueryListCommand schedulerAsyncDataTaskQueryListCommand) {
        return schedulerAsyncDataTaskQueryCommandExecutor.execute(schedulerAsyncDataTaskQueryListCommand);
    }


    @Autowired
    public void setSchedulerAsyncDataTaskQueryCommandExecutor(SchedulerAsyncDataTaskQueryCommandExecutor schedulerAsyncDataTaskQueryCommandExecutor) {
        this.schedulerAsyncDataTaskQueryCommandExecutor = schedulerAsyncDataTaskQueryCommandExecutor;
    }
}
