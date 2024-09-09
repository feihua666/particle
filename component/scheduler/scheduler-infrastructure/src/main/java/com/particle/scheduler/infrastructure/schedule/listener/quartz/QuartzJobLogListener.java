package com.particle.scheduler.infrastructure.schedule.listener.quartz;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by yangwei
 * Created at 2021/2/3 10:08
 */
@Slf4j
public class QuartzJobLogListener implements JobListener {
    @Override
    public String getName() {
        return "全局任务监听";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("任务开始执行 name={},group={},previousFireAt={},nextFireAt={},jobClassName={}"
                ,context.getJobDetail().getKey().getName()
                ,context.getJobDetail().getKey().getGroup()
                , DateUtil.formatDateTime(context.getPreviousFireTime())
                , DateUtil.formatDateTime(context.getNextFireTime())
                ,context.getJobInstance().getClass().getName());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("任务被投票终止（触发器投票不执行） name={},group=[],previousFireAt={},nextFireAt={},jobClassName={}"
                ,context.getJobDetail().getKey().getName()
                ,context.getJobDetail().getKey().getGroup()
                , DateUtil.formatDateTime(context.getPreviousFireTime())
                , DateUtil.formatDateTime(context.getNextFireTime())
                ,context.getJobInstance().getClass().getName());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("任务执行完成 name={},group=[],previousFireAt={},nextFireAt={},jobClassName={}"
                ,context.getJobDetail().getKey().getName()
                ,context.getJobDetail().getKey().getGroup()
                , DateUtil.formatDateTime(context.getPreviousFireTime())
                , DateUtil.formatDateTime(context.getNextFireTime())
                ,context.getJobInstance().getClass().getName());
    }
}
