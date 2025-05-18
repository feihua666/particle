package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyPersonApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyPersonRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPersonUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonQueryListCommand;
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
 * 企业个人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Tag(name = "企业个人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_person")
public class DataCompanyPersonAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyPersonApplicationService iDataCompanyPersonApplicationService;
    @Autowired
    private IDataCompanyPersonRepresentationApplicationService iDataCompanyPersonRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:create')")
    @Operation(summary = "添加企业个人")
    @PostMapping("/create")
    @OpLog(name = "添加企业个人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyPersonVO> create(@RequestBody DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand){
        return iDataCompanyPersonApplicationService.create(dataCompanyPersonCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:delete')")
    @Operation(summary = "删除企业个人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业个人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyPersonVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyPersonApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:update')")
    @Operation(summary = "更新企业个人")
    @PutMapping("/update")
    @OpLog(name = "更新企业个人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyPersonVO> update(@RequestBody DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand){
        dataCompanyPersonUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyPersonApplicationService.update(dataCompanyPersonUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:update')")
    @Operation(summary = "企业个人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyPersonRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:detail')")
    @Operation(summary = "企业个人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyPersonVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyPersonRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:queryList')")
    @Operation(summary = "列表查询企业个人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyPersonVO> queryList(DataCompanyPersonQueryListCommand dataCompanyPersonQueryListCommand){
        dataCompanyPersonQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPersonRepresentationApplicationService.queryList(dataCompanyPersonQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPerson:pageQuery')")
    @Operation(summary = "分页查询企业个人")
    @GetMapping("/page")
    public PageResponse<DataCompanyPersonVO> pageQueryList(DataCompanyPersonPageQueryCommand dataCompanyPersonPageQueryCommand){
        dataCompanyPersonPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPersonRepresentationApplicationService.pageQuery(dataCompanyPersonPageQueryCommand);
    }
}