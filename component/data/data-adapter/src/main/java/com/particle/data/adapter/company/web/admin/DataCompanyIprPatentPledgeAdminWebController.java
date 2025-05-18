package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentPledgeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgeQueryListCommand;
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
 * 企业知识产权专利质押信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Tag(name = "企业知识产权专利质押信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_pledge")
public class DataCompanyIprPatentPledgeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentPledgeApplicationService iDataCompanyIprPatentPledgeApplicationService;
    @Autowired
    private IDataCompanyIprPatentPledgeRepresentationApplicationService iDataCompanyIprPatentPledgeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:create')")
    @Operation(summary = "添加企业知识产权专利质押信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentPledgeVO> create(@RequestBody DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand){
        return iDataCompanyIprPatentPledgeApplicationService.create(dataCompanyIprPatentPledgeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:delete')")
    @Operation(summary = "删除企业知识产权专利质押信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentPledgeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentPledgeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:update')")
    @Operation(summary = "更新企业知识产权专利质押信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentPledgeVO> update(@RequestBody DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand){
        dataCompanyIprPatentPledgeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentPledgeApplicationService.update(dataCompanyIprPatentPledgeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:update')")
    @Operation(summary = "企业知识产权专利质押信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentPledgeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:detail')")
    @Operation(summary = "企业知识产权专利质押信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentPledgeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentPledgeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:queryList')")
    @Operation(summary = "列表查询企业知识产权专利质押信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentPledgeVO> queryList(DataCompanyIprPatentPledgeQueryListCommand dataCompanyIprPatentPledgeQueryListCommand){
        dataCompanyIprPatentPledgeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPledgeRepresentationApplicationService.queryList(dataCompanyIprPatentPledgeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPledge:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利质押信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentPledgeVO> pageQueryList(DataCompanyIprPatentPledgePageQueryCommand dataCompanyIprPatentPledgePageQueryCommand){
        dataCompanyIprPatentPledgePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPledgeRepresentationApplicationService.pageQuery(dataCompanyIprPatentPledgePageQueryCommand);
    }
}