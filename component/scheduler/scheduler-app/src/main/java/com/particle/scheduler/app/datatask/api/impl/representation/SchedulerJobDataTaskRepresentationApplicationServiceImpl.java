package com.particle.scheduler.app.datatask.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.datatask.executor.representation.SchedulerJobDataTaskQueryCommandExecutor;
import com.particle.scheduler.client.datatask.api.representation.ISchedulerJobDataTaskRepresentationApplicationService;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划任务数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Service
@CatchAndLog
public class SchedulerJobDataTaskRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerJobDataTaskRepresentationApplicationService {

    private SchedulerJobDataTaskQueryCommandExecutor schedulerJobDataTaskQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerJobDataTaskVO> queryDetail(IdCommand detailCommand) {
        return schedulerJobDataTaskQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerJobDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerJobDataTaskQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerJobDataTaskVO> pageQuery(SchedulerJobDataTaskPageQueryCommand schedulerJobDataTaskPageQueryCommand) {
        return schedulerJobDataTaskQueryCommandExecutor.execute(schedulerJobDataTaskPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerJobDataTaskVO> queryList(SchedulerJobDataTaskQueryListCommand schedulerJobDataTaskQueryListCommand) {
        return schedulerJobDataTaskQueryCommandExecutor.execute(schedulerJobDataTaskQueryListCommand);
    }


    @Autowired
    public void setSchedulerJobDataTaskQueryCommandExecutor(SchedulerJobDataTaskQueryCommandExecutor schedulerJobDataTaskQueryCommandExecutor) {
        this.schedulerJobDataTaskQueryCommandExecutor = schedulerJobDataTaskQueryCommandExecutor;
    }
}
