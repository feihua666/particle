package com.particle.data.adapter.company.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.data.client.company.api.IDataCompanyMd5ApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyMd5RepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyMd5CreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyMd5UpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5PageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5QueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 企业md5后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Tag(name = "企业md5pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_md5")
public class DataCompanyMd5AdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyMd5ApplicationService iDataCompanyMd5ApplicationService;
    @Autowired
    private IDataCompanyMd5RepresentationApplicationService iDataCompanyMd5RepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:create')")
    @Operation(summary = "添加企业md5")
    @PostMapping("/create")
    @OpLog(name = "添加企业md5",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyMd5VO> create(@RequestBody DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand){
        return iDataCompanyMd5ApplicationService.create(dataCompanyMd5CreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:delete')")
    @Operation(summary = "删除企业md5")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业md5",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyMd5VO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyMd5ApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:update')")
    @Operation(summary = "更新企业md5")
    @PutMapping("/update")
    @OpLog(name = "更新企业md5",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyMd5VO> update(@RequestBody DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand){
        dataCompanyMd5UpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyMd5ApplicationService.update(dataCompanyMd5UpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:update')")
    @Operation(summary = "企业md5更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyMd5VO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyMd5RepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:detail')")
    @Operation(summary = "企业md5详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyMd5VO> queryDetail(IdCommand detailCommand){
        return iDataCompanyMd5RepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:queryList')")
    @Operation(summary = "列表查询企业md5")
    @GetMapping("/list")
    public MultiResponse<DataCompanyMd5VO> queryList(DataCompanyMd5QueryListCommand dataCompanyMd5QueryListCommand){
        dataCompanyMd5QueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyMd5RepresentationApplicationService.queryList(dataCompanyMd5QueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5:pageQuery')")
    @Operation(summary = "分页查询企业md5")
    @GetMapping("/page")
    public PageResponse<DataCompanyMd5VO> pageQueryList(DataCompanyMd5PageQueryCommand dataCompanyMd5PageQueryCommand){
        dataCompanyMd5PageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyMd5RepresentationApplicationService.pageQuery(dataCompanyMd5PageQueryCommand);
    }
}
