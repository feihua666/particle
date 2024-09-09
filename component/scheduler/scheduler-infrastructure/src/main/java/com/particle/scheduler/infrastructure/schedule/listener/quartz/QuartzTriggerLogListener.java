package com.particle.scheduler.infrastructure.schedule.listener.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by yangwei
 * Created at 2021/2/3 13:23
 */
@Slf4j
public class QuartzTriggerLogListener implements TriggerListener {
    @Override
    public String getName() {
        return "全局触发器监听";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("触发器点火 name={},group={},jobName={},jobGroup={}"
                ,trigger.getKey().getName()
                ,trigger.getKey().getGroup()
                ,trigger.getJobKey().getName()
                ,trigger.getJobKey().getGroup()
                );

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        // 如果返回true任务会不执行，全局监听为false
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("触发器失火 name={},group={},jobName={},jobGroup={}"
                ,trigger.getKey().getName()
                ,trigger.getKey().getGroup()
                ,trigger.getJobKey().getName()
                ,trigger.getJobKey().getGroup()
        );
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        log.info("触发器点火完成，任务执行完毕 name={},group={},jobName={},jobGroup={}"
                ,trigger.getKey().getName()
                ,trigger.getKey().getGroup()
                ,trigger.getJobKey().getName()
                ,trigger.getJobKey().getGroup()
        );
    }
}
