package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprWorkCopyrightApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprWorkCopyrightRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightQueryListCommand;
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
 * 企业知识产权作品著作后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Tag(name = "企业知识产权作品著作pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_work_copyright")
public class DataCompanyIprWorkCopyrightAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprWorkCopyrightApplicationService iDataCompanyIprWorkCopyrightApplicationService;
    @Autowired
    private IDataCompanyIprWorkCopyrightRepresentationApplicationService iDataCompanyIprWorkCopyrightRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:create')")
    @Operation(summary = "添加企业知识产权作品著作")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权作品著作",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprWorkCopyrightVO> create(@RequestBody DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand){
        return iDataCompanyIprWorkCopyrightApplicationService.create(dataCompanyIprWorkCopyrightCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:delete')")
    @Operation(summary = "删除企业知识产权作品著作")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权作品著作",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprWorkCopyrightVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprWorkCopyrightApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:update')")
    @Operation(summary = "更新企业知识产权作品著作")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权作品著作",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprWorkCopyrightVO> update(@RequestBody DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand){
        dataCompanyIprWorkCopyrightUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprWorkCopyrightApplicationService.update(dataCompanyIprWorkCopyrightUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:update')")
    @Operation(summary = "企业知识产权作品著作更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprWorkCopyrightRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:detail')")
    @Operation(summary = "企业知识产权作品著作详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprWorkCopyrightRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:queryList')")
    @Operation(summary = "列表查询企业知识产权作品著作")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprWorkCopyrightVO> queryList(DataCompanyIprWorkCopyrightQueryListCommand dataCompanyIprWorkCopyrightQueryListCommand){
        dataCompanyIprWorkCopyrightQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprWorkCopyrightRepresentationApplicationService.queryList(dataCompanyIprWorkCopyrightQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprWorkCopyright:pageQuery')")
    @Operation(summary = "分页查询企业知识产权作品著作")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprWorkCopyrightVO> pageQueryList(DataCompanyIprWorkCopyrightPageQueryCommand dataCompanyIprWorkCopyrightPageQueryCommand){
        dataCompanyIprWorkCopyrightPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprWorkCopyrightRepresentationApplicationService.pageQuery(dataCompanyIprWorkCopyrightPageQueryCommand);
    }
}