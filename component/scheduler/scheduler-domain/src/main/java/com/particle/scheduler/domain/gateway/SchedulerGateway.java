package com.particle.scheduler.domain.gateway;


import com.particle.scheduler.domain.value.*;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2021/2/2 12:57
 */
public interface SchedulerGateway {

    /**
     * 列出计划
     * @return
     */
    List<ScheduleDTO> schedules();

    /**
     * 查询计划
     * @param scheduleQueryParam
     * @return
     */
    List<ScheduleDTO> schedules( ScheduleQueryParam scheduleQueryParam);

    /**
     * 创建job
     * @param addJobParam
     * @return
     * @throws Exception
     */
    boolean addJob(JobCronAddParam addJobParam);
    /**
     * 复制 job
     * @param nameAndGroupCopyParam
     * @return
     * @throws Exception
     */
    boolean copyJob(NameAndGroupCopyParam nameAndGroupCopyParam, ScheduleParam scheduleParam);

    /**
     * 执行一次任务
     * 重新安排一个触发器，执行一次任务
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean executeOnce(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);

    /**
     * 暂停job
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean pauseJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);
    /**
     * 暂停触发器
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean pauseTrigger(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);

    /**
     * 恢复job，job暂停后可以恢复
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean resumeJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);
    /**
     * 恢复触发器，触发器暂停后可以恢复
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean resumeTrigger(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);


    /**
     * job 更新
     * @param updateJobParam
     * @return
     * @throws Exception
     */
    boolean updateJob(JobCronUpdateParam updateJobParam);


    /**
     * job 删除
     * @param nameAndGroupParam
     * @param scheduleParam
     * @return
     */
    boolean deleteJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam);


    /**
     * 启动任务计划
     * @throws Exception
     */
    boolean startSchedule(ScheduleParam scheduleParam);
    /**
     * 挂起任务计划
     * @throws Exception
     */
    boolean standbySchedule(ScheduleParam scheduleParam);

    /**
     * 停止任务计划
     * @param waitForJobsToComplete 是否等待任务执行完成
     * @param scheduleParam
     * @return
     */
    boolean shutdownSchedule(boolean waitForJobsToComplete, ScheduleParam scheduleParam);

    /**
     * 查询job
     * @param jobQueryParam
     * @return
     * @throws Exception
     */
    List<JobDetailDTO> getJobs(JobQueryParam jobQueryParam, ScheduleQueryParam scheduleQueryParam);

    /**
     * 查询触发器
     * @param triggerQueryParam
     * @return
     * @throws Exception
     */
    List<TriggerDTO> getTriggers(TriggerQueryParam triggerQueryParam, ScheduleQueryParam scheduleQueryParam);

}
