package com.particle.data.adapter.dynamicdata.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorApplicationService;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorUpdateCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorWithDynamicTableFieldVO;
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
 * 动态数据指标后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Tag(name = "动态数据指标pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_data_indicator")
public class DynamicDataIndicatorAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicDataIndicatorApplicationService iDynamicDataIndicatorApplicationService;
    @Autowired
    private IDynamicDataIndicatorRepresentationApplicationService iDynamicDataIndicatorRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:create')")
    @Operation(summary = "添加动态数据指标")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据指标",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicDataIndicatorVO> create(@RequestBody DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand){
        return iDynamicDataIndicatorApplicationService.create(dynamicDataIndicatorCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:delete')")
    @Operation(summary = "删除动态数据指标")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据指标",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicDataIndicatorVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicDataIndicatorApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:update')")
    @Operation(summary = "更新动态数据指标")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据指标",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicDataIndicatorVO> update(@RequestBody DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand){
        dynamicDataIndicatorUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicDataIndicatorApplicationService.update(dynamicDataIndicatorUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:update')")
    @Operation(summary = "动态数据指标更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicDataIndicatorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicDataIndicatorRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:detail')")
    @Operation(summary = "动态数据指标详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicDataIndicatorVO> queryDetail(IdCommand detailCommand){
        return iDynamicDataIndicatorRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:queryList')")
    @Operation(summary = "列表查询动态数据指标")
    @GetMapping("/list")
    public MultiResponse<DynamicDataIndicatorVO> queryList(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand){
        dynamicDataIndicatorQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorRepresentationApplicationService.queryList(dynamicDataIndicatorQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:pageQuery')")
    @Operation(summary = "分页查询动态数据指标")
    @GetMapping("/page")
    public PageResponse<DynamicDataIndicatorVO> pageQueryList(DynamicDataIndicatorPageQueryCommand dynamicDataIndicatorPageQueryCommand){
        dynamicDataIndicatorPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorRepresentationApplicationService.pageQuery(dynamicDataIndicatorPageQueryCommand);
    }
    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicator:queryList')")
    @Operation(summary = "列表查询动态数据指标包含动态数据表格字段")
    @GetMapping("/listWithDynamicTableField")
    public MultiResponse<DynamicDataIndicatorWithDynamicTableFieldVO> queryListWithDynamicTableField(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand){
        dynamicDataIndicatorQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorRepresentationApplicationService.queryListWithDynamicTableField(dynamicDataIndicatorQueryListCommand);
    }
}
