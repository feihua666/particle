package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprGeograApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprGeograRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograQueryListCommand;
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
 * 企业知识产权地理标识后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Tag(name = "企业知识产权地理标识pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_geogra")
public class DataCompanyIprGeograAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprGeograApplicationService iDataCompanyIprGeograApplicationService;
    @Autowired
    private IDataCompanyIprGeograRepresentationApplicationService iDataCompanyIprGeograRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:create')")
    @Operation(summary = "添加企业知识产权地理标识")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权地理标识",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprGeograVO> create(@RequestBody DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand){
        return iDataCompanyIprGeograApplicationService.create(dataCompanyIprGeograCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:delete')")
    @Operation(summary = "删除企业知识产权地理标识")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权地理标识",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprGeograVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprGeograApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:update')")
    @Operation(summary = "更新企业知识产权地理标识")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权地理标识",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprGeograVO> update(@RequestBody DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand){
        dataCompanyIprGeograUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprGeograApplicationService.update(dataCompanyIprGeograUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:update')")
    @Operation(summary = "企业知识产权地理标识更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprGeograVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprGeograRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:detail')")
    @Operation(summary = "企业知识产权地理标识详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprGeograVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprGeograRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:queryList')")
    @Operation(summary = "列表查询企业知识产权地理标识")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprGeograVO> queryList(DataCompanyIprGeograQueryListCommand dataCompanyIprGeograQueryListCommand){
        dataCompanyIprGeograQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprGeograRepresentationApplicationService.queryList(dataCompanyIprGeograQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeogra:pageQuery')")
    @Operation(summary = "分页查询企业知识产权地理标识")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprGeograVO> pageQueryList(DataCompanyIprGeograPageQueryCommand dataCompanyIprGeograPageQueryCommand){
        dataCompanyIprGeograPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprGeograRepresentationApplicationService.pageQuery(dataCompanyIprGeograPageQueryCommand);
    }
}