package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentLegalStatusApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentLegalStatusRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusQueryListCommand;
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
 * 企业知识产权专利法律状态后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Tag(name = "企业知识产权专利法律状态pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_legal_status")
public class DataCompanyIprPatentLegalStatusAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentLegalStatusApplicationService iDataCompanyIprPatentLegalStatusApplicationService;
    @Autowired
    private IDataCompanyIprPatentLegalStatusRepresentationApplicationService iDataCompanyIprPatentLegalStatusRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:create')")
    @Operation(summary = "添加企业知识产权专利法律状态")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利法律状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> create(@RequestBody DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand){
        return iDataCompanyIprPatentLegalStatusApplicationService.create(dataCompanyIprPatentLegalStatusCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:delete')")
    @Operation(summary = "删除企业知识产权专利法律状态")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利法律状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentLegalStatusApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:update')")
    @Operation(summary = "更新企业知识产权专利法律状态")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利法律状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> update(@RequestBody DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand){
        dataCompanyIprPatentLegalStatusUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentLegalStatusApplicationService.update(dataCompanyIprPatentLegalStatusUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:update')")
    @Operation(summary = "企业知识产权专利法律状态更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentLegalStatusRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:detail')")
    @Operation(summary = "企业知识产权专利法律状态详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentLegalStatusRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:queryList')")
    @Operation(summary = "列表查询企业知识产权专利法律状态")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentLegalStatusVO> queryList(DataCompanyIprPatentLegalStatusQueryListCommand dataCompanyIprPatentLegalStatusQueryListCommand){
        dataCompanyIprPatentLegalStatusQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentLegalStatusRepresentationApplicationService.queryList(dataCompanyIprPatentLegalStatusQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLegalStatus:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利法律状态")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentLegalStatusVO> pageQueryList(DataCompanyIprPatentLegalStatusPageQueryCommand dataCompanyIprPatentLegalStatusPageQueryCommand){
        dataCompanyIprPatentLegalStatusPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentLegalStatusRepresentationApplicationService.pageQuery(dataCompanyIprPatentLegalStatusPageQueryCommand);
    }
}