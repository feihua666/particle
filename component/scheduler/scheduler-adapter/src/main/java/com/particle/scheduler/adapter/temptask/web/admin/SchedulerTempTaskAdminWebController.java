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
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 任务计划临时任务后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Tag(name = "任务计划临时任务pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/scheduler_temp_task")
public class SchedulerTempTaskAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ISchedulerTempTaskApplicationService iSchedulerTempTaskApplicationService;
    @Autowired
    private ISchedulerTempTaskRepresentationApplicationService iSchedulerTempTaskRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:schedulerTempTask:delete')")
    @Operation(summary = "删除任务计划临时任务")
    @DeleteMapping("/delete")
    @OpLog(name = "删除任务计划临时任务",module = OpLogConstants.Module.scheduler,type = OpLogConstants.Type.delete)
    public SingleResponse<SchedulerTempTaskVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iSchedulerTempTaskApplicationService.delete(deleteCommand);
    }


    @PreAuthorize("hasAuthority('admin:web:schedulerTempTask:detail')")
    @Operation(summary = "任务计划临时任务详情展示")
    @GetMapping("/detail")
    public SingleResponse<SchedulerTempTaskVO> queryDetail(IdCommand detailCommand){
        return iSchedulerTempTaskRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTask:queryList')")
    @Operation(summary = "列表查询任务计划临时任务")
    @GetMapping("/list")
    public MultiResponse<SchedulerTempTaskVO> queryList(SchedulerTempTaskQueryListCommand schedulerTempTaskQueryListCommand){
        schedulerTempTaskQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRepresentationApplicationService.queryList(schedulerTempTaskQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:schedulerTempTask:pageQuery')")
    @Operation(summary = "分页查询任务计划临时任务")
    @GetMapping("/page")
    public PageResponse<SchedulerTempTaskVO> pageQueryList(SchedulerTempTaskPageQueryCommand schedulerTempTaskPageQueryCommand){
        schedulerTempTaskPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iSchedulerTempTaskRepresentationApplicationService.pageQuery(schedulerTempTaskPageQueryCommand);
    }
}