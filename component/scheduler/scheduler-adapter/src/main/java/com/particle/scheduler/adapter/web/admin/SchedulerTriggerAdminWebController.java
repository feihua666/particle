package com.particle.scheduler.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.api.ISchedulerApplicationService;
import com.particle.scheduler.client.dto.command.NameAndGroupCommand;
import com.particle.scheduler.client.dto.command.ScheduleQueryCommand;
import com.particle.scheduler.client.dto.command.TriggerQueryCommand;
import com.particle.scheduler.client.dto.data.TriggerVo;
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
@RequestMapping("/admin/web/schedule/trigger")
@Tag(name = "任务计划trigger相关接口")
public class SchedulerTriggerAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerApplicationService iSchedulerApplicationService;


    @Operation(summary = "暂停触发器")
    @PreAuthorize("hasAuthority('schedule:trigger:pause')")
    @PostMapping("/pauseTrigger")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "暂停触发器",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> pauseTrigger(@RequestBody @Valid NameAndGroupCommand addCommand,@RequestBody @Valid  ScheduleQueryCommand scheduleQueryCommand) {
        Boolean b = iSchedulerApplicationService.pauseTrigger(addCommand, scheduleQueryCommand);
        return SingleResponse.of(b);
    }
    @Operation(summary = "恢复触发器")
    @PreAuthorize("hasAuthority('schedule:trigger:resume')")
    @PostMapping("/resumeTrigger")
    @ResponseStatus(HttpStatus.OK)
    @OpLog(name = "恢复触发器",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.other)
    public SingleResponse<Boolean> resumeTrigger(@RequestBody @Valid NameAndGroupCommand addCommand,@RequestBody @Valid ScheduleQueryCommand scheduleQueryCommand) {
        Boolean b = iSchedulerApplicationService.resumeTrigger(addCommand, scheduleQueryCommand);
        return SingleResponse.of(b);
    }

    @Operation(summary = "不分页查询触发器")
    @PreAuthorize("hasAuthority('schedule:getTriggerList')")
    @GetMapping("/getTriggerList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<TriggerVo> getTriggerList(TriggerQueryCommand triggerQueryCommand, ScheduleQueryCommand scheduleQueryCommand) {
        List<TriggerVo> triggerVos = iSchedulerApplicationService.triggerList(triggerQueryCommand, scheduleQueryCommand);
        return MultiResponse.of(triggerVos);
    }
}
