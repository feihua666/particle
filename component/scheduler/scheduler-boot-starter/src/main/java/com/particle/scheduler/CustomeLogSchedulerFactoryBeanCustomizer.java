package com.particle.scheduler;

import com.particle.scheduler.infrastructure.schedule.listener.quartz.QuartzJobLogListener;
import com.particle.scheduler.infrastructure.schedule.listener.quartz.QuartzScheduleLogListener;
import com.particle.scheduler.infrastructure.schedule.listener.quartz.QuartzTriggerLogListener;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p>
 * 自定义全局日志监听器
 * </p>
 *
 * @author yangwei
 * @since 2024/9/3 17:49
 */
public class CustomeLogSchedulerFactoryBeanCustomizer implements SchedulerFactoryBeanCustomizer {
    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setSchedulerListeners(new QuartzScheduleLogListener());
        schedulerFactoryBean.setGlobalJobListeners(new QuartzJobLogListener());
        schedulerFactoryBean.setGlobalTriggerListeners(new QuartzTriggerLogListener());

    }
}
