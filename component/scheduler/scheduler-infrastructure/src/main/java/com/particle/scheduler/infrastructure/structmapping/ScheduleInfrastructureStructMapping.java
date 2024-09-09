package com.particle.scheduler.infrastructure.structmapping;

import com.particle.scheduler.domain.value.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.quartz.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2021/2/3 17:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleInfrastructureStructMapping {
    ScheduleInfrastructureStructMapping INSTANCE = Mappers.getMapper( ScheduleInfrastructureStructMapping.class );


    /**
     * scheduler 转 vo
     * @param scheduler
     * @return
     */
    default ScheduleDTO mapScheduler(Scheduler scheduler) throws SchedulerException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setSchedulerName(scheduler.getSchedulerName());
        scheduleDTO.setSchedulerInstanceId(scheduler.getSchedulerInstanceId());
        scheduleDTO.setScheduleContextDataMap(scheduler.getContext());
        scheduleDTO.setIsStarted(scheduler.isStarted());
        scheduleDTO.setIsInStandbyMode(scheduler.isInStandbyMode());
        scheduleDTO.setIsShutdown(scheduler.isShutdown());
        scheduleDTO.setScheduleMetaData(mapSchedulerMetaData(scheduler.getMetaData()));
        scheduleDTO.setTriggerListeners(mapTriggerListeners(scheduler.getListenerManager().getTriggerListeners()));
        scheduleDTO.setJobListeners(mapJobListeners(scheduler.getListenerManager().getJobListeners()));
        scheduleDTO.setScheduleListeners(mapScheduleListeners(scheduler.getListenerManager().getSchedulerListeners()));
        return scheduleDTO;
    }
    public List<ScheduleDTO> mapSchedulers(List<Scheduler> schedulers) throws SchedulerException;

    /**
     * TriggerListeners 转vo
     * @param schedulerListeners
     * @return
     */
    default List<TriggerListenerDTO> mapTriggerListeners(List<TriggerListener> schedulerListeners) {
        return schedulerListeners.stream().map(schedulerListener -> mapTriggerListener(schedulerListener)).collect(Collectors.toList());
    }

    /**
     * TriggerListener 转 vo
     * @param triggerListener
     * @return
     */
    default TriggerListenerDTO mapTriggerListener(TriggerListener triggerListener) {
        TriggerListenerDTO triggerListenerDTO = new TriggerListenerDTO();
        triggerListenerDTO.setClassName(triggerListener.getClass().getName());
        triggerListenerDTO.setName(triggerListener.getName());
        return triggerListenerDTO;
    }
    /**
     * JobListeners 转vo
     * @param schedulerListeners
     * @return
     */
    default List<JobListenerDTO> mapJobListeners(List<JobListener> schedulerListeners) {
        return schedulerListeners.stream().map(schedulerListener -> mapJobListener(schedulerListener)).collect(Collectors.toList());
    }

    /**
     * JobListener 转 vo
     * @param jobListener
     * @return
     */
    default JobListenerDTO mapJobListener(JobListener jobListener) {
        JobListenerDTO jobListenerDTO = new JobListenerDTO();
        jobListenerDTO.setClassName(jobListener.getClass().getName());
        jobListenerDTO.setName(jobListener.getName());
        return jobListenerDTO;
    }
    /**
     * SchedulerListeners 转vo
     * @param schedulerListeners
     * @return
     */
    default List<ScheduleListenerDTO> mapScheduleListeners(List<SchedulerListener> schedulerListeners) {
        return schedulerListeners.stream().map(schedulerListener -> mapScheduleListener(schedulerListener)).collect(Collectors.toList());
    }

    /**
     * SchedulerListener 转 vo
     * @param schedulerListener
     * @return
     */
    default ScheduleListenerDTO mapScheduleListener(SchedulerListener schedulerListener) {
        ScheduleListenerDTO scheduleListenerDTO = new ScheduleListenerDTO();
        scheduleListenerDTO.setClassName(schedulerListener.getClass().getName());
        return scheduleListenerDTO;
    }
    /**
     * SchedulerMetaData 转 vo
     * @param schedulerMetaData
     * @return
     */
    default ScheduleMetaDataDTO mapSchedulerMetaData(SchedulerMetaData schedulerMetaData) {
        ScheduleMetaDataDTO scheduleMetaDataDTO = new ScheduleMetaDataDTO();
        scheduleMetaDataDTO.setSchedulerName(schedulerMetaData.getSchedulerName());
        scheduleMetaDataDTO.setSchedulerInstanceId(schedulerMetaData.getSchedulerInstanceId());
        scheduleMetaDataDTO.setSchedulerClassName(schedulerMetaData.getSchedulerClass().getName());
        scheduleMetaDataDTO.setIsSchedulerRemote(schedulerMetaData.isSchedulerRemote());
        scheduleMetaDataDTO.setIsStarted(schedulerMetaData.isStarted());
        scheduleMetaDataDTO.setIsInStandbyMode(schedulerMetaData.isInStandbyMode());
        scheduleMetaDataDTO.setIsShutdown(schedulerMetaData.isShutdown());
        scheduleMetaDataDTO.setStartAt(schedulerMetaData.getRunningSince());
        scheduleMetaDataDTO.setNumberOfJobsExecuted(schedulerMetaData.getNumberOfJobsExecuted());
        scheduleMetaDataDTO.setJobStoreClassName(schedulerMetaData.getJobStoreClass().getName());
        scheduleMetaDataDTO.setIsJobStoreSupportsPersistence(schedulerMetaData.isJobStoreSupportsPersistence());
        scheduleMetaDataDTO.setIsJobStoreClustered(schedulerMetaData.isJobStoreClustered());
        scheduleMetaDataDTO.setThreadPoolClassName(schedulerMetaData.getThreadPoolClass().getName());
        scheduleMetaDataDTO.setThreadPoolSize(schedulerMetaData.getThreadPoolSize());
        scheduleMetaDataDTO.setVersion(schedulerMetaData.getVersion());
        return scheduleMetaDataDTO;
    }

    /**
     * Trigger 转 vo
     * @param trigger
     * @return
     */
    default TriggerDTO mapTrigger(Trigger trigger, Scheduler scheduler) throws SchedulerException {
        TriggerDTO triggerDTO = new TriggerDTO();
        triggerDTO.setSchedulerName(scheduler.getSchedulerName());
        triggerDTO.setSchedulerInstanceId(scheduler.getSchedulerInstanceId());
        triggerDTO.setName(trigger.getKey().getName());
        triggerDTO.setGroup(trigger.getKey().getGroup());
        triggerDTO.setTriggerClassName(trigger.getClass().getName());
        triggerDTO.setTriggerState(scheduler.getTriggerState(trigger.getKey()).name());
        triggerDTO.setCalendarName(trigger.getCalendarName());
        triggerDTO.setDescription(trigger.getDescription());
        triggerDTO.setPriority(trigger.getPriority());
        triggerDTO.setIsMayFireAgain(trigger.mayFireAgain());
        triggerDTO.setStartAt(trigger.getStartTime());
        triggerDTO.setEndAt(trigger.getEndTime());
        triggerDTO.setNextFireAt(trigger.getNextFireTime());
        triggerDTO.setPreviousFireAt(trigger.getPreviousFireTime());
        triggerDTO.setFinalFireAt(trigger.getFinalFireTime());
        triggerDTO.setMisfireInstruction(trigger.getMisfireInstruction());
        if (trigger instanceof CronTrigger) {
            triggerDTO.setCronExpression(((CronTrigger) trigger).getCronExpression());
        }
        return triggerDTO;
    }


    /**
     * JobDetail 转 vo
     * @param jobDetail
     * @return
     */
    default JobDetailDTO mapJobDetail(JobDetail jobDetail) {
        JobDetailDTO jobDetailDTO = new JobDetailDTO();
        jobDetailDTO.setName(jobDetail.getKey().getName());
        jobDetailDTO.setGroup(jobDetail.getKey().getGroup());
        jobDetailDTO.setJobClassName(jobDetail.getJobClass().getName());
        jobDetailDTO.setDescription(jobDetail.getDescription());
        jobDetailDTO.setIsDurable(jobDetail.isDurable());
        jobDetailDTO.setIsPersistJobDataAfterExecution(jobDetail.isPersistJobDataAfterExecution());
        jobDetailDTO.setIsConcurrentExectionDisallowed(jobDetail.isConcurrentExectionDisallowed());
        jobDetailDTO.setIsRecovery(jobDetail.requestsRecovery());
        jobDetailDTO.setDataMap(jobDetail.getJobDataMap());
        return jobDetailDTO;
    }

    public JobDetailExtDTO map( JobDetailDTO jobDetailDTO);

    public JobCronAddParam JobCronUpdateParamToJobCronAddParam(JobCronUpdateParam jobCronUpdateParam);
}
