package com.particle.scheduler.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.api.ISchedulerApplicationService;
import com.particle.scheduler.client.dto.command.*;
import com.particle.scheduler.client.dto.data.JobDetailExtVo;
import com.particle.scheduler.client.dto.data.JobDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2021/2/2 18:18
 */
@RestController
@RequestMapping("/admin/web/schedule/job")
@Tag(name = "任务计划job相关接口")
public class SchedulerJobAdminWebController extends AbstractBaseWebAdapter {


    @Autowired
    private ISchedulerApplicationService iSchedulerApplicationService;


    @Operation(summary = "添加任务")
    @PreAuthorize("hasAuthority('schedule:job:add')")
    @PostMapping("/addJob")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "添加任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.create)
    public SingleResponse<JobDetailVo> addJob(@RequestBody @Valid JobCronAddCommand addCommand) {
        JobDetailVo jobDetailVo = iSchedulerApplicationService.addJob(addCommand);
        return SingleResponse.of(jobDetailVo);
    }

    @Operation(summary = "复制任务")
    @PreAuthorize("hasAuthority('schedule:job:copy')")
    @PostMapping("/copyJob")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "复制任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.create)
    public SingleResponse<JobDetailVo> copyJob(@RequestBody @Valid NameAndGroupCommand nameAndGroupCommand,@RequestBody @Valid ScheduleQueryCommand scheduleQueryCommand) {

        JobDetailVo jobDetailVo = iSchedulerApplicationService.copyJob(nameAndGroupCommand, scheduleQueryCommand);
        return SingleResponse.of(jobDetailVo);
    }

    @Operation(summary = "更新任务")
    @PreAuthorize("hasAuthority('schedule:job:update')")
    @PutMapping("/updateJob")
    @OpLog(name = "更新任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.update)
    public SingleResponse<JobDetailVo> updateJob(@RequestBody @Valid JobCronUpdateCommand updateCommand) {

        JobDetailVo jobDetailVo = iSchedulerApplicationService.updateJob(updateCommand);
        return SingleResponse.of(jobDetailVo);
    }

    @Operation(summary = "删除任务")
    @PreAuthorize("hasAuthority('schedule:job:delete')")
    @DeleteMapping("/deleteJob")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "删除任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.delete)
    public SingleResponse<Boolean> deleteJob(@RequestBody @Valid NameAndGroupCommand addCommand,@RequestBody @Valid ScheduleQueryCommand scheduleQueryCommand) {
        Boolean b = iSchedulerApplicationService.deleteJob(addCommand, scheduleQueryCommand);
        return SingleResponse.of(b);
    }
    @Operation(summary = "暂停任务")
    @PreAuthorize("hasAuthority('schedule:job:pause')")
    @PostMapping("/pauseJob")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "暂停任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> pauseJob(@RequestBody @Valid NameAndGroupCommand addCommand,@RequestBody @Valid ScheduleQueryCommand scheduleQueryCommand) {
        Boolean b = iSchedulerApplicationService.pauseJob(addCommand, scheduleQueryCommand);
        return SingleResponse.of(b);
    }
    @Operation(summary = "恢复任务")
    @PreAuthorize("hasAuthority('schedule:job:resume')")
    @PostMapping("/resumeJob")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "恢复任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> resumeJob(@RequestBody @Valid NameAndGroupCommand addCommand,@RequestBody @Valid ScheduleQueryCommand scheduleQueryCommand) {
        Boolean b = iSchedulerApplicationService.resumeJob(addCommand, scheduleQueryCommand);
        return SingleResponse.of(b);
    }

    @Operation(summary = "获取任务")
    @PreAuthorize("hasAuthority('schedule:job:getJobDetailExt')")
    @GetMapping("/getJobDetailExt")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<JobDetailExtVo> getJobDetailExt(@Valid NameAndGroupCommand addCommand, ScheduleQueryCommand scheduleQueryCommand){
        JobDetailExtVo jobDetailExtVo = iSchedulerApplicationService.jobExtDetail(addCommand, scheduleQueryCommand);
        return SingleResponse.of(jobDetailExtVo);
    }

    @Operation(summary = "不分页查询任务")
    @PreAuthorize("hasAuthority('schedule:getJobDetailList')")
    @GetMapping("/getJobDetailList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<JobDetailVo> getJobDetailList(JobQueryCommand jobQueryCommand, ScheduleQueryCommand scheduleQueryCommand){
        List<JobDetailVo> jobDetailVos = iSchedulerApplicationService.jobDetailList(jobQueryCommand, scheduleQueryCommand);
        return MultiResponse.of(jobDetailVos);
    }

    @Operation(summary = "手动执行一次任务")
    @PreAuthorize("hasAuthority('schedule:job:executeOnce')")
    @PostMapping("/executeOnce")
    @OpLog(name = "手动执行一次任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> executeOnce(@RequestBody @Valid NameAndGroupCommand nameAndGroupCommand,@RequestBody @Valid ScheduleCommand scheduleCommand){
        Boolean b = iSchedulerApplicationService.executeJobOnce(nameAndGroupCommand, scheduleCommand);
        return SingleResponse.of(b);
    }
}
