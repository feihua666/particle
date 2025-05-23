package com.particle.scheduler.adapter.datatask.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import com.particle.scheduler.client.datatask.api.representation.ISchedulerAsyncDataTaskRepresentationApplicationService;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskQueryListCommand;
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
 * 任务计划异步任务数据后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Tag(name = "任务计划异步任务数据pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_async_data_task")
public class SchedulerAsyncDataTaskAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerAsyncDataTaskApplicationService iSchedulerAsyncDataTaskApplicationService;
    @Autowired
    private ISchedulerAsyncDataTaskRepresentationApplicationService iSchedulerAsyncDataTaskRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:create')")
    @Operation(summary = "添加任务计划异步任务数据")
    @PostMapping("/create")
    @OpLog(name = "添加任务计划异步任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<SchedulerAsyncDataTaskVO> create(@RequestBody SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand){
        return iSchedulerAsyncDataTaskApplicationService.create(schedulerAsyncDataTaskCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:delete')")
    @Operation(summary = "删除任务计划异步任务数据")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划异步任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerAsyncDataTaskVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerAsyncDataTaskApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:update')")
    @Operation(summary = "更新任务计划异步任务数据")
    @PutMapping("/update")
    @OpLog(name = "更新任务计划异步任务数据",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<SchedulerAsyncDataTaskVO> update(@RequestBody SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand){
        schedulerAsyncDataTaskUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iSchedulerAsyncDataTaskApplicationService.update(schedulerAsyncDataTaskUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:update')")
    @Operation(summary = "任务计划异步任务数据更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<SchedulerAsyncDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iSchedulerAsyncDataTaskRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:detail')")
    @Operation(summary = "任务计划异步任务数据详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerAsyncDataTaskVO> queryDetail(IdCommand detailCommand){
        return iSchedulerAsyncDataTaskRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:queryList')")
    @Operation(summary = "列表查询任务计划异步任务数据")
    @GetMapping("/list")
    public MultiResponse<SchedulerAsyncDataTaskVO> queryList(SchedulerAsyncDataTaskQueryListCommand schedulerAsyncDataTaskQueryListCommand){
        schedulerAsyncDataTaskQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerAsyncDataTaskRepresentationApplicationService.queryList(schedulerAsyncDataTaskQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerAsyncDataTask:pageQuery')")
    @Operation(summary = "分页查询任务计划异步任务数据")
    @GetMapping("/page")
    public PageResponse<SchedulerAsyncDataTaskVO> pageQueryList(SchedulerAsyncDataTaskPageQueryCommand schedulerAsyncDataTaskPageQueryCommand){
        schedulerAsyncDataTaskPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerAsyncDataTaskRepresentationApplicationService.pageQuery(schedulerAsyncDataTaskPageQueryCommand);
    }
}