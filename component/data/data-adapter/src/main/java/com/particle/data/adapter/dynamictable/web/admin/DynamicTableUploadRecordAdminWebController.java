package com.particle.data.adapter.dynamictable.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableUploadRecordRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordUpdateCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordQueryListCommand;
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
 * 动态数据表格上传记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Tag(name = "动态数据表格上传记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_table_upload_record")
public class DynamicTableUploadRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;
    @Autowired
    private IDynamicTableUploadRecordRepresentationApplicationService iDynamicTableUploadRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:create')")
    @Operation(summary = "添加动态数据表格上传记录")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据表格上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicTableUploadRecordVO> create(@RequestBody DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand){
        return iDynamicTableUploadRecordApplicationService.create(dynamicTableUploadRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:delete')")
    @Operation(summary = "删除动态数据表格上传记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据表格上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicTableUploadRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicTableUploadRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:update')")
    @Operation(summary = "更新动态数据表格上传记录")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据表格上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicTableUploadRecordVO> update(@RequestBody DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand){
        dynamicTableUploadRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicTableUploadRecordApplicationService.update(dynamicTableUploadRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:update')")
    @Operation(summary = "动态数据表格上传记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicTableUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicTableUploadRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:detail')")
    @Operation(summary = "动态数据表格上传记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicTableUploadRecordVO> queryDetail(IdCommand detailCommand){
        return iDynamicTableUploadRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:queryList')")
    @Operation(summary = "列表查询动态数据表格上传记录")
    @GetMapping("/list")
    public MultiResponse<DynamicTableUploadRecordVO> queryList(DynamicTableUploadRecordQueryListCommand dynamicTableUploadRecordQueryListCommand){
        dynamicTableUploadRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableUploadRecordRepresentationApplicationService.queryList(dynamicTableUploadRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:pageQuery')")
    @Operation(summary = "分页查询动态数据表格上传记录")
    @GetMapping("/page")
    public PageResponse<DynamicTableUploadRecordVO> pageQueryList(DynamicTableUploadRecordPageQueryCommand dynamicTableUploadRecordPageQueryCommand){
        dynamicTableUploadRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableUploadRecordRepresentationApplicationService.pageQuery(dynamicTableUploadRecordPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableUploadRecord:publish')")
    @Operation(summary = "发布动态数据表格上传记录")
    @PutMapping("/publish")
    @OpLog(name = "发布动态数据表格上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicTableUploadRecordVO> publish(@RequestBody IdCommand publishCommand){
        publishCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.update.name());
        return iDynamicTableUploadRecordApplicationService.publish(publishCommand);
    }
}
