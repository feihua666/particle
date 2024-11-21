package com.particle.oplog.adapter.error.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.api.IOpLogErrorContentApplicationService;
import com.particle.oplog.client.error.api.representation.IOpLogErrorContentRepresentationApplicationService;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 操作异常日志内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Tag(name = "操作异常日志内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/op_log_error_content")
public class OpLogErrorContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpLogErrorContentApplicationService iOpLogErrorContentApplicationService;
    @Autowired
    private IOpLogErrorContentRepresentationApplicationService iOpLogErrorContentRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:opLogErrorContent:delete')")
    @Operation(summary = "删除操作异常日志内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除操作异常日志内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpLogErrorContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpLogErrorContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogErrorContent:detail')")
    @Operation(summary = "根据异常id获取操作异常日志内容详情展示")
    @GetMapping("/detailByOpLogErrorId")
    public SingleResponse<OpLogErrorContentVO> detailByOpLogErrorId(IdCommand detailForUpdateCommand){
        return iOpLogErrorContentRepresentationApplicationService.detailByOpLogErrorId(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:opLogErrorContent:detail')")
    @Operation(summary = "操作异常日志内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpLogErrorContentVO> queryDetail(IdCommand detailCommand){
        return iOpLogErrorContentRepresentationApplicationService.queryDetail(detailCommand);
    }
}
