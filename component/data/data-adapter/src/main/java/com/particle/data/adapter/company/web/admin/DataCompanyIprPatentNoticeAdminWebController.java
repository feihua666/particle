package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentNoticeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentNoticeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticeQueryListCommand;
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
 * 企业知识产权专利通知书信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Tag(name = "企业知识产权专利通知书信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_notice")
public class DataCompanyIprPatentNoticeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentNoticeApplicationService iDataCompanyIprPatentNoticeApplicationService;
    @Autowired
    private IDataCompanyIprPatentNoticeRepresentationApplicationService iDataCompanyIprPatentNoticeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:create')")
    @Operation(summary = "添加企业知识产权专利通知书信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利通知书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentNoticeVO> create(@RequestBody DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand){
        return iDataCompanyIprPatentNoticeApplicationService.create(dataCompanyIprPatentNoticeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:delete')")
    @Operation(summary = "删除企业知识产权专利通知书信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利通知书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentNoticeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentNoticeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:update')")
    @Operation(summary = "更新企业知识产权专利通知书信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利通知书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentNoticeVO> update(@RequestBody DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand){
        dataCompanyIprPatentNoticeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentNoticeApplicationService.update(dataCompanyIprPatentNoticeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:update')")
    @Operation(summary = "企业知识产权专利通知书信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentNoticeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentNoticeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:detail')")
    @Operation(summary = "企业知识产权专利通知书信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentNoticeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentNoticeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:queryList')")
    @Operation(summary = "列表查询企业知识产权专利通知书信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentNoticeVO> queryList(DataCompanyIprPatentNoticeQueryListCommand dataCompanyIprPatentNoticeQueryListCommand){
        dataCompanyIprPatentNoticeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentNoticeRepresentationApplicationService.queryList(dataCompanyIprPatentNoticeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentNotice:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利通知书信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentNoticeVO> pageQueryList(DataCompanyIprPatentNoticePageQueryCommand dataCompanyIprPatentNoticePageQueryCommand){
        dataCompanyIprPatentNoticePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentNoticeRepresentationApplicationService.pageQuery(dataCompanyIprPatentNoticePageQueryCommand);
    }
}