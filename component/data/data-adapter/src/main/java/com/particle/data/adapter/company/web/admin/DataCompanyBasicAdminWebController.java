package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyBasicApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyBasicRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyBasicCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyBasicUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicQueryListCommand;
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
 * 企业基本信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Tag(name = "企业基本信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_basic")
public class DataCompanyBasicAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyBasicApplicationService iDataCompanyBasicApplicationService;
    @Autowired
    private IDataCompanyBasicRepresentationApplicationService iDataCompanyBasicRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:create')")
    @Operation(summary = "添加企业基本信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业基本信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyBasicVO> create(@RequestBody DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand){
        return iDataCompanyBasicApplicationService.create(dataCompanyBasicCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:delete')")
    @Operation(summary = "删除企业基本信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业基本信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyBasicVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyBasicApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:update')")
    @Operation(summary = "更新企业基本信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业基本信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyBasicVO> update(@RequestBody DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand){
        dataCompanyBasicUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyBasicApplicationService.update(dataCompanyBasicUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:update')")
    @Operation(summary = "企业基本信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyBasicVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyBasicRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:detail')")
    @Operation(summary = "企业基本信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyBasicVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyBasicRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:queryList')")
    @Operation(summary = "列表查询企业基本信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyBasicVO> queryList(DataCompanyBasicQueryListCommand dataCompanyBasicQueryListCommand){
        dataCompanyBasicQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyBasicRepresentationApplicationService.queryList(dataCompanyBasicQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyBasic:pageQuery')")
    @Operation(summary = "分页查询企业基本信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyBasicVO> pageQueryList(DataCompanyBasicPageQueryCommand dataCompanyBasicPageQueryCommand){
        dataCompanyBasicPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyBasicRepresentationApplicationService.pageQuery(dataCompanyBasicPageQueryCommand);
    }
}
