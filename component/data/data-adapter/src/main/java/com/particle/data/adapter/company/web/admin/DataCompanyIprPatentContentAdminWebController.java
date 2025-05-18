package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentContentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentQueryListCommand;
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
 * 企业知识产权专利内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Tag(name = "企业知识产权专利内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_content")
public class DataCompanyIprPatentContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentContentApplicationService iDataCompanyIprPatentContentApplicationService;
    @Autowired
    private IDataCompanyIprPatentContentRepresentationApplicationService iDataCompanyIprPatentContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:create')")
    @Operation(summary = "添加企业知识产权专利内容")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentContentVO> create(@RequestBody DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand){
        return iDataCompanyIprPatentContentApplicationService.create(dataCompanyIprPatentContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:delete')")
    @Operation(summary = "删除企业知识产权专利内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:update')")
    @Operation(summary = "更新企业知识产权专利内容")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentContentVO> update(@RequestBody DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand){
        dataCompanyIprPatentContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentContentApplicationService.update(dataCompanyIprPatentContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:update')")
    @Operation(summary = "企业知识产权专利内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:detail')")
    @Operation(summary = "企业知识产权专利内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentContentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:queryList')")
    @Operation(summary = "列表查询企业知识产权专利内容")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentContentVO> queryList(DataCompanyIprPatentContentQueryListCommand dataCompanyIprPatentContentQueryListCommand){
        dataCompanyIprPatentContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentContentRepresentationApplicationService.queryList(dataCompanyIprPatentContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentContent:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利内容")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentContentVO> pageQueryList(DataCompanyIprPatentContentPageQueryCommand dataCompanyIprPatentContentPageQueryCommand){
        dataCompanyIprPatentContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentContentRepresentationApplicationService.pageQuery(dataCompanyIprPatentContentPageQueryCommand);
    }
}