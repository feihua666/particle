package com.particle.scheduler.adapter.schedule.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import com.particle.scheduler.client.schedule.api.representation.ISchedulerExecuteRecordRepresentationApplicationService;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordCreateCommand;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordUpdateCommand;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordPageQueryCommand;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordQueryListCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 任务计划执行记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Tag(name = "任务计划执行记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_execute_record")
public class SchedulerExecuteRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerExecuteRecordApplicationService iSchedulerExecuteRecordApplicationService;
    @Autowired
    private ISchedulerExecuteRecordRepresentationApplicationService iSchedulerExecuteRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:create')")
    @Operation(summary = "添加任务计划执行记录")
    @PostMapping("/create")
    @OpLog(name = "添加任务计划执行记录",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<SchedulerExecuteRecordVO> create(@RequestBody SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand){
        return iSchedulerExecuteRecordApplicationService.create(schedulerExecuteRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:delete')")
    @Operation(summary = "删除任务计划执行记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划执行记录",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerExecuteRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerExecuteRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:update')")
    @Operation(summary = "更新任务计划执行记录")
    @PutMapping("/update")
    @OpLog(name = "更新任务计划执行记录",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<SchedulerExecuteRecordVO> update(@RequestBody SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand){
        schedulerExecuteRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iSchedulerExecuteRecordApplicationService.update(schedulerExecuteRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:update')")
    @Operation(summary = "任务计划执行记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<SchedulerExecuteRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iSchedulerExecuteRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:detail')")
    @Operation(summary = "任务计划执行记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerExecuteRecordVO> queryDetail(IdCommand detailCommand){
        return iSchedulerExecuteRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:queryList')")
    @Operation(summary = "列表查询任务计划执行记录")
    @GetMapping("/list")
    public MultiResponse<SchedulerExecuteRecordVO> queryList(SchedulerExecuteRecordQueryListCommand schedulerExecuteRecordQueryListCommand){
        schedulerExecuteRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerExecuteRecordRepresentationApplicationService.queryList(schedulerExecuteRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerExecuteRecord:pageQuery')")
    @Operation(summary = "分页查询任务计划执行记录")
    @GetMapping("/page")
    public PageResponse<SchedulerExecuteRecordVO> pageQueryList(SchedulerExecuteRecordPageQueryCommand schedulerExecuteRecordPageQueryCommand){
        schedulerExecuteRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerExecuteRecordRepresentationApplicationService.pageQuery(schedulerExecuteRecordPageQueryCommand);
    }
}
