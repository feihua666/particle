package com.particle.scheduler.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.api.ISchedulerApplicationService;
import com.particle.scheduler.client.dto.command.ScheduleCommand;
import com.particle.scheduler.client.dto.command.ScheduleQueryCommand;
import com.particle.scheduler.client.dto.command.ScheduleShutdownCommand;
import com.particle.scheduler.client.dto.data.ScheduleVo;
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
 * Created at 2024-08-29 18:01:17
 */
@RestController
@RequestMapping("/admin/web/schedule")
@Tag(name = "任务计划相关接口")
public class SchedulerAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerApplicationService iSchedulerApplicationService;

    @Operation(summary = "挂起任务计划")
    @PreAuthorize("hasAuthority('schedule:standby')")
    @PostMapping("/standby")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "挂起任务计划",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> standby(@RequestBody @Valid ScheduleCommand scheduleCommand) {
        Boolean b = iSchedulerApplicationService.standbySchedule(scheduleCommand);
        return SingleResponse.of(b);
    }

    @Operation(summary = "启动任务计划")
    @PreAuthorize("hasAuthority('schedule:start')")
    @PostMapping("/start")
    @OpLog(name = "启动任务计划",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<Boolean> start(@RequestBody @Valid ScheduleCommand scheduleCommand) {
        Boolean b = iSchedulerApplicationService.startSchedule(scheduleCommand);
        return SingleResponse.of(b);
    }

    @Operation(summary = "停止任务计划")
    @PreAuthorize("hasAuthority('schedule:shutdown')")
    @PostMapping("/shutdown")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "停止任务计划",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> shutdown(@RequestBody @Valid ScheduleShutdownCommand scheduleShutdownCommand) {
        Boolean b = iSchedulerApplicationService.shutdownSchedule(scheduleShutdownCommand);
        return SingleResponse.of(b);
    }

    @Operation(summary = "不分页查询任务计划")
    @PreAuthorize("hasAuthority('schedule:getScheduleList')")
    @GetMapping("/getScheduleList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<ScheduleVo> getList(ScheduleQueryCommand scheduleQueryCommand){
        List<ScheduleVo> scheduleVoList = iSchedulerApplicationService.scheduleList(scheduleQueryCommand);
        return MultiResponse.of(scheduleVoList);
    }

}
