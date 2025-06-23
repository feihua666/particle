package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPledgeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgeQueryListCommand;
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
 * 企业知识产权出质后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Tag(name = "企业知识产权出质pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_pledge")
public class DataCompanyIprPledgeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPledgeApplicationService iDataCompanyIprPledgeApplicationService;
    @Autowired
    private IDataCompanyIprPledgeRepresentationApplicationService iDataCompanyIprPledgeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:create')")
    @Operation(summary = "添加企业知识产权出质")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPledgeVO> create(@RequestBody DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand){
        return iDataCompanyIprPledgeApplicationService.create(dataCompanyIprPledgeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:delete')")
    @Operation(summary = "删除企业知识产权出质")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPledgeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPledgeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:update')")
    @Operation(summary = "更新企业知识产权出质")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权出质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPledgeVO> update(@RequestBody DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand){
        dataCompanyIprPledgeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPledgeApplicationService.update(dataCompanyIprPledgeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:update')")
    @Operation(summary = "企业知识产权出质更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPledgeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:detail')")
    @Operation(summary = "企业知识产权出质详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPledgeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPledgeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:queryList')")
    @Operation(summary = "列表查询企业知识产权出质")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPledgeVO> queryList(DataCompanyIprPledgeQueryListCommand dataCompanyIprPledgeQueryListCommand){
        dataCompanyIprPledgeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPledgeRepresentationApplicationService.queryList(dataCompanyIprPledgeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPledge:pageQuery')")
    @Operation(summary = "分页查询企业知识产权出质")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPledgeVO> pageQueryList(DataCompanyIprPledgePageQueryCommand dataCompanyIprPledgePageQueryCommand){
        dataCompanyIprPledgePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPledgeRepresentationApplicationService.pageQuery(dataCompanyIprPledgePageQueryCommand);
    }
}