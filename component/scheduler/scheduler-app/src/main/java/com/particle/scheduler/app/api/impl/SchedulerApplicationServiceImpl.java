package com.particle.scheduler.app.api.impl;

import com.particle.global.catchlog.CatchAndLog;
import com.particle.scheduler.app.executor.SchedulerCommandExecutor;
import com.particle.scheduler.client.api.ISchedulerApplicationService;
import com.particle.scheduler.client.dto.command.*;
import com.particle.scheduler.client.dto.data.JobDetailExtVo;
import com.particle.scheduler.client.dto.data.JobDetailVo;
import com.particle.scheduler.client.dto.data.ScheduleVo;
import com.particle.scheduler.client.dto.data.TriggerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务计划服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2024/8/30 17:07
 */
@Service
@CatchAndLog
public class SchedulerApplicationServiceImpl implements ISchedulerApplicationService {

    private SchedulerCommandExecutor schedulerCommandExecutor;

    @Override
    public ScheduleVo getUniqueScheduler(ScheduleCommand scheduleCommand) {
        return schedulerCommandExecutor.getUniqueScheduler(scheduleCommand);
    }

    @Override
    public List<ScheduleVo> scheduleList(ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.scheduleList(scheduleQueryCommand);
    }

    @Override
    public Boolean standbySchedule(ScheduleCommand scheduleCommand) {
        return schedulerCommandExecutor.standbySchedule(scheduleCommand);
    }

    @Override
    public Boolean startSchedule(ScheduleCommand scheduleCommand) {
        return schedulerCommandExecutor.startSchedule(scheduleCommand);
    }

    @Override
    public Boolean shutdownSchedule(ScheduleShutdownCommand scheduleShutdownCommand) {
        return schedulerCommandExecutor.shutdownSchedule(scheduleShutdownCommand);
    }

    @Override
    public JobDetailVo addJob(JobCronAddCommand addCommand) {
        return schedulerCommandExecutor.addJob(addCommand);
    }

    @Override
    public JobDetailVo copyJob(NameAndGroupCommand nameAndGroupCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.copyJob(nameAndGroupCommand,scheduleQueryCommand);
    }

    @Override
    public JobDetailVo updateJob(JobCronUpdateCommand updateCommand) {
        return schedulerCommandExecutor.updateJob(updateCommand);
    }

    @Override
    public Boolean deleteJob(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.deleteJob(addCommand,scheduleQueryCommand);
    }

    @Override
    public Boolean pauseJob(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.pauseJob(addCommand,scheduleQueryCommand);
    }

    @Override
    public Boolean resumeJob(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.resumeJob(addCommand,scheduleQueryCommand);
    }

    @Override
    public Boolean executeJobOnce(NameAndGroupCommand nameAndGroupCommand, ScheduleCommand scheduleCommand) {
        return schedulerCommandExecutor.executeJobOnce(nameAndGroupCommand,scheduleCommand);
    }

    @Override
    public JobDetailExtVo jobExtDetail(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.jobExtDetail(addCommand,scheduleQueryCommand);
    }

    @Override
    public List<JobDetailVo> jobDetailList(JobQueryCommand jobQueryCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.jobDetailList(jobQueryCommand,scheduleQueryCommand);
    }

    @Override
    public Boolean pauseTrigger(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.pauseTrigger(addCommand,scheduleQueryCommand);
    }

    @Override
    public Boolean resumeTrigger(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.resumeTrigger(addCommand,scheduleQueryCommand);
    }

    @Override
    public List<TriggerVo> triggerList(TriggerQueryCommand triggerQueryCommand, ScheduleQueryCommand scheduleQueryCommand) {
        return schedulerCommandExecutor.triggerList(triggerQueryCommand,scheduleQueryCommand);
    }

    @Autowired
    public void setSchedulerCommandExecutor(SchedulerCommandExecutor schedulerCommandExecutor) {
        this.schedulerCommandExecutor = schedulerCommandExecutor;
    }
}
