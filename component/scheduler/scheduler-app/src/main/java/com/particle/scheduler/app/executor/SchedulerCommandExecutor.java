package com.particle.scheduler.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.exception.Assert;
import com.particle.scheduler.app.structmapping.ScheduleAppStructMapping;
import com.particle.scheduler.client.dto.command.*;
import com.particle.scheduler.client.dto.data.JobDetailExtVo;
import com.particle.scheduler.client.dto.data.JobDetailVo;
import com.particle.scheduler.client.dto.data.ScheduleVo;
import com.particle.scheduler.client.dto.data.TriggerVo;
import com.particle.scheduler.domain.gateway.SchedulerGateway;
import com.particle.scheduler.domain.value.*;
import com.particle.scheduler.infrastructure.job.quartzjob.HttpInvokerQuartzJob;
import com.particle.scheduler.infrastructure.job.quartzjob.ScriptInvokerQuartzJob;
import com.particle.scheduler.infrastructure.job.quartzjob.SpringBeanInvokerQuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 任务计划相关指令执行器
 * </p>
 *
 * @author yangwei
 * @since 2024/8/30 17:10
 */
@Component
@Validated
public class SchedulerCommandExecutor {

    private SchedulerGateway schedulerGateway;
    /**
     * 获取唯一的任务计划实例
     * @param scheduleCommand
     * @return
     */
    public ScheduleVo getUniqueScheduler(ScheduleCommand scheduleCommand){
        ScheduleQueryCommand scheduleQueryCommand = new ScheduleQueryCommand();
        scheduleQueryCommand.setSchedulerInstanceId(scheduleCommand.getSchedulerInstanceId());
        scheduleQueryCommand.setSchedulerName(scheduleCommand.getSchedulerName());
        List<ScheduleVo> scheduleVos = scheduleList(scheduleQueryCommand);
        Assert.notEmpty(scheduleVos,"任务计划不存在");
        Assert.isTrue(scheduleVos.size() == 1,"任务计划存在多个，这可能是系统内部错误");
        return scheduleVos.iterator().next();
    }

    /**
     * 作为计划列表
     * @param scheduleQueryCommand
     * @return
     */
    public List<ScheduleVo> scheduleList(ScheduleQueryCommand scheduleQueryCommand){
        List<ScheduleDTO> schedules = schedulerGateway.schedules(ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleQueryParam(scheduleQueryCommand));
        List<ScheduleVo> scheduleVos = ScheduleAppStructMapping.INSTANCE.scheduleDTOsToScheduleVos(schedules);
        return scheduleVos;
    }

    /**
     * 挂起任务计划
     * @param scheduleCommand
     * @return
     */
    public Boolean standbySchedule(ScheduleCommand scheduleCommand){
        return schedulerGateway.standbySchedule(ScheduleAppStructMapping.INSTANCE.scheduleCommandToScheduleParam(scheduleCommand));
    }

    /**
     * 启动任务计划
     * @param scheduleCommand
     * @return
     */
    public Boolean startSchedule(ScheduleCommand scheduleCommand){
        return schedulerGateway.startSchedule(ScheduleAppStructMapping.INSTANCE.scheduleCommandToScheduleParam(scheduleCommand));
    }

    /**
     * 关闭任务计划
     * @param scheduleShutdownCommand
     * @return
     */
    public Boolean shutdownSchedule( ScheduleShutdownCommand scheduleShutdownCommand){
        ScheduleParam scheduleParam = ScheduleAppStructMapping.INSTANCE.scheduleShutdownCommandToScheduleParam(scheduleShutdownCommand);
        return schedulerGateway.shutdownSchedule(
                Optional.ofNullable(scheduleShutdownCommand.getIsWaitForJobsToComplete()).orElse(false),
                scheduleParam
        );
    }


    /**
     * 添加任务
     * @param addCommand
     * @return
     */
    public JobDetailVo addJob(JobCronAddCommand addCommand){
        boolean b = schedulerGateway.addJob(ScheduleAppStructMapping.INSTANCE.jobCronAddCommandToJobCronAddParam(addCommand));
        if (b) {
            JobQueryCommand jobQueryCommand = ScheduleAppStructMapping.INSTANCE.jobCronAddCommandToJobQueryCommand(addCommand);
            ScheduleQueryCommand scheduleQueryCommand = ScheduleAppStructMapping.INSTANCE.jobCronAddCommandToScheduleQueryCommand(addCommand);
            return jobDetailOne(jobQueryCommand, scheduleQueryCommand);
        }else {
            return null;
        }
    }

