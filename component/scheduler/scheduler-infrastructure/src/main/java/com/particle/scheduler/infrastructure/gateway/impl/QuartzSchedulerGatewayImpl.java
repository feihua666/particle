package com.particle.scheduler.infrastructure.gateway.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.exception.biz.BizException;
import com.particle.scheduler.domain.gateway.SchedulerGateway;
import com.particle.scheduler.domain.schedule.value.SchedulerExecuteRecordClearParam;
import com.particle.scheduler.domain.value.*;
import com.particle.scheduler.infrastructure.job.quartzjob.HttpInvokerQuartzJob;
import com.particle.scheduler.infrastructure.job.quartzjob.ScriptInvokerQuartzJob;
import com.particle.scheduler.infrastructure.job.quartzjob.SpringBeanInvokerQuartzJob;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import com.particle.scheduler.infrastructure.structmapping.ScheduleInfrastructureStructMapping;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 基于 Quartz 的任务实现
 * </p>
 *
 * @author yangwei
 * @since 2024/8/30 17:20
 */
@Component
public class QuartzSchedulerGatewayImpl implements SchedulerGateway {

    private List<Scheduler> schedulers;

    private ISchedulerExecuteRecordService iSchedulerExecuteRecordService;
    /**
     * 获取唯一的任务计划实例
     * @param scheduleQueryParam
     * @return
     */
    private Scheduler getUniqueScheduler(ScheduleQueryParam scheduleQueryParam){
        List<Scheduler> schedulers = doSchedules(scheduleQueryParam);
        Assert.notEmpty(schedulers,"任务计划不存在");
        Assert.isTrue(schedulers.size() == 1,"任务计划存在多个，这可能是系统内部错误");
        return schedulers.iterator().next();
    }

    @SneakyThrows
    @Override
    public List<ScheduleDTO> schedules() {
        List<ScheduleDTO> result = new ArrayList<>(schedulers.size());
        for (Scheduler scheduler : schedulers) {
            ScheduleDTO scheduleDTO = ScheduleInfrastructureStructMapping.INSTANCE.mapScheduler(scheduler);
            result.add(scheduleDTO);
        }
        return result;
    }

    @SneakyThrows
    private List<Scheduler> doSchedules(ScheduleQueryParam scheduleQueryParam) {
        List<Scheduler> resultTemp = new ArrayList<>(schedulers.size());
        for (Scheduler scheduler : schedulers) {
            if (StrUtil.isEmpty(scheduleQueryParam.getSchedulerName()) || StrUtil.equals(scheduler.getSchedulerName(),scheduleQueryParam.getSchedulerName())) {
                resultTemp.add(scheduler);
            }
        }
        List<Scheduler> result = new ArrayList<>(resultTemp.size());
        for (Scheduler scheduler : resultTemp) {
            if (StrUtil.isEmpty(scheduleQueryParam.getSchedulerInstanceId()) || StrUtil.equals(scheduler.getSchedulerInstanceId(),scheduleQueryParam.getSchedulerInstanceId())) {
                result.add(scheduler);
            }
        }
        return result;
    }
    @SneakyThrows
    @Override
    public List<ScheduleDTO> schedules(ScheduleQueryParam scheduleQueryParam) {
        List<Scheduler> result = doSchedules(scheduleQueryParam);
        return ScheduleInfrastructureStructMapping.INSTANCE.mapSchedulers(result);
    }

    @SneakyThrows
    @Override
    public boolean addJob(JobCronAddParam addJobParam) {
        Class<? extends Job> clazz = null;

        Map<String, Object> jobDataMap = new HashMap<>();
        if (JobCronAddParam.JOB_CLASS_TYPE_CUSTOM.equals(addJobParam.getJobClassType())) {
            clazz = (Class<? extends Job>) Class.forName(addJobParam.getJobClassName());
        }else if (JobCronAddParam.JOB_CLASS_TYPE_HTTP.equals(addJobParam.getJobClassType())) {
            clazz = HttpInvokerQuartzJob.class;
            jobDataMap.put(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpUrl.name(), addJobParam.getHttpUrl());
            jobDataMap.put(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpMethod.name(), addJobParam.getHttpMethod());
            jobDataMap.put(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpHeaders.name(), addJobParam.getHttpHeaders());
            jobDataMap.put(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpParams.name(), addJobParam.getHttpParams());
        }else if (JobCronAddParam.JOB_CLASS_TYPE_BEAN.equals(addJobParam.getJobClassType())) {
            clazz = SpringBeanInvokerQuartzJob.class;
            jobDataMap.put(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanName.name(), addJobParam.getBeanName());
            jobDataMap.put(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodName.name(), addJobParam.getBeanMethodName());
            jobDataMap.put(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodParams.name(), addJobParam.getBeanMethodParams());
        }else if (JobCronAddParam.JOB_CLASS_TYPE_SCRIPT.equals(addJobParam.getJobClassType())) {
            clazz = ScriptInvokerQuartzJob.class;
            jobDataMap.put(ScriptInvokerQuartzJob.ScriptInvokerQuartzJobDataMapKeys.scriptType.name(), addJobParam.getScriptType());
            jobDataMap.put(ScriptInvokerQuartzJob.ScriptInvokerQuartzJobDataMapKeys.scriptContent.name(), addJobParam.getScriptContent());
        }
        if (clazz == null) {
            throw new BizException("目前暂不支持其它类型设置支持的类型");
        }
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(addJobParam.getName(), addJobParam.getGroup()).withDescription(addJobParam.getDescription()).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(addJobParam.getCronExpression());
        //按新的 cronExpression 表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(addJobParam.getName(), addJobParam.getGroup())
                .withSchedule(scheduleBuilder)
                .withDescription("任务默认新建触发器").build();
        //获得JobDataMap，写入数据
        if (addJobParam.getDataMap() != null) {
            jobDetail.getJobDataMap().putAll(addJobParam.getDataMap());
        }
        jobDetail.getJobDataMap().putAll(jobDataMap);
        // scheduler.scheduleJob 会存储 job 如果 name 和 group 已存在会报异常
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(addJobParam.getSchedulerName(), addJobParam.getSchedulerInstanceId()));
        uniqueScheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    @SneakyThrows
    @Override
    public boolean copyJob(NameAndGroupCopyParam nameAndGroupCopyParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));

        JobDetail jobDetail = uniqueScheduler.getJobDetail(JobKey.jobKey(nameAndGroupCopyParam.getName(), nameAndGroupCopyParam.getGroup()));
        JobCronAddParam jobCronAddParam = new JobCronAddParam();
        jobCronAddParam.setSchedulerName(uniqueScheduler.getSchedulerName());
        jobCronAddParam.setSchedulerInstanceId(uniqueScheduler.getSchedulerInstanceId());

        Class<? extends Job> jobClass = jobDetail.getJobClass();
        if (HttpInvokerQuartzJob.class.equals(jobClass)) {
            jobCronAddParam.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_HTTP);
        }else if (SpringBeanInvokerQuartzJob.class.equals(jobClass)) {
            jobCronAddParam.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_BEAN);
        }else if (ScriptInvokerQuartzJob.class.equals(jobClass)) {
            jobCronAddParam.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_SCRIPT);
        }else {
            jobCronAddParam.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_CUSTOM);
        }
        jobCronAddParam.setJobClassName(jobClass.getName());

        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        String httpUrl = Optional.ofNullable(jobDataMap.get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpUrl.name())).map(Objects::toString).orElse(null);
        String httpMethod = Optional.ofNullable(jobDataMap.get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpMethod.name())).map(Objects::toString).orElse(null);
        Map<String,String> httpHeaders = ((Map) (jobDataMap.get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpHeaders.name())));
        Map<String,Object> httpParams = ((Map) (jobDataMap.get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpParams.name())));


        String beanName = Optional.ofNullable(jobDataMap.get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanName.name())).map(Objects::toString).orElse(null);
        String beanMethodName = Optional.ofNullable(jobDataMap.get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodName.name())).map(Objects::toString).orElse(null);
        List beanMethodParams = (List)jobDataMap.get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodParams.name());

        jobCronAddParam.setHttpUrl(httpUrl);
        jobCronAddParam.setHttpMethod(httpMethod);
        jobCronAddParam.setHttpHeaders(httpHeaders);
        jobCronAddParam.setHttpParams(httpParams);

        jobCronAddParam.setBeanName(beanName);
        jobCronAddParam.setBeanMethodName(beanMethodName);
        jobCronAddParam.setBeanMethodParams(beanMethodParams);

        TriggerKey triggerKey = TriggerKey.triggerKey(jobDetail.getKey().getName(), jobDetail.getKey().getGroup());

        Trigger trigger =  uniqueScheduler.getTrigger(triggerKey);
        if( trigger instanceof CronTrigger){
            jobCronAddParam.setCronExpression(((CronTrigger) trigger).getCronExpression());

        }

        jobCronAddParam.setName(nameAndGroupCopyParam.getCopyName());
        jobCronAddParam.setGroup(nameAndGroupCopyParam.getCopyGroup());

        jobCronAddParam.setDescription(jobDetail.getDescription());
        return addJob(jobCronAddParam);
    }

    @SneakyThrows
    @Override
    public boolean executeOnce(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));

        JobDetail jobDetail = uniqueScheduler.getJobDetail(JobKey.jobKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));
        String stuffix = UUID.fastUUID().toString(true);
        Trigger trigger = TriggerBuilder.newTrigger().startNow()

                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(0))
                .withIdentity(nameAndGroupParam.getName() + "-" + stuffix, nameAndGroupParam.getGroup() + "-" + stuffix)
                .withDescription("executeOnce")
                .forJob(jobDetail)
                .build();
        uniqueScheduler.scheduleJob(trigger);
        return true;
    }

    @SneakyThrows
    @Override
    public boolean pauseJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.pauseJob(JobKey.jobKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));
        return true;
    }

    @SneakyThrows
    @Override
    public boolean pauseTrigger(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.pauseTrigger(TriggerKey.triggerKey(nameAndGroupParam.getName(),nameAndGroupParam.getGroup()));
        return true;
    }

    @SneakyThrows
    @Override
    public boolean resumeJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.resumeJob(JobKey.jobKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));
        return true;
    }

    @SneakyThrows
    @Override
    public boolean resumeTrigger(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.resumeTrigger(TriggerKey.triggerKey(nameAndGroupParam.getName(),nameAndGroupParam.getGroup()));
        return true;
    }

    @Override
    public boolean updateJob(JobCronUpdateParam updateJobParam) {
        NameAndGroupParam nameAndGroupParam = new NameAndGroupParam();
        nameAndGroupParam.setName(updateJobParam.getOldName());
        nameAndGroupParam.setGroup(updateJobParam.getOldGroup());
        // 删除job，重新添加
        deleteJob(nameAndGroupParam,ScheduleParam.create(updateJobParam.getSchedulerName(), updateJobParam.getSchedulerInstanceId()));

        JobCronAddParam addJobParam = ScheduleInfrastructureStructMapping.INSTANCE.JobCronUpdateParamToJobCronAddParam(updateJobParam);
        return addJob(addJobParam);
    }

    @SneakyThrows
    @Override
    public boolean deleteJob(NameAndGroupParam nameAndGroupParam, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.pauseTrigger(TriggerKey.triggerKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));
        uniqueScheduler.unscheduleJob(TriggerKey.triggerKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));
        uniqueScheduler.deleteJob(JobKey.jobKey(nameAndGroupParam.getName(), nameAndGroupParam.getGroup()));

        // 清理对应的日志
        SchedulerExecuteRecordClearParam schedulerRecordClearForm = new SchedulerExecuteRecordClearParam();
        schedulerRecordClearForm.setName(nameAndGroupParam.getName());
        schedulerRecordClearForm.setGroup(nameAndGroupParam.getGroup());
        schedulerRecordClearForm.setBeforeAt(LocalDateTime.now());
        iSchedulerExecuteRecordService.clear(schedulerRecordClearForm);
        return true;
    }

    @SneakyThrows
    @Override
    public boolean startSchedule(ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.start();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean standbySchedule(ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        uniqueScheduler.standby();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean shutdownSchedule(boolean waitForJobsToComplete, ScheduleParam scheduleParam) {
        Scheduler uniqueScheduler = getUniqueScheduler(ScheduleQueryParam.create(scheduleParam.getSchedulerName(), scheduleParam.getSchedulerInstanceId()));
        if (!uniqueScheduler.isShutdown()) {
            uniqueScheduler.shutdown(waitForJobsToComplete);
        }
        return true;
    }

    @SneakyThrows
    @Override
    public List<JobDetailDTO> getJobs(JobQueryParam jobQueryParam, ScheduleQueryParam scheduleQueryParam) {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        if (StrUtil.isNotEmpty(jobQueryParam.getGroup())) {
            matcher = GroupMatcher.jobGroupEquals(jobQueryParam.getGroup());
        }
        List<JobDetailDTO> resultList = new ArrayList<>();

        List<Scheduler> schedulers = doSchedules(scheduleQueryParam);
        for (Scheduler scheduler : schedulers) {
            List<JobDetailDTO> oneSchedulerResultTempList = new ArrayList<>();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                if (StrUtil.isNotEmpty(jobQueryParam.getName()) && !StrUtil.equals(jobKey.getName(),jobQueryParam.getName())) {
                    continue;
                }
                oneSchedulerResultTempList.add(ScheduleInfrastructureStructMapping.INSTANCE.mapJobDetail(scheduler.getJobDetail(jobKey)));
            }
            String schedulerName = scheduler.getSchedulerName();
            String schedulerInstanceId = scheduler.getSchedulerInstanceId();
            // 填充 cronExpression
            for (JobDetailDTO jobDetailDTO : oneSchedulerResultTempList) {
                Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(jobDetailDTO.getName(), jobDetailDTO.getGroup()));
                if (trigger instanceof CronTrigger) {
                    jobDetailDTO.setCronExpression(((CronTrigger) trigger).getCronExpression());
                }
                jobDetailDTO.setSchedulerName(schedulerName);
                jobDetailDTO.setSchedulerInstanceId(schedulerInstanceId);
            }

            resultList.addAll(oneSchedulerResultTempList);
        }


        return resultList;
    }

    @SneakyThrows
    @Override
    public List<TriggerDTO> getTriggers(TriggerQueryParam triggerQueryParam, ScheduleQueryParam scheduleQueryParam) {
        GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
        if (StrUtil.isNotEmpty(triggerQueryParam.getGroup())) {
            matcher = GroupMatcher.triggerGroupEquals(triggerQueryParam.getGroup());
        }
        List<TriggerDTO> triggerVoList = new ArrayList<>();


        List<Scheduler> schedulers = doSchedules(scheduleQueryParam);
        for (Scheduler scheduler : schedulers) {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(matcher);
            for (TriggerKey triggerKey : triggerKeys) {
                if (StrUtil.isNotEmpty(triggerQueryParam.getName()) && !StrUtil.equals(triggerKey.getName(),triggerQueryParam.getName())) {
                    continue;
                }
                triggerVoList.add(ScheduleInfrastructureStructMapping.INSTANCE.mapTrigger(scheduler.getTrigger(triggerKey),scheduler));
            }
        }


        return triggerVoList;
    }

    @Autowired
    public void setSchedulers(List<Scheduler> schedulers) {
        this.schedulers = schedulers;
    }
    @Autowired
    public void setiSchedulerExecuteRecordService(ISchedulerExecuteRecordService iSchedulerExecuteRecordService) {
        this.iSchedulerExecuteRecordService = iSchedulerExecuteRecordService;
    }
}
