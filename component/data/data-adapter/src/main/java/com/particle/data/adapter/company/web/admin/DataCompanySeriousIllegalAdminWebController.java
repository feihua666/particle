package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanySeriousIllegalApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanySeriousIllegalRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalQueryListCommand;
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
 * 企业严重违法后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Tag(name = "企业严重违法pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_serious_illegal")
public class DataCompanySeriousIllegalAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanySeriousIllegalApplicationService iDataCompanySeriousIllegalApplicationService;
    @Autowired
    private IDataCompanySeriousIllegalRepresentationApplicationService iDataCompanySeriousIllegalRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:create')")
    @Operation(summary = "添加企业严重违法")
    @PostMapping("/create")
    @OpLog(name = "添加企业严重违法",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanySeriousIllegalVO> create(@RequestBody DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand){
        return iDataCompanySeriousIllegalApplicationService.create(dataCompanySeriousIllegalCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:delete')")
    @Operation(summary = "删除企业严重违法")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业严重违法",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanySeriousIllegalVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanySeriousIllegalApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:update')")
    @Operation(summary = "更新企业严重违法")
    @PutMapping("/update")
    @OpLog(name = "更新企业严重违法",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanySeriousIllegalVO> update(@RequestBody DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand){
        dataCompanySeriousIllegalUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanySeriousIllegalApplicationService.update(dataCompanySeriousIllegalUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:update')")
    @Operation(summary = "企业严重违法更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanySeriousIllegalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanySeriousIllegalRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:detail')")
    @Operation(summary = "企业严重违法详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanySeriousIllegalVO> queryDetail(IdCommand detailCommand){
        return iDataCompanySeriousIllegalRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:queryList')")
    @Operation(summary = "列表查询企业严重违法")
    @GetMapping("/list")
    public MultiResponse<DataCompanySeriousIllegalVO> queryList(DataCompanySeriousIllegalQueryListCommand dataCompanySeriousIllegalQueryListCommand){
        dataCompanySeriousIllegalQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanySeriousIllegalRepresentationApplicationService.queryList(dataCompanySeriousIllegalQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanySeriousIllegal:pageQuery')")
    @Operation(summary = "分页查询企业严重违法")
    @GetMapping("/page")
    public PageResponse<DataCompanySeriousIllegalVO> pageQueryList(DataCompanySeriousIllegalPageQueryCommand dataCompanySeriousIllegalPageQueryCommand){
        dataCompanySeriousIllegalPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanySeriousIllegalRepresentationApplicationService.pageQuery(dataCompanySeriousIllegalPageQueryCommand);
    }
}