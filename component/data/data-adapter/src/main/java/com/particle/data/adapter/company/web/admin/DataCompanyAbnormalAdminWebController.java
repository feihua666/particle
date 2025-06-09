package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAbnormalApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAbnormalRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalQueryListCommand;
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
 * 企业经营异常后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Tag(name = "企业经营异常pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_abnormal")
public class DataCompanyAbnormalAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAbnormalApplicationService iDataCompanyAbnormalApplicationService;
    @Autowired
    private IDataCompanyAbnormalRepresentationApplicationService iDataCompanyAbnormalRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:create')")
    @Operation(summary = "添加企业经营异常")
    @PostMapping("/create")
    @OpLog(name = "添加企业经营异常",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAbnormalVO> create(@RequestBody DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand){
        return iDataCompanyAbnormalApplicationService.create(dataCompanyAbnormalCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:delete')")
    @Operation(summary = "删除企业经营异常")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业经营异常",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAbnormalVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAbnormalApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:update')")
    @Operation(summary = "更新企业经营异常")
    @PutMapping("/update")
    @OpLog(name = "更新企业经营异常",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAbnormalVO> update(@RequestBody DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand){
        dataCompanyAbnormalUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAbnormalApplicationService.update(dataCompanyAbnormalUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:update')")
    @Operation(summary = "企业经营异常更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAbnormalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAbnormalRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:detail')")
    @Operation(summary = "企业经营异常详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAbnormalVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAbnormalRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:queryList')")
    @Operation(summary = "列表查询企业经营异常")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAbnormalVO> queryList(DataCompanyAbnormalQueryListCommand dataCompanyAbnormalQueryListCommand){
        dataCompanyAbnormalQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAbnormalRepresentationApplicationService.queryList(dataCompanyAbnormalQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAbnormal:pageQuery')")
    @Operation(summary = "分页查询企业经营异常")
    @GetMapping("/page")
    public PageResponse<DataCompanyAbnormalVO> pageQueryList(DataCompanyAbnormalPageQueryCommand dataCompanyAbnormalPageQueryCommand){
        dataCompanyAbnormalPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAbnormalRepresentationApplicationService.pageQuery(dataCompanyAbnormalPageQueryCommand);
    }
}