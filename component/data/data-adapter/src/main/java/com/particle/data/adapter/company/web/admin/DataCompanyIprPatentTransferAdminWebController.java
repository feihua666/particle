package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentTransferApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentTransferRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferQueryListCommand;
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
 * 企业知识产权专利转让信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Tag(name = "企业知识产权专利转让信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_transfer")
public class DataCompanyIprPatentTransferAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentTransferApplicationService iDataCompanyIprPatentTransferApplicationService;
    @Autowired
    private IDataCompanyIprPatentTransferRepresentationApplicationService iDataCompanyIprPatentTransferRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:create')")
    @Operation(summary = "添加企业知识产权专利转让信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentTransferVO> create(@RequestBody DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand){
        return iDataCompanyIprPatentTransferApplicationService.create(dataCompanyIprPatentTransferCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:delete')")
    @Operation(summary = "删除企业知识产权专利转让信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentTransferVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentTransferApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:update')")
    @Operation(summary = "更新企业知识产权专利转让信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentTransferVO> update(@RequestBody DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand){
        dataCompanyIprPatentTransferUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentTransferApplicationService.update(dataCompanyIprPatentTransferUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:update')")
    @Operation(summary = "企业知识产权专利转让信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentTransferRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:detail')")
    @Operation(summary = "企业知识产权专利转让信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentTransferVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentTransferRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:queryList')")
    @Operation(summary = "列表查询企业知识产权专利转让信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentTransferVO> queryList(DataCompanyIprPatentTransferQueryListCommand dataCompanyIprPatentTransferQueryListCommand){
        dataCompanyIprPatentTransferQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentTransferRepresentationApplicationService.queryList(dataCompanyIprPatentTransferQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentTransfer:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利转让信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentTransferVO> pageQueryList(DataCompanyIprPatentTransferPageQueryCommand dataCompanyIprPatentTransferPageQueryCommand){
        dataCompanyIprPatentTransferPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentTransferRepresentationApplicationService.pageQuery(dataCompanyIprPatentTransferPageQueryCommand);
    }
}