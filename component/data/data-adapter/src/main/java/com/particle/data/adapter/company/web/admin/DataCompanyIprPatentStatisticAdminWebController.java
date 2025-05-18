package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentStatisticApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentStatisticRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticQueryListCommand;
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
 * 企业知识产权专利统计后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Tag(name = "企业知识产权专利统计pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_statistic")
public class DataCompanyIprPatentStatisticAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentStatisticApplicationService iDataCompanyIprPatentStatisticApplicationService;
    @Autowired
    private IDataCompanyIprPatentStatisticRepresentationApplicationService iDataCompanyIprPatentStatisticRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:create')")
    @Operation(summary = "添加企业知识产权专利统计")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentStatisticVO> create(@RequestBody DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand){
        return iDataCompanyIprPatentStatisticApplicationService.create(dataCompanyIprPatentStatisticCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:delete')")
    @Operation(summary = "删除企业知识产权专利统计")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentStatisticVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentStatisticApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:update')")
    @Operation(summary = "更新企业知识产权专利统计")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利统计",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentStatisticVO> update(@RequestBody DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand){
        dataCompanyIprPatentStatisticUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentStatisticApplicationService.update(dataCompanyIprPatentStatisticUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:update')")
    @Operation(summary = "企业知识产权专利统计更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentStatisticRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:detail')")
    @Operation(summary = "企业知识产权专利统计详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentStatisticVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentStatisticRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:queryList')")
    @Operation(summary = "列表查询企业知识产权专利统计")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentStatisticVO> queryList(DataCompanyIprPatentStatisticQueryListCommand dataCompanyIprPatentStatisticQueryListCommand){
        dataCompanyIprPatentStatisticQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentStatisticRepresentationApplicationService.queryList(dataCompanyIprPatentStatisticQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentStatistic:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利统计")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentStatisticVO> pageQueryList(DataCompanyIprPatentStatisticPageQueryCommand dataCompanyIprPatentStatisticPageQueryCommand){
        dataCompanyIprPatentStatisticPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentStatisticRepresentationApplicationService.pageQuery(dataCompanyIprPatentStatisticPageQueryCommand);
    }
}