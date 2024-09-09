package com.particle.scheduler.infrastructure.schedule.listener.quartz;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * Created by yangwei
 * Created at 2021/2/3 14:41
 */
@Slf4j
public class QuartzScheduleLogListener implements SchedulerListener {
    @Override
    public void jobScheduled(Trigger trigger) {
        log.info("任务计划,触发器已排期 triggerName={},triggerGroup={},jobName={},jobGroup={},nextFireAt={}"
                ,trigger.getKey().getName()
                ,trigger.getKey().getGroup()
                ,trigger.getJobKey().getName()
                ,trigger.getJobKey().getGroup()
                , DateUtil.formatDateTime(trigger.getNextFireTime())
        );
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        log.info("任务计划，触发器取消 triggerName={},triggerGroup={}"
                ,triggerKey.getName()
                ,triggerKey.getGroup()
        );
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        log.info("任务计划，触发器最后一执行完毕  triggerName={},triggerGroup={},jobName={},jobGroup={}"
                ,trigger.getKey().getName()
                ,trigger.getKey().getGroup()
                ,trigger.getJobKey().getName()
                ,trigger.getJobKey().getGroup()
        );
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        log.info("任务计划，触发器暂停  triggerName={},triggerGroup={}"
                ,triggerKey.getName()
                ,triggerKey.getGroup()
        );
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        log.info("任务计划，触发器们暂停  triggerGroup={}"
                ,triggerGroup
        );
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        log.info("任务计划，触发器恢复  triggerName={},triggerGroup={}"
                ,triggerKey.getName()
                ,triggerKey.getGroup()
        );
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        log.info("任务计划，触发器们恢复  triggerGroup={}"
                ,triggerGroup
        );
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        log.info("任务计划，任务已添加  jobName={},jobGroup={},jobDescription={},jobClassName={}"
                ,jobDetail.getKey().getName()
                ,jobDetail.getKey().getGroup()
                ,jobDetail.getDescription()
                ,jobDetail.getJobClass().getName()
        );
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        log.info("任务计划，任务已删除  jobName={},jobGroup={}"
                ,jobKey.getName()
                ,jobKey.getGroup()
        );
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        log.info("任务计划，任务已暂停  jobName={},jobGroup={}"
                ,jobKey.getName()
                ,jobKey.getGroup()
        );
    }

    @Override
    public void jobsPaused(String jobGroup) {
        log.info("任务计划，任务们已暂停  jobGroup={}"
                ,jobGroup
        );
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        log.info("任务计划，任务已恢复  jobName={},jobGroup={}"
                ,jobKey.getName()
                ,jobKey.getGroup()
        );
    }

    @Override
    public void jobsResumed(String jobGroup) {
        log.info("任务计划，任务们已恢复  jobGroup={}"
                ,jobGroup
        );
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        log.error("任务计划，异常  msg={}"
                ,msg,cause
        );
    }

    @Override
    public void schedulerInStandbyMode() {
        log.info("任务计划，已处于挂起状态");
    }

    @Override
    public void schedulerStarted() {
        log.info("任务计划，已启动");
    }

    @Override
    public void schedulerStarting() {
        log.info("任务计划，启动中...");
    }

    @Override
    public void schedulerShutdown() {
        log.info("任务计划，已关闭");
    }

    @Override
    public void schedulerShuttingdown() {
        log.info("任务计划，关闭中...");
    }

    @Override
    public void schedulingDataCleared() {
        log.info("任务计划，数据已清理完毕");
    }
}
