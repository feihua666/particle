package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentQuoteApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentQuoteRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuotePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuoteQueryListCommand;
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
 * 企业知识产权专利引证信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Tag(name = "企业知识产权专利引证信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_quote")
public class DataCompanyIprPatentQuoteAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentQuoteApplicationService iDataCompanyIprPatentQuoteApplicationService;
    @Autowired
    private IDataCompanyIprPatentQuoteRepresentationApplicationService iDataCompanyIprPatentQuoteRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:create')")
    @Operation(summary = "添加企业知识产权专利引证信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentQuoteVO> create(@RequestBody DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand){
        return iDataCompanyIprPatentQuoteApplicationService.create(dataCompanyIprPatentQuoteCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:delete')")
    @Operation(summary = "删除企业知识产权专利引证信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentQuoteVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentQuoteApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:update')")
    @Operation(summary = "更新企业知识产权专利引证信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利引证信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentQuoteVO> update(@RequestBody DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand){
        dataCompanyIprPatentQuoteUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentQuoteApplicationService.update(dataCompanyIprPatentQuoteUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:update')")
    @Operation(summary = "企业知识产权专利引证信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentQuoteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentQuoteRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:detail')")
    @Operation(summary = "企业知识产权专利引证信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentQuoteVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentQuoteRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:queryList')")
    @Operation(summary = "列表查询企业知识产权专利引证信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentQuoteVO> queryList(DataCompanyIprPatentQuoteQueryListCommand dataCompanyIprPatentQuoteQueryListCommand){
        dataCompanyIprPatentQuoteQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentQuoteRepresentationApplicationService.queryList(dataCompanyIprPatentQuoteQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentQuote:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利引证信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentQuoteVO> pageQueryList(DataCompanyIprPatentQuotePageQueryCommand dataCompanyIprPatentQuotePageQueryCommand){
        dataCompanyIprPatentQuotePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentQuoteRepresentationApplicationService.pageQuery(dataCompanyIprPatentQuotePageQueryCommand);
    }
}