package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentCitedApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentCitedRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedQueryListCommand;
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
 * 企业知识产权专利被引证信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Tag(name = "企业知识产权专利被引证信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_cited")
public class DataCompanyIprPatentCitedAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentCitedApplicationService iDataCompanyIprPatentCitedApplicationService;
    @Autowired
    private IDataCompanyIprPatentCitedRepresentationApplicationService iDataCompanyIprPatentCitedRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:create')")
    @Operation(summary = "添加企业知识产权专利被引证信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利被引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentCitedVO> create(@RequestBody DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand){
        return iDataCompanyIprPatentCitedApplicationService.create(dataCompanyIprPatentCitedCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:delete')")
    @Operation(summary = "删除企业知识产权专利被引证信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利被引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentCitedVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentCitedApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:update')")
    @Operation(summary = "更新企业知识产权专利被引证信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利被引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentCitedVO> update(@RequestBody DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand){
        dataCompanyIprPatentCitedUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentCitedApplicationService.update(dataCompanyIprPatentCitedUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:update')")
    @Operation(summary = "企业知识产权专利被引证信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentCitedVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentCitedRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:detail')")
    @Operation(summary = "企业知识产权专利被引证信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentCitedVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentCitedRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:queryList')")
    @Operation(summary = "列表查询企业知识产权专利被引证信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentCitedVO> queryList(DataCompanyIprPatentCitedQueryListCommand dataCompanyIprPatentCitedQueryListCommand){
        dataCompanyIprPatentCitedQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentCitedRepresentationApplicationService.queryList(dataCompanyIprPatentCitedQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCited:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利被引证信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentCitedVO> pageQueryList(DataCompanyIprPatentCitedPageQueryCommand dataCompanyIprPatentCitedPageQueryCommand){
        dataCompanyIprPatentCitedPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentCitedRepresentationApplicationService.pageQuery(dataCompanyIprPatentCitedPageQueryCommand);
    }
}