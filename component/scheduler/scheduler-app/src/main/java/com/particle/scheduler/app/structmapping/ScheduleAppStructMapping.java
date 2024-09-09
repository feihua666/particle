package com.particle.scheduler.app.structmapping;

import com.particle.scheduler.client.dto.command.*;
import com.particle.scheduler.client.dto.data.JobDetailExtVo;
import com.particle.scheduler.client.dto.data.JobDetailVo;
import com.particle.scheduler.client.dto.data.ScheduleVo;
import com.particle.scheduler.client.dto.data.TriggerVo;
import com.particle.scheduler.domain.value.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2021/2/3 17:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleAppStructMapping {
    ScheduleAppStructMapping INSTANCE = Mappers.getMapper( ScheduleAppStructMapping.class );

    public NameAndGroupParam nameAndGroupCommandToNameAndGroupParam(NameAndGroupCommand nameAndGroupCommand);

    public JobCronAddParam jobCronAddCommandToJobCronAddParam(JobCronAddCommand addCommand);
    public JobQueryCommand jobCronAddCommandToJobQueryCommand(JobCronAddCommand addCommand);
    public ScheduleCommand jobCronAddCommandToScheduleCommand(JobCronAddCommand addCommand);
    public ScheduleQueryCommand jobCronAddCommandToScheduleQueryCommand(JobCronAddCommand addCommand);


    public NameAndGroupCopyParam nameAndGroupCommandToNameAndGroupCopyParam(NameAndGroupCommand nameAndGroupCommand);
    public JobQueryCommand nameAndGroupCommandToJobQueryCommand(NameAndGroupCommand nameAndGroupCommand);
    public TriggerQueryCommand nameAndGroupCommandToTriggerQueryCommand(NameAndGroupCommand nameAndGroupCommand);

    public ScheduleParam scheduleQueryCommandToScheduleParam( ScheduleQueryCommand scheduleQueryCommand);
    public ScheduleQueryParam scheduleQueryCommandToScheduleQueryParam( ScheduleQueryCommand scheduleQueryCommand);


    public JobCronUpdateParam jobCronUpdateCommandToJobCronUpdateParam(JobCronUpdateCommand updateCommand);
    public JobQueryCommand jobCronUpdateCommandToJobQueryCommand(JobCronUpdateCommand updateCommand);
    public ScheduleQueryCommand jobCronUpdateCommandToScheduleQueryCommand(JobCronUpdateCommand updateCommand);

    public JobQueryParam jobQueryCommandToJobQueryParam(JobQueryCommand jobQueryCommand );
    public TriggerQueryParam triggerQueryCommandToTriggerQueryParam(TriggerQueryCommand triggerQueryCommand );


    public JobDetailExtVo jobDetailVoToJobDetailExtVo(JobDetailVo jobDetailVo);


    public JobDetailVo jobDetailDTOToJobDetailVo(JobDetailDTO jobDetailDTO);
    public List<JobDetailVo> jobDetailDTOsToJobDetailVos(List<JobDetailDTO> jobDetailDTOS);

    public TriggerVo triggerDTOToTriggerVo(TriggerDTO triggerDTO);
    public List<TriggerVo> triggerDTOsToTriggerVos(List<TriggerDTO> triggerDTOs);


    public JobCronAddCommand mapUpdateToAddCommand(JobCronUpdateCommand updateCommand);
    public ScheduleQueryCommand map(ScheduleCommand scheduleCommand);

    public ScheduleVo scheduleDTOToScheduleVo(ScheduleDTO scheduleDTO);

    public List<ScheduleVo> scheduleDTOsToScheduleVos(List<ScheduleDTO> scheduleDTOs);

    public ScheduleParam scheduleCommandToScheduleParam(ScheduleCommand scheduleCommand);
    public ScheduleParam scheduleShutdownCommandToScheduleParam(ScheduleShutdownCommand scheduleShutdownCommand);

}
