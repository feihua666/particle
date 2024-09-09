package com.particle.scheduler.client.api;

import com.particle.scheduler.client.dto.command.*;
import com.particle.scheduler.client.dto.data.JobDetailExtVo;
import com.particle.scheduler.client.dto.data.JobDetailVo;
import com.particle.scheduler.client.dto.data.ScheduleVo;
import com.particle.scheduler.client.dto.data.TriggerVo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
/**
 * <p>
 * 任务计划应用服务
 * </p>
 *
 * @author yangwei
 * @since 2024/8/30 16:09
 */
public interface ISchedulerApplicationService {

    /**
     * 获取唯一的任务计划实例
     * @param scheduleCommand
     * @return
     */
    ScheduleVo getUniqueScheduler(ScheduleCommand scheduleCommand);

    /**
     * 作为计划列表
     * @param scheduleQueryCommand
     * @return
     */
    List<ScheduleVo> scheduleList(ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 挂起任务计划
     * @param scheduleCommand
     * @return
     */
    Boolean standbySchedule(ScheduleCommand scheduleCommand);

    /**
     * 启动任务计划
     * @param scheduleCommand
     * @return
     */
    Boolean startSchedule(ScheduleCommand scheduleCommand);

    /**
     * 关闭任务计划
     * @param scheduleShutdownCommand
     * @return
     */
    Boolean shutdownSchedule( ScheduleShutdownCommand scheduleShutdownCommand);


    /**
     * 添加任务
     * @param addCommand
     * @return
     */
    JobDetailVo addJob(JobCronAddCommand addCommand);

    /**
     * 复制任务
     * @param nameAndGroupCommand
     * @param scheduleQueryCommand
     * @return
     */
    JobDetailVo copyJob(NameAndGroupCommand nameAndGroupCommand,ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 更新任务
     * @param updateCommand
     * @return
     */
    JobDetailVo updateJob(JobCronUpdateCommand updateCommand);

    /**
     * 删除job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    Boolean deleteJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 暂停job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    Boolean pauseJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 恢复job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    Boolean resumeJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 手动执行一次任务
     * @param nameAndGroupCommand
     * @param scheduleCommand
     * @return
     */
    Boolean executeJobOnce(NameAndGroupCommand nameAndGroupCommand,ScheduleCommand scheduleCommand);

    /**
     * 获取任务详情
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    JobDetailExtVo jobExtDetail(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 任务列表
     * @param jobQueryCommand
     * @param scheduleQueryCommand
     * @return
     */
    List<JobDetailVo> jobDetailList(JobQueryCommand jobQueryCommand, ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 暂停触发器
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    Boolean pauseTrigger(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 恢复触发器
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    Boolean resumeTrigger(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand);

    /**
     * 触发器列表
     *
     * @param triggerQueryCommand
     * @param scheduleQueryCommand
     * @return
     */
    List<TriggerVo> triggerList(TriggerQueryCommand triggerQueryCommand, ScheduleQueryCommand scheduleQueryCommand);
}
