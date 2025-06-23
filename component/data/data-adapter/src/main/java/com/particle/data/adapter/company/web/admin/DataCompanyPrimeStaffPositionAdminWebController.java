package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffPositionApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyPrimeStaffPositionRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionQueryListCommand;
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
 * 企业主要人员职位后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Tag(name = "企业主要人员职位pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_prime_staff_position")
public class DataCompanyPrimeStaffPositionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyPrimeStaffPositionApplicationService iDataCompanyPrimeStaffPositionApplicationService;
    @Autowired
    private IDataCompanyPrimeStaffPositionRepresentationApplicationService iDataCompanyPrimeStaffPositionRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:create')")
    @Operation(summary = "添加企业主要人员职位")
    @PostMapping("/create")
    @OpLog(name = "添加企业主要人员职位",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyPrimeStaffPositionVO> create(@RequestBody DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand){
        return iDataCompanyPrimeStaffPositionApplicationService.create(dataCompanyPrimeStaffPositionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:delete')")
    @Operation(summary = "删除企业主要人员职位")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业主要人员职位",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyPrimeStaffPositionVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyPrimeStaffPositionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:update')")
    @Operation(summary = "更新企业主要人员职位")
    @PutMapping("/update")
    @OpLog(name = "更新企业主要人员职位",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyPrimeStaffPositionVO> update(@RequestBody DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand){
        dataCompanyPrimeStaffPositionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyPrimeStaffPositionApplicationService.update(dataCompanyPrimeStaffPositionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:update')")
    @Operation(summary = "企业主要人员职位更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyPrimeStaffPositionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:detail')")
    @Operation(summary = "企业主要人员职位详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyPrimeStaffPositionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:queryList')")
    @Operation(summary = "列表查询企业主要人员职位")
    @GetMapping("/list")
    public MultiResponse<DataCompanyPrimeStaffPositionVO> queryList(DataCompanyPrimeStaffPositionQueryListCommand dataCompanyPrimeStaffPositionQueryListCommand){
        dataCompanyPrimeStaffPositionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPrimeStaffPositionRepresentationApplicationService.queryList(dataCompanyPrimeStaffPositionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPrimeStaffPosition:pageQuery')")
    @Operation(summary = "分页查询企业主要人员职位")
    @GetMapping("/page")
    public PageResponse<DataCompanyPrimeStaffPositionVO> pageQueryList(DataCompanyPrimeStaffPositionPageQueryCommand dataCompanyPrimeStaffPositionPageQueryCommand){
        dataCompanyPrimeStaffPositionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPrimeStaffPositionRepresentationApplicationService.pageQuery(dataCompanyPrimeStaffPositionPageQueryCommand);
    }
}