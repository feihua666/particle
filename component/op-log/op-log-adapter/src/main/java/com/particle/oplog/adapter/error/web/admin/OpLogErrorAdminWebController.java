package com.particle.oplog.adapter.error.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.api.IOpLogErrorApplicationService;
import com.particle.oplog.client.error.api.representation.IOpLogErrorRepresentationApplicationService;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorPageQueryCommand;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorQueryListCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 操作异常日志后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Tag(name = "操作异常日志pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/op_log_error")
public class OpLogErrorAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpLogErrorApplicationService iOpLogErrorApplicationService;
    @Autowired
    private IOpLogErrorRepresentationApplicationService iOpLogErrorRepresentationApplicationService;

    /**
     * 没有权限
     * @param opLogErrorCreateCommand
     * @return
     */
    @Operation(summary = "添加操作异常日志")
    @PostMapping("/create")
    public SingleResponse<OpLogErrorVO> create(@RequestBody OpLogErrorCreateCommand opLogErrorCreateCommand){
        return iOpLogErrorApplicationService.create(opLogErrorCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogError:delete')")
    @Operation(summary = "删除操作异常日志")
    @DeleteMapping("/delete")
    public SingleResponse<OpLogErrorVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpLogErrorApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogError:detail')")
    @Operation(summary = "操作异常日志详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpLogErrorVO> queryDetail(IdCommand detailCommand){
        return iOpLogErrorRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogError:queryList')")
    @Operation(summary = "列表查询操作异常日志")
    @GetMapping("/list")
    public MultiResponse<OpLogErrorVO> queryList(OpLogErrorQueryListCommand opLogErrorQueryListCommand){
        opLogErrorQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpLogErrorRepresentationApplicationService.queryList(opLogErrorQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogError:pageQuery')")
    @Operation(summary = "分页查询操作异常日志")
    @GetMapping("/page")
    public PageResponse<OpLogErrorVO> pageQueryList(OpLogErrorPageQueryCommand opLogErrorPageQueryCommand){
        opLogErrorPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpLogErrorRepresentationApplicationService.pageQuery(opLogErrorPageQueryCommand);
    }
}