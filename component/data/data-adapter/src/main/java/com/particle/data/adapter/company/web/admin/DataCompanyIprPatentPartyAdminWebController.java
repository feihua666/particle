package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentPartyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyQueryListCommand;
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
 * 企业知识产权当事人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Tag(name = "企业知识产权当事人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_party")
public class DataCompanyIprPatentPartyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentPartyApplicationService iDataCompanyIprPatentPartyApplicationService;
    @Autowired
    private IDataCompanyIprPatentPartyRepresentationApplicationService iDataCompanyIprPatentPartyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:create')")
    @Operation(summary = "添加企业知识产权当事人")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentPartyVO> create(@RequestBody DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand){
        return iDataCompanyIprPatentPartyApplicationService.create(dataCompanyIprPatentPartyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:delete')")
    @Operation(summary = "删除企业知识产权当事人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentPartyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentPartyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:update')")
    @Operation(summary = "更新企业知识产权当事人")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentPartyVO> update(@RequestBody DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand){
        dataCompanyIprPatentPartyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentPartyApplicationService.update(dataCompanyIprPatentPartyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:update')")
    @Operation(summary = "企业知识产权当事人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentPartyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:detail')")
    @Operation(summary = "企业知识产权当事人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentPartyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentPartyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:queryList')")
    @Operation(summary = "列表查询企业知识产权当事人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentPartyVO> queryList(DataCompanyIprPatentPartyQueryListCommand dataCompanyIprPatentPartyQueryListCommand){
        dataCompanyIprPatentPartyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPartyRepresentationApplicationService.queryList(dataCompanyIprPatentPartyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentParty:pageQuery')")
    @Operation(summary = "分页查询企业知识产权当事人")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentPartyVO> pageQueryList(DataCompanyIprPatentPartyPageQueryCommand dataCompanyIprPatentPartyPageQueryCommand){
        dataCompanyIprPatentPartyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPartyRepresentationApplicationService.pageQuery(dataCompanyIprPatentPartyPageQueryCommand);
    }
}