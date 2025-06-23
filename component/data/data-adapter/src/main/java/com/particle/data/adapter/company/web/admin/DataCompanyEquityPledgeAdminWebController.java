package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyEquityPledgeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyEquityPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgeQueryListCommand;
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
 * 企业股权出质后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Tag(name = "企业股权出质pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_equity_pledge")
public class DataCompanyEquityPledgeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyEquityPledgeApplicationService iDataCompanyEquityPledgeApplicationService;
    @Autowired
    private IDataCompanyEquityPledgeRepresentationApplicationService iDataCompanyEquityPledgeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:create')")
    @Operation(summary = "添加企业股权出质")
    @PostMapping("/create")
    @OpLog(name = "添加企业股权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyEquityPledgeVO> create(@RequestBody DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand){
        return iDataCompanyEquityPledgeApplicationService.create(dataCompanyEquityPledgeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:delete')")
    @Operation(summary = "删除企业股权出质")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业股权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyEquityPledgeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyEquityPledgeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:update')")
    @Operation(summary = "更新企业股权出质")
    @PutMapping("/update")
    @OpLog(name = "更新企业股权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyEquityPledgeVO> update(@RequestBody DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand){
        dataCompanyEquityPledgeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyEquityPledgeApplicationService.update(dataCompanyEquityPledgeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:update')")
    @Operation(summary = "企业股权出质更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyEquityPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyEquityPledgeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:detail')")
    @Operation(summary = "企业股权出质详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyEquityPledgeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyEquityPledgeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:queryList')")
    @Operation(summary = "列表查询企业股权出质")
    @GetMapping("/list")
    public MultiResponse<DataCompanyEquityPledgeVO> queryList(DataCompanyEquityPledgeQueryListCommand dataCompanyEquityPledgeQueryListCommand){
        dataCompanyEquityPledgeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyEquityPledgeRepresentationApplicationService.queryList(dataCompanyEquityPledgeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEquityPledge:pageQuery')")
    @Operation(summary = "分页查询企业股权出质")
    @GetMapping("/page")
    public PageResponse<DataCompanyEquityPledgeVO> pageQueryList(DataCompanyEquityPledgePageQueryCommand dataCompanyEquityPledgePageQueryCommand){
        dataCompanyEquityPledgePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyEquityPledgeRepresentationApplicationService.pageQuery(dataCompanyEquityPledgePageQueryCommand);
    }
}