package com.particle.data.adapter.temp.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.data.client.temp.api.IDataCompanyMd5IdsApplicationService;
import com.particle.data.client.temp.api.representation.IDataCompanyMd5IdsRepresentationApplicationService;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsCreateCommand;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsUpdateCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsPageQueryCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsQueryListCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
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
 * 企业md5ids后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Tag(name = "企业md5idspc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_md5_ids")
public class DataCompanyMd5IdsAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyMd5IdsApplicationService iDataCompanyMd5IdsApplicationService;
    @Autowired
    private IDataCompanyMd5IdsRepresentationApplicationService iDataCompanyMd5IdsRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:create')")
    @Operation(summary = "添加企业md5ids")
    @PostMapping("/create")
    @OpLog(name = "添加企业md5ids",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyMd5IdsVO> create(@RequestBody DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand){
        return iDataCompanyMd5IdsApplicationService.create(dataCompanyMd5IdsCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:delete')")
    @Operation(summary = "删除企业md5ids")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业md5ids",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyMd5IdsVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyMd5IdsApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:update')")
    @Operation(summary = "更新企业md5ids")
    @PutMapping("/update")
    @OpLog(name = "更新企业md5ids",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyMd5IdsVO> update(@RequestBody DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand){
        dataCompanyMd5IdsUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyMd5IdsApplicationService.update(dataCompanyMd5IdsUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:update')")
    @Operation(summary = "企业md5ids更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyMd5IdsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyMd5IdsRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:detail')")
    @Operation(summary = "企业md5ids详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyMd5IdsVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyMd5IdsRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:queryList')")
    @Operation(summary = "列表查询企业md5ids")
    @GetMapping("/list")
    public MultiResponse<DataCompanyMd5IdsVO> queryList(DataCompanyMd5IdsQueryListCommand dataCompanyMd5IdsQueryListCommand){
        dataCompanyMd5IdsQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyMd5IdsRepresentationApplicationService.queryList(dataCompanyMd5IdsQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyMd5Ids:pageQuery')")
    @Operation(summary = "分页查询企业md5ids")
    @GetMapping("/page")
    public PageResponse<DataCompanyMd5IdsVO> pageQueryList(DataCompanyMd5IdsPageQueryCommand dataCompanyMd5IdsPageQueryCommand){
        dataCompanyMd5IdsPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyMd5IdsRepresentationApplicationService.pageQuery(dataCompanyMd5IdsPageQueryCommand);
    }
}
