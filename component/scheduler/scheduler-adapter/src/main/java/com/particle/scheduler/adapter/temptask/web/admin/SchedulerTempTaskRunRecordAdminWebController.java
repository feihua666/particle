package com.particle.scheduler.adapter.temptask.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordApplicationService;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRunRecordRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordUpdateCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordQueryListCommand;
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
 * 任务计划临时任务运行记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Tag(name = "任务计划临时任务运行记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_temp_task_run_record")
public class SchedulerTempTaskRunRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerTempTaskRunRecordApplicationService iSchedulerTempTaskRunRecordApplicationService;
    @Autowired
    private ISchedulerTempTaskRunRecordRepresentationApplicationService iSchedulerTempTaskRunRecordRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:delete')")
    @Operation(summary = "删除任务计划临时任务运行记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划临时任务运行记录",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerTempTaskRunRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerTempTaskRunRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:update')")
    @Operation(summary = "更新任务计划临时任务运行记录")
    @PutMapping("/update")
    @OpLog(name = "更新任务计划临时任务运行记录",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.update)
    public SingleResponse<SchedulerTempTaskRunRecordVO> update(@RequestBody SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand){
        schedulerTempTaskRunRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iSchedulerTempTaskRunRecordApplicationService.update(schedulerTempTaskRunRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:update')")
    @Operation(summary = "任务计划临时任务运行记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<SchedulerTempTaskRunRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iSchedulerTempTaskRunRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:detail')")
    @Operation(summary = "任务计划临时任务运行记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerTempTaskRunRecordVO> queryDetail(IdCommand detailCommand){
        return iSchedulerTempTaskRunRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:queryList')")
    @Operation(summary = "列表查询任务计划临时任务运行记录")
    @GetMapping("/list")
    public MultiResponse<SchedulerTempTaskRunRecordVO> queryList(SchedulerTempTaskRunRecordQueryListCommand schedulerTempTaskRunRecordQueryListCommand){
        schedulerTempTaskRunRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRunRecordRepresentationApplicationService.queryList(schedulerTempTaskRunRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecord:pageQuery')")
    @Operation(summary = "分页查询任务计划临时任务运行记录")
    @GetMapping("/page")
    public PageResponse<SchedulerTempTaskRunRecordVO> pageQueryList(SchedulerTempTaskRunRecordPageQueryCommand schedulerTempTaskRunRecordPageQueryCommand){
        schedulerTempTaskRunRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRunRecordRepresentationApplicationService.pageQuery(schedulerTempTaskRunRecordPageQueryCommand);
    }
}