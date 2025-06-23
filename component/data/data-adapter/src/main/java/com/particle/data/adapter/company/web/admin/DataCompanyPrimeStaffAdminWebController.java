package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyPrimeStaffRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffQueryListCommand;
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
 * 企业主要人员后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Tag(name = "企业主要人员pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_prime_staff")
public class DataCompanyPrimeStaffAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyPrimeStaffApplicationService iDataCompanyPrimeStaffApplicationService;
    @Autowired
    private IDataCompanyPrimeStaffRepresentationApplicationService iDataCompanyPrimeStaffRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:create')")
    @Operation(summary = "添加企业主要人员")
    @PostMapping("/create")
    @OpLog(name = "添加企业主要人员",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyPrimeStaffVO> create(@RequestBody DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand){
        return iDataCompanyPrimeStaffApplicationService.create(dataCompanyPrimeStaffCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:delete')")
    @Operation(summary = "删除企业主要人员")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业主要人员",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyPrimeStaffVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyPrimeStaffApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:update')")
    @Operation(summary = "更新企业主要人员")
    @PutMapping("/update")
    @OpLog(name = "更新企业主要人员",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyPrimeStaffVO> update(@RequestBody DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand){
        dataCompanyPrimeStaffUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyPrimeStaffApplicationService.update(dataCompanyPrimeStaffUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:update')")
    @Operation(summary = "企业主要人员更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyPrimeStaffVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyPrimeStaffRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:detail')")
    @Operation(summary = "企业主要人员详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyPrimeStaffVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyPrimeStaffRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:queryList')")
    @Operation(summary = "列表查询企业主要人员")
    @GetMapping("/list")
    public MultiResponse<DataCompanyPrimeStaffVO> queryList(DataCompanyPrimeStaffQueryListCommand dataCompanyPrimeStaffQueryListCommand){
        dataCompanyPrimeStaffQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPrimeStaffRepresentationApplicationService.queryList(dataCompanyPrimeStaffQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaff:pageQuery')")
    @Operation(summary = "分页查询企业主要人员")
    @GetMapping("/page")
    public PageResponse<DataCompanyPrimeStaffVO> pageQueryList(DataCompanyPrimeStaffPageQueryCommand dataCompanyPrimeStaffPageQueryCommand){
        dataCompanyPrimeStaffPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPrimeStaffRepresentationApplicationService.pageQuery(dataCompanyPrimeStaffPageQueryCommand);
    }
}