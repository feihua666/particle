package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkTransferApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkTransferRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferQueryListCommand;
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
 * 企业知识产权商标转让信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Tag(name = "企业知识产权商标转让信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_trademark_transfer")
public class DataCompanyIprTrademarkTransferAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprTrademarkTransferApplicationService iDataCompanyIprTrademarkTransferApplicationService;
    @Autowired
    private IDataCompanyIprTrademarkTransferRepresentationApplicationService iDataCompanyIprTrademarkTransferRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:create')")
    @Operation(summary = "添加企业知识产权商标转让信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权商标转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprTrademarkTransferVO> create(@RequestBody DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand){
        return iDataCompanyIprTrademarkTransferApplicationService.create(dataCompanyIprTrademarkTransferCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:delete')")
    @Operation(summary = "删除企业知识产权商标转让信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权商标转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprTrademarkTransferVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprTrademarkTransferApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:update')")
    @Operation(summary = "更新企业知识产权商标转让信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权商标转让信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprTrademarkTransferVO> update(@RequestBody DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand){
        dataCompanyIprTrademarkTransferUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprTrademarkTransferApplicationService.update(dataCompanyIprTrademarkTransferUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:update')")
    @Operation(summary = "企业知识产权商标转让信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprTrademarkTransferRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:detail')")
    @Operation(summary = "企业知识产权商标转让信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprTrademarkTransferRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:queryList')")
    @Operation(summary = "列表查询企业知识产权商标转让信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprTrademarkTransferVO> queryList(DataCompanyIprTrademarkTransferQueryListCommand dataCompanyIprTrademarkTransferQueryListCommand){
        dataCompanyIprTrademarkTransferQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkTransferRepresentationApplicationService.queryList(dataCompanyIprTrademarkTransferQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransfer:pageQuery')")
    @Operation(summary = "分页查询企业知识产权商标转让信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprTrademarkTransferVO> pageQueryList(DataCompanyIprTrademarkTransferPageQueryCommand dataCompanyIprTrademarkTransferPageQueryCommand){
        dataCompanyIprTrademarkTransferPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkTransferRepresentationApplicationService.pageQuery(dataCompanyIprTrademarkTransferPageQueryCommand);
    }
}