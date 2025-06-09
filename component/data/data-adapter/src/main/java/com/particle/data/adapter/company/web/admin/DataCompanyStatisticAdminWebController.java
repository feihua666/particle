package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyStatisticApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyStatisticRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyStatisticUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticQueryListCommand;
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
 * 企业统计后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Tag(name = "企业统计pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_statistic")
public class DataCompanyStatisticAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyStatisticApplicationService iDataCompanyStatisticApplicationService;
    @Autowired
    private IDataCompanyStatisticRepresentationApplicationService iDataCompanyStatisticRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:create')")
    @Operation(summary = "添加企业统计")
    @PostMapping("/create")
    @OpLog(name = "添加企业统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyStatisticVO> create(@RequestBody DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand){
        return iDataCompanyStatisticApplicationService.create(dataCompanyStatisticCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:delete')")
    @Operation(summary = "删除企业统计")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyStatisticVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyStatisticApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:update')")
    @Operation(summary = "更新企业统计")
    @PutMapping("/update")
    @OpLog(name = "更新企业统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyStatisticVO> update(@RequestBody DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand){
        dataCompanyStatisticUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyStatisticApplicationService.update(dataCompanyStatisticUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:update')")
    @Operation(summary = "企业统计更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyStatisticRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:detail')")
    @Operation(summary = "企业统计详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyStatisticVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyStatisticRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:queryList')")
    @Operation(summary = "列表查询企业统计")
    @GetMapping("/list")
    public MultiResponse<DataCompanyStatisticVO> queryList(DataCompanyStatisticQueryListCommand dataCompanyStatisticQueryListCommand){
        dataCompanyStatisticQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyStatisticRepresentationApplicationService.queryList(dataCompanyStatisticQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyStatistic:pageQuery')")
    @Operation(summary = "分页查询企业统计")
    @GetMapping("/page")
    public PageResponse<DataCompanyStatisticVO> pageQueryList(DataCompanyStatisticPageQueryCommand dataCompanyStatisticPageQueryCommand){
        dataCompanyStatisticPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyStatisticRepresentationApplicationService.pageQuery(dataCompanyStatisticPageQueryCommand);
    }
}