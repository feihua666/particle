package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkPledgeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgeQueryListCommand;
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
 * 企业知识产权商标质押信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Tag(name = "企业知识产权商标质押信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_trademark_pledge")
public class DataCompanyIprTrademarkPledgeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprTrademarkPledgeApplicationService iDataCompanyIprTrademarkPledgeApplicationService;
    @Autowired
    private IDataCompanyIprTrademarkPledgeRepresentationApplicationService iDataCompanyIprTrademarkPledgeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:create')")
    @Operation(summary = "添加企业知识产权商标质押信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权商标质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> create(@RequestBody DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand){
        return iDataCompanyIprTrademarkPledgeApplicationService.create(dataCompanyIprTrademarkPledgeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:delete')")
    @Operation(summary = "删除企业知识产权商标质押信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权商标质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprTrademarkPledgeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:update')")
    @Operation(summary = "更新企业知识产权商标质押信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权商标质押信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> update(@RequestBody DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand){
        dataCompanyIprTrademarkPledgeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprTrademarkPledgeApplicationService.update(dataCompanyIprTrademarkPledgeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:update')")
    @Operation(summary = "企业知识产权商标质押信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprTrademarkPledgeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:detail')")
    @Operation(summary = "企业知识产权商标质押信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprTrademarkPledgeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:queryList')")
    @Operation(summary = "列表查询企业知识产权商标质押信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprTrademarkPledgeVO> queryList(DataCompanyIprTrademarkPledgeQueryListCommand dataCompanyIprTrademarkPledgeQueryListCommand){
        dataCompanyIprTrademarkPledgeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkPledgeRepresentationApplicationService.queryList(dataCompanyIprTrademarkPledgeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkPledge:pageQuery')")
    @Operation(summary = "分页查询企业知识产权商标质押信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprTrademarkPledgeVO> pageQueryList(DataCompanyIprTrademarkPledgePageQueryCommand dataCompanyIprTrademarkPledgePageQueryCommand){
        dataCompanyIprTrademarkPledgePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkPledgeRepresentationApplicationService.pageQuery(dataCompanyIprTrademarkPledgePageQueryCommand);
    }
}