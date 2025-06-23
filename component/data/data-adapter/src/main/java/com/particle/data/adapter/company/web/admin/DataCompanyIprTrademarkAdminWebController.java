package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkQueryListCommand;
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
 * 企业知识产权商标后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Tag(name = "企业知识产权商标pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_trademark")
public class DataCompanyIprTrademarkAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprTrademarkApplicationService iDataCompanyIprTrademarkApplicationService;
    @Autowired
    private IDataCompanyIprTrademarkRepresentationApplicationService iDataCompanyIprTrademarkRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:create')")
    @Operation(summary = "添加企业知识产权商标")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权商标",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprTrademarkVO> create(@RequestBody DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand){
        return iDataCompanyIprTrademarkApplicationService.create(dataCompanyIprTrademarkCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:delete')")
    @Operation(summary = "删除企业知识产权商标")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权商标",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprTrademarkVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprTrademarkApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:update')")
    @Operation(summary = "更新企业知识产权商标")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权商标",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprTrademarkVO> update(@RequestBody DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand){
        dataCompanyIprTrademarkUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprTrademarkApplicationService.update(dataCompanyIprTrademarkUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:update')")
    @Operation(summary = "企业知识产权商标更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprTrademarkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprTrademarkRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:detail')")
    @Operation(summary = "企业知识产权商标详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprTrademarkVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprTrademarkRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:queryList')")
    @Operation(summary = "列表查询企业知识产权商标")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprTrademarkVO> queryList(DataCompanyIprTrademarkQueryListCommand dataCompanyIprTrademarkQueryListCommand){
        dataCompanyIprTrademarkQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkRepresentationApplicationService.queryList(dataCompanyIprTrademarkQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademark:pageQuery')")
    @Operation(summary = "分页查询企业知识产权商标")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprTrademarkVO> pageQueryList(DataCompanyIprTrademarkPageQueryCommand dataCompanyIprTrademarkPageQueryCommand){
        dataCompanyIprTrademarkPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkRepresentationApplicationService.pageQuery(dataCompanyIprTrademarkPageQueryCommand);
    }
}