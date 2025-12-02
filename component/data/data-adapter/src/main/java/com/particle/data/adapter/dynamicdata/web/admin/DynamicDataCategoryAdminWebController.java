package com.particle.data.adapter.dynamicdata.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.dynamicdata.api.IDynamicDataCategoryApplicationService;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataCategoryRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryQueryListCommand;
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
 * 动态数据分类后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Tag(name = "动态数据分类pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_data_category")
public class DynamicDataCategoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicDataCategoryApplicationService iDynamicDataCategoryApplicationService;
    @Autowired
    private IDynamicDataCategoryRepresentationApplicationService iDynamicDataCategoryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:create')")
    @Operation(summary = "添加动态数据分类")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicDataCategoryVO> create(@RequestBody DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand){
        return iDynamicDataCategoryApplicationService.create(dynamicDataCategoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:delete')")
    @Operation(summary = "删除动态数据分类")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicDataCategoryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicDataCategoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:update')")
    @Operation(summary = "更新动态数据分类")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicDataCategoryVO> update(@RequestBody DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand){
        dynamicDataCategoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicDataCategoryApplicationService.update(dynamicDataCategoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:update')")
    @Operation(summary = "动态数据分类更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicDataCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicDataCategoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:detail')")
    @Operation(summary = "动态数据分类详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicDataCategoryVO> queryDetail(IdCommand detailCommand){
        return iDynamicDataCategoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:queryList')")
    @Operation(summary = "列表查询动态数据分类")
    @GetMapping("/list")
    public MultiResponse<DynamicDataCategoryVO> queryList(DynamicDataCategoryQueryListCommand dynamicDataCategoryQueryListCommand){
        dynamicDataCategoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataCategoryRepresentationApplicationService.queryList(dynamicDataCategoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataCategory:pageQuery')")
    @Operation(summary = "分页查询动态数据分类")
    @GetMapping("/page")
    public PageResponse<DynamicDataCategoryVO> pageQueryList(DynamicDataCategoryPageQueryCommand dynamicDataCategoryPageQueryCommand){
        dynamicDataCategoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataCategoryRepresentationApplicationService.pageQuery(dynamicDataCategoryPageQueryCommand);
    }
}
