package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQueryListCommand;
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
 * 企业知识产权专利后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Tag(name = "企业知识产权专利pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent")
public class DataCompanyIprPatentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentApplicationService iDataCompanyIprPatentApplicationService;
    @Autowired
    private IDataCompanyIprPatentRepresentationApplicationService iDataCompanyIprPatentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:create')")
    @Operation(summary = "添加企业知识产权专利")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentVO> create(@RequestBody DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand){
        return iDataCompanyIprPatentApplicationService.create(dataCompanyIprPatentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:delete')")
    @Operation(summary = "删除企业知识产权专利")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:update')")
    @Operation(summary = "更新企业知识产权专利")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentVO> update(@RequestBody DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand){
        dataCompanyIprPatentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentApplicationService.update(dataCompanyIprPatentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:update')")
    @Operation(summary = "企业知识产权专利更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:detail')")
    @Operation(summary = "企业知识产权专利详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:queryList')")
    @Operation(summary = "列表查询企业知识产权专利")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentVO> queryList(DataCompanyIprPatentQueryListCommand dataCompanyIprPatentQueryListCommand){
        dataCompanyIprPatentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentRepresentationApplicationService.queryList(dataCompanyIprPatentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatent:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentVO> pageQueryList(DataCompanyIprPatentPageQueryCommand dataCompanyIprPatentPageQueryCommand){
        dataCompanyIprPatentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentRepresentationApplicationService.pageQuery(dataCompanyIprPatentPageQueryCommand);
    }
}