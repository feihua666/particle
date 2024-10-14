package com.particle.openplatform.adapter.openapi.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand;
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
 * 开放接口批量查询记录明细后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Tag(name = "开放接口批量查询记录明细pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_batch_query_record_detail")
public class OpenplatformOpenapiBatchQueryRecordDetailAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiBatchQueryRecordDetailApplicationService iOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
    @Autowired
    private IOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService iOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecordDetail:delete')")
    @Operation(summary = "删除开放接口批量查询记录明细")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放接口批量查询记录明细",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiBatchQueryRecordDetailApplicationService.delete(deleteCommand);
    }


    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecordDetail:detail')")
    @Operation(summary = "开放接口批量查询记录明细详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecordDetail:queryList')")
    @Operation(summary = "列表查询开放接口批量查询记录明细")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> queryList(OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand openplatformOpenapiBatchQueryRecordDetailQueryListCommand){
        openplatformOpenapiBatchQueryRecordDetailQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService.queryList(openplatformOpenapiBatchQueryRecordDetailQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecordDetail:pageQuery')")
    @Operation(summary = "分页查询开放接口批量查询记录明细")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> pageQueryList(OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand openplatformOpenapiBatchQueryRecordDetailPageQueryCommand){
        openplatformOpenapiBatchQueryRecordDetailPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiBatchQueryRecordDetailRepresentationApplicationService.pageQuery(openplatformOpenapiBatchQueryRecordDetailPageQueryCommand);
    }
}