package com.particle.data.adapter.dynamictable.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.dynamictable.api.IDynamicTableFieldApplicationService;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableFieldRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldUpdateCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldQueryListCommand;
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
 * 动态数据表格字段后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Tag(name = "动态数据表格字段pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_table_field")
public class DynamicTableFieldAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicTableFieldApplicationService iDynamicTableFieldApplicationService;
    @Autowired
    private IDynamicTableFieldRepresentationApplicationService iDynamicTableFieldRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:create')")
    @Operation(summary = "添加动态数据表格字段")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据表格字段",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicTableFieldVO> create(@RequestBody DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand){
        return iDynamicTableFieldApplicationService.create(dynamicTableFieldCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:delete')")
    @Operation(summary = "删除动态数据表格字段")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据表格字段",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicTableFieldVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicTableFieldApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:update')")
    @Operation(summary = "更新动态数据表格字段")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据表格字段",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicTableFieldVO> update(@RequestBody DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand){
        dynamicTableFieldUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicTableFieldApplicationService.update(dynamicTableFieldUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:update')")
    @Operation(summary = "动态数据表格字段更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicTableFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicTableFieldRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:detail')")
    @Operation(summary = "动态数据表格字段详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicTableFieldVO> queryDetail(IdCommand detailCommand){
        return iDynamicTableFieldRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:queryList')")
    @Operation(summary = "列表查询动态数据表格字段")
    @GetMapping("/list")
    public MultiResponse<DynamicTableFieldVO> queryList(DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand){
        dynamicTableFieldQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableFieldRepresentationApplicationService.queryList(dynamicTableFieldQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTableField:pageQuery')")
    @Operation(summary = "分页查询动态数据表格字段")
    @GetMapping("/page")
    public PageResponse<DynamicTableFieldVO> pageQueryList(DynamicTableFieldPageQueryCommand dynamicTableFieldPageQueryCommand){
        dynamicTableFieldPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableFieldRepresentationApplicationService.pageQuery(dynamicTableFieldPageQueryCommand);
    }
}