    /**
     * 复制任务
     * @param nameAndGroupCommand
     * @param scheduleQueryCommand
     * @return
     */
    public JobDetailVo copyJob(NameAndGroupCommand nameAndGroupCommand, ScheduleQueryCommand scheduleQueryCommand){
        NameAndGroupCommand nameAndGroupCommandCopy = nameAndGroupCommand.createCopy();
        NameAndGroupCopyParam nameAndGroupCopyParam = ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupCopyParam(nameAndGroupCommand);
        nameAndGroupCopyParam.setCopyName(nameAndGroupCommandCopy.getName());
        nameAndGroupCopyParam.setCopyGroup(nameAndGroupCommandCopy.getGroup());

        boolean b = schedulerGateway.copyJob(
                nameAndGroupCopyParam,
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
        if (b) {
            JobQueryCommand jobQueryCommand = ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToJobQueryCommand(nameAndGroupCommandCopy);
            return jobDetailOne(jobQueryCommand, scheduleQueryCommand);
        }else {
            return null;
        }
    }

    /**
     * 更新任务
     * @param updateCommand
     * @return
     */
    public JobDetailVo updateJob(JobCronUpdateCommand updateCommand){
        boolean b = schedulerGateway.updateJob(
                ScheduleAppStructMapping.INSTANCE.jobCronUpdateCommandToJobCronUpdateParam(updateCommand)
                );

        if (b) {
            JobQueryCommand jobQueryCommand = ScheduleAppStructMapping.INSTANCE.jobCronUpdateCommandToJobQueryCommand(updateCommand);
            ScheduleQueryCommand scheduleQueryCommand = ScheduleAppStructMapping.INSTANCE.jobCronUpdateCommandToScheduleQueryCommand(updateCommand);
            return jobDetailOne(jobQueryCommand, scheduleQueryCommand);
        }else {
            return null;
        }
    }

    /**
     * 删除job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public Boolean deleteJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand){
        boolean b = schedulerGateway.deleteJob(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(addCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
        return b;
    }

    /**
     * 暂停job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public Boolean pauseJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand){
        boolean b = schedulerGateway.pauseJob(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(addCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
        return b;
    }

    /**
     * 恢复job
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public Boolean resumeJob(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand){
        boolean b = schedulerGateway.resumeJob(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(addCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
        return b;
    }

    /**
     * 手动执行一次任务
     * @param nameAndGroupCommand
     * @param scheduleCommand
     * @return
     */
    public Boolean executeJobOnce(NameAndGroupCommand nameAndGroupCommand,ScheduleCommand scheduleCommand){
        boolean b = schedulerGateway.executeOnce(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(nameAndGroupCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleCommandToScheduleParam(scheduleCommand)
        );
        return b;
    }

    /**
     * 获取任务详情
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public JobDetailExtVo jobExtDetail(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand){
        List<JobDetailVo> jobDetailVos = jobDetailList(ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToJobQueryCommand(addCommand), scheduleQueryCommand);
        Assert.notEmpty(jobDetailVos,"任务不存在");
        Assert.isTrue(jobDetailVos.size() == 1,"任务存在多个，这可能是系统内部错误");

        List<TriggerVo> triggerVos = triggerList(ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToTriggerQueryCommand(addCommand), scheduleQueryCommand);
        Assert.notEmpty(triggerVos,"触发器不存在");
        Assert.isTrue(triggerVos.size() == 1,"触发器存在多个，这可能是系统内部错误");

        JobDetailVo jobDetailVo = jobDetailVos.iterator().next();
        TriggerVo triggerVo = triggerVos.iterator().next();

        JobDetailExtVo jobDetailExtVo = ScheduleAppStructMapping.INSTANCE.jobDetailVoToJobDetailExtVo(jobDetailVo);
        jobDetailExtVo.setCronExpression(triggerVo.getCronExpression());

        if (HttpInvokerQuartzJob.class.getName().equals(jobDetailExtVo.getJobClassName())) {
            jobDetailExtVo.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_HTTP);
        }else if (SpringBeanInvokerQuartzJob.class.getName().equals(jobDetailExtVo.getJobClassName())) {
            jobDetailExtVo.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_BEAN);
        }else if (ScriptInvokerQuartzJob.class.getName().equals(jobDetailExtVo.getJobClassName())) {
            jobDetailExtVo.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_SCRIPT);
        }else {
            jobDetailExtVo.setJobClassType(JobCronAddParam.JOB_CLASS_TYPE_CUSTOM);
        }
        // 额外参数信息
        if (jobDetailExtVo.getDataMap() != null) {

            jobDetailExtVo.setHttpUrl((String) jobDetailExtVo.getDataMap().get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpUrl.name()));
            jobDetailExtVo.setHttpMethod((String) jobDetailExtVo.getDataMap().get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpMethod.name()));
            jobDetailExtVo.setHttpHeaders((Map<String, String>) jobDetailExtVo.getDataMap().get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpHeaders.name()));
            jobDetailExtVo.setHttpParams((Map<String, Object>) jobDetailExtVo.getDataMap().get(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.httpParams.name()));

            jobDetailExtVo.setBeanName((String) jobDetailExtVo.getDataMap().get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanName.name()));
            jobDetailExtVo.setBeanMethodName((String) jobDetailExtVo.getDataMap().get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodName.name()));
            jobDetailExtVo.setBeanMethodParams((List) jobDetailExtVo.getDataMap().get(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.beanMethodParams.name()));

            jobDetailExtVo.setScriptType((String) jobDetailExtVo.getDataMap().get(ScriptInvokerQuartzJob.ScriptInvokerQuartzJobDataMapKeys.scriptType.name()));
            jobDetailExtVo.setScriptContent((String) jobDetailExtVo.getDataMap().get(ScriptInvokerQuartzJob.ScriptInvokerQuartzJobDataMapKeys.scriptContent.name()));

            Arrays.stream(HttpInvokerQuartzJob.HttpInvokerQuartzJobDataMapKeys.values()).forEach(item-> jobDetailExtVo.getDataMap().remove(item.name()));
            Arrays.stream(SpringBeanInvokerQuartzJob.SpringBeanInvokerQuartzJobDataMapKeys.values()).forEach(item-> jobDetailExtVo.getDataMap().remove(item.name()));
            Arrays.stream(ScriptInvokerQuartzJob.ScriptInvokerQuartzJobDataMapKeys.values()).forEach(item-> jobDetailExtVo.getDataMap().remove(item.name()));
        }
        return jobDetailExtVo;
    }

    /**
     * 任务列表
     * @param jobQueryCommand
     * @param scheduleQueryCommand
     * @return
     */
    public List<JobDetailVo> jobDetailList(JobQueryCommand jobQueryCommand, ScheduleQueryCommand scheduleQueryCommand){
        ScheduleQueryParam scheduleQueryParam = ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleQueryParam(scheduleQueryCommand);
        JobQueryParam jobQueryParam = ScheduleAppStructMapping.INSTANCE.jobQueryCommandToJobQueryParam(jobQueryCommand);

        List<JobDetailDTO> jobDetailDTOs = schedulerGateway.getJobs(jobQueryParam, scheduleQueryParam);
        List<JobDetailVo> jobDetailVos = ScheduleAppStructMapping.INSTANCE.jobDetailDTOsToJobDetailVos(jobDetailDTOs);

        return jobDetailVos;
    }

    /**
     * 获取一个任务详情
     * @param jobQueryCommand
     * @param scheduleQueryCommand
     * @return
     */
    private JobDetailVo jobDetailOne(JobQueryCommand jobQueryCommand, ScheduleQueryCommand scheduleQueryCommand){
        List<JobDetailVo> jobDetailVos = jobDetailList(jobQueryCommand, scheduleQueryCommand);
        if (CollectionUtil.isEmpty(jobDetailVos)) {
            return null;
        }
        return jobDetailVos.iterator().next();
    }
    /**
     * 暂停触发器
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public Boolean pauseTrigger(NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand){
        return schedulerGateway.pauseTrigger(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(addCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
    }

    /**
     * 恢复触发器
     * @param addCommand
     * @param scheduleQueryCommand
     * @return
     */
    public Boolean resumeTrigger(NameAndGroupCommand addCommand,ScheduleQueryCommand scheduleQueryCommand){
        return schedulerGateway.resumeTrigger(
                ScheduleAppStructMapping.INSTANCE.nameAndGroupCommandToNameAndGroupParam(addCommand),
                ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleParam(scheduleQueryCommand)
        );
    }

    /**
     * 触发器列表
     *
     * @param triggerQueryCommand
     * @param scheduleQueryCommand
     * @return
     */
    public List<TriggerVo> triggerList(TriggerQueryCommand triggerQueryCommand, ScheduleQueryCommand scheduleQueryCommand) {
        ScheduleQueryParam scheduleQueryParam = ScheduleAppStructMapping.INSTANCE.scheduleQueryCommandToScheduleQueryParam(scheduleQueryCommand);
        TriggerQueryParam triggerQueryParam = ScheduleAppStructMapping.INSTANCE.triggerQueryCommandToTriggerQueryParam(triggerQueryCommand);

        List<TriggerDTO> triggerDTOS = schedulerGateway.getTriggers(triggerQueryParam, scheduleQueryParam);
        List<TriggerVo> triggerVos = ScheduleAppStructMapping.INSTANCE.triggerDTOsToTriggerVos(triggerDTOS);

        return triggerVos;
    }

    @Autowired
    public void setSchedulerGateway(SchedulerGateway schedulerGateway) {
        this.schedulerGateway = schedulerGateway;
    }
}
