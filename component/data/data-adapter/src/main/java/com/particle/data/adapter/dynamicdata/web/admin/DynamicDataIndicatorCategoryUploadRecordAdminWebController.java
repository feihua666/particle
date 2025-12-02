package com.particle.data.adapter.dynamicdata.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordUpdateCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordQueryListCommand;
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
 * 动态数据指标分类上传记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Tag(name = "动态数据指标分类上传记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_data_indicator_category_upload_record")
public class DynamicDataIndicatorCategoryUploadRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicDataIndicatorCategoryUploadRecordApplicationService iDynamicDataIndicatorCategoryUploadRecordApplicationService;
    @Autowired
    private IDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService iDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:create')")
    @Operation(summary = "添加动态数据指标分类上传记录")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据指标分类上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> create(@RequestBody DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand){
        return iDynamicDataIndicatorCategoryUploadRecordApplicationService.create(dynamicDataIndicatorCategoryUploadRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:delete')")
    @Operation(summary = "删除动态数据指标分类上传记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据指标分类上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicDataIndicatorCategoryUploadRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:update')")
    @Operation(summary = "更新动态数据指标分类上传记录")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据指标分类上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> update(@RequestBody DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand){
        dynamicDataIndicatorCategoryUploadRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicDataIndicatorCategoryUploadRecordApplicationService.update(dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:update')")
    @Operation(summary = "动态数据指标分类上传记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:detail')")
    @Operation(summary = "动态数据指标分类上传记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetail(IdCommand detailCommand){
        return iDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:queryList')")
    @Operation(summary = "列表查询动态数据指标分类上传记录")
    @GetMapping("/list")
    public MultiResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryList(DynamicDataIndicatorCategoryUploadRecordQueryListCommand dynamicDataIndicatorCategoryUploadRecordQueryListCommand){
        dynamicDataIndicatorCategoryUploadRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService.queryList(dynamicDataIndicatorCategoryUploadRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:pageQuery')")
    @Operation(summary = "分页查询动态数据指标分类上传记录")
    @GetMapping("/page")
    public PageResponse<DynamicDataIndicatorCategoryUploadRecordVO> pageQueryList(DynamicDataIndicatorCategoryUploadRecordPageQueryCommand dynamicDataIndicatorCategoryUploadRecordPageQueryCommand){
        dynamicDataIndicatorCategoryUploadRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService.pageQuery(dynamicDataIndicatorCategoryUploadRecordPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategoryUploadRecord:publish')")
    @Operation(summary = "发布动态数据指标分类上传记录")
    @PutMapping("/publish")
    @OpLog(name = "发布动态数据指标分类上传记录",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> publish(@RequestBody IdCommand publishCommand){
        publishCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.update.name());
        return iDynamicDataIndicatorCategoryUploadRecordApplicationService.publish(publishCommand);
    }
}
