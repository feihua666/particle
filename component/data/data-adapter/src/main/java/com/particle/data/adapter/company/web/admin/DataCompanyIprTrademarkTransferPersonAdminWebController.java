package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkTransferPersonApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkTransferPersonRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonQueryListCommand;
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
 * 企业知识产权商标转让人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Tag(name = "企业知识产权商标转让人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_trademark_transfer_person")
public class DataCompanyIprTrademarkTransferPersonAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprTrademarkTransferPersonApplicationService iDataCompanyIprTrademarkTransferPersonApplicationService;
    @Autowired
    private IDataCompanyIprTrademarkTransferPersonRepresentationApplicationService iDataCompanyIprTrademarkTransferPersonRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:create')")
    @Operation(summary = "添加企业知识产权商标转让人")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权商标转让人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> create(@RequestBody DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand){
        return iDataCompanyIprTrademarkTransferPersonApplicationService.create(dataCompanyIprTrademarkTransferPersonCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:delete')")
    @Operation(summary = "删除企业知识产权商标转让人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权商标转让人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprTrademarkTransferPersonApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:update')")
    @Operation(summary = "更新企业知识产权商标转让人")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权商标转让人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> update(@RequestBody DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand){
        dataCompanyIprTrademarkTransferPersonUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprTrademarkTransferPersonApplicationService.update(dataCompanyIprTrademarkTransferPersonUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:update')")
    @Operation(summary = "企业知识产权商标转让人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprTrademarkTransferPersonRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:detail')")
    @Operation(summary = "企业知识产权商标转让人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprTrademarkTransferPersonRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:queryList')")
    @Operation(summary = "列表查询企业知识产权商标转让人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprTrademarkTransferPersonVO> queryList(DataCompanyIprTrademarkTransferPersonQueryListCommand dataCompanyIprTrademarkTransferPersonQueryListCommand){
        dataCompanyIprTrademarkTransferPersonQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkTransferPersonRepresentationApplicationService.queryList(dataCompanyIprTrademarkTransferPersonQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkTransferPerson:pageQuery')")
    @Operation(summary = "分页查询企业知识产权商标转让人")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprTrademarkTransferPersonVO> pageQueryList(DataCompanyIprTrademarkTransferPersonPageQueryCommand dataCompanyIprTrademarkTransferPersonPageQueryCommand){
        dataCompanyIprTrademarkTransferPersonPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkTransferPersonRepresentationApplicationService.pageQuery(dataCompanyIprTrademarkTransferPersonPageQueryCommand);
    }
}