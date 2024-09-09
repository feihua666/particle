package com.particle.scheduler.app.temptask.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.representation.SchedulerTempTaskQueryCommandExecutor;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划临时任务 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Service
@CatchAndLog
public class SchedulerTempTaskRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskRepresentationApplicationService {

    private SchedulerTempTaskQueryCommandExecutor schedulerTempTaskQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerTempTaskVO> queryDetail(IdCommand detailCommand) {
        return schedulerTempTaskQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerTempTaskQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerTempTaskVO> pageQuery(SchedulerTempTaskPageQueryCommand schedulerTempTaskPageQueryCommand) {
        return schedulerTempTaskQueryCommandExecutor.execute(schedulerTempTaskPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerTempTaskVO> queryList(SchedulerTempTaskQueryListCommand schedulerTempTaskQueryListCommand) {
        return schedulerTempTaskQueryCommandExecutor.execute(schedulerTempTaskQueryListCommand);
    }


    @Autowired
    public void setSchedulerTempTaskQueryCommandExecutor(SchedulerTempTaskQueryCommandExecutor schedulerTempTaskQueryCommandExecutor) {
        this.schedulerTempTaskQueryCommandExecutor = schedulerTempTaskQueryCommandExecutor;
    }
}
