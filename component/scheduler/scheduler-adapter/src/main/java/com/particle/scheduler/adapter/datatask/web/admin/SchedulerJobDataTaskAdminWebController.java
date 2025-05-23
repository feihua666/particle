package com.particle.scheduler.adapter.datatask.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.scheduler.client.datatask.api.ISchedulerJobDataTaskApplicationService;
import com.particle.scheduler.client.datatask.api.representation.ISchedulerJobDataTaskRepresentationApplicationService;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
/**
 * <p>
 * 任务计划任务数据后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Tag(name = "任务计划任务数据pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_job_data_task")
public class SchedulerJobDataTaskAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerJobDataTaskApplicationService iSchedulerJobDataTaskApplicationService;
    @Autowired
    private ISchedulerJobDataTaskRepresentationApplicationService iSchedulerJobDataTaskRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:create')")
    @Operation(summary = "添加任务计划任务数据")
    @PostMapping("/create")
    @OpLog(name = "添加任务计划任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<SchedulerJobDataTaskVO> create(@RequestBody SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand){
        return iSchedulerJobDataTaskApplicationService.create(schedulerJobDataTaskCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:delete')")
    @Operation(summary = "删除任务计划任务数据")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerJobDataTaskVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerJobDataTaskApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:update')")
    @Operation(summary = "更新任务计划任务数据")
    @PutMapping("/update")
    @OpLog(name = "更新任务计划任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<SchedulerJobDataTaskVO> update(@RequestBody SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand){
        schedulerJobDataTaskUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iSchedulerJobDataTaskApplicationService.update(schedulerJobDataTaskUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:update')")
    @Operation(summary = "任务计划任务数据更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<SchedulerJobDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iSchedulerJobDataTaskRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:detail')")
    @Operation(summary = "任务计划任务数据详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerJobDataTaskVO> queryDetail(IdCommand detailCommand){
        return iSchedulerJobDataTaskRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:queryList')")
    @Operation(summary = "列表查询任务计划任务数据")
    @GetMapping("/list")
    public MultiResponse<SchedulerJobDataTaskVO> queryList(SchedulerJobDataTaskQueryListCommand schedulerJobDataTaskQueryListCommand){
        schedulerJobDataTaskQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerJobDataTaskRepresentationApplicationService.queryList(schedulerJobDataTaskQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerJobDataTask:pageQuery')")
    @Operation(summary = "分页查询任务计划任务数据")
    @GetMapping("/page")
    public PageResponse<SchedulerJobDataTaskVO> pageQueryList(SchedulerJobDataTaskPageQueryCommand schedulerJobDataTaskPageQueryCommand){
        schedulerJobDataTaskPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerJobDataTaskRepresentationApplicationService.pageQuery(schedulerJobDataTaskPageQueryCommand);
    }
}