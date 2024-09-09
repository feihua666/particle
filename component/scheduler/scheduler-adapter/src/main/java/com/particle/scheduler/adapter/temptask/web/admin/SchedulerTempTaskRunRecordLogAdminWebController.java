package com.particle.scheduler.adapter.temptask.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordLogApplicationService;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRunRecordLogRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 任务计划临时任务运行记录日志后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Tag(name = "任务计划临时任务运行记录日志pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_temp_task_run_record_log")
public class SchedulerTempTaskRunRecordLogAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerTempTaskRunRecordLogApplicationService iSchedulerTempTaskRunRecordLogApplicationService;
    @Autowired
    private ISchedulerTempTaskRunRecordLogRepresentationApplicationService iSchedulerTempTaskRunRecordLogRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecordLog:delete')")
    @Operation(summary = "删除任务计划临时任务运行记录日志")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划临时任务运行记录日志",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerTempTaskRunRecordLogApplicationService.delete(deleteCommand);
    }


    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecordLog:detail')")
    @Operation(summary = "任务计划临时任务运行记录日志详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> queryDetail(IdCommand detailCommand){
        return iSchedulerTempTaskRunRecordLogRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecordLog:queryList')")
    @Operation(summary = "列表查询任务计划临时任务运行记录日志")
    @GetMapping("/list")
    public MultiResponse<SchedulerTempTaskRunRecordLogVO> queryList(SchedulerTempTaskRunRecordLogQueryListCommand schedulerTempTaskRunRecordLogQueryListCommand){
        schedulerTempTaskRunRecordLogQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRunRecordLogRepresentationApplicationService.queryList(schedulerTempTaskRunRecordLogQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTaskRunRecordLog:pageQuery')")
    @Operation(summary = "分页查询任务计划临时任务运行记录日志")
    @GetMapping("/page")
    public PageResponse<SchedulerTempTaskRunRecordLogVO> pageQueryList(SchedulerTempTaskRunRecordLogPageQueryCommand schedulerTempTaskRunRecordLogPageQueryCommand){
        schedulerTempTaskRunRecordLogPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRunRecordLogRepresentationApplicationService.pageQuery(schedulerTempTaskRunRecordLogPageQueryCommand);
    }
}