package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyRestrictHighConsumeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumeQueryListCommand;
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
 * 企业限制高消费后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Tag(name = "企业限制高消费pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_restrict_high_consume")
public class DataCompanyRestrictHighConsumeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyRestrictHighConsumeApplicationService iDataCompanyRestrictHighConsumeApplicationService;
    @Autowired
    private IDataCompanyRestrictHighConsumeRepresentationApplicationService iDataCompanyRestrictHighConsumeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:create')")
    @Operation(summary = "添加企业限制高消费")
    @PostMapping("/create")
    @OpLog(name = "添加企业限制高消费",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyRestrictHighConsumeVO> create(@RequestBody DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand){
        return iDataCompanyRestrictHighConsumeApplicationService.create(dataCompanyRestrictHighConsumeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:delete')")
    @Operation(summary = "删除企业限制高消费")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业限制高消费",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyRestrictHighConsumeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyRestrictHighConsumeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:update')")
    @Operation(summary = "更新企业限制高消费")
    @PutMapping("/update")
    @OpLog(name = "更新企业限制高消费",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyRestrictHighConsumeVO> update(@RequestBody DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand){
        dataCompanyRestrictHighConsumeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyRestrictHighConsumeApplicationService.update(dataCompanyRestrictHighConsumeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:update')")
    @Operation(summary = "企业限制高消费更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyRestrictHighConsumeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:detail')")
    @Operation(summary = "企业限制高消费详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyRestrictHighConsumeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:queryList')")
    @Operation(summary = "列表查询企业限制高消费")
    @GetMapping("/list")
    public MultiResponse<DataCompanyRestrictHighConsumeVO> queryList(DataCompanyRestrictHighConsumeQueryListCommand dataCompanyRestrictHighConsumeQueryListCommand){
        dataCompanyRestrictHighConsumeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRestrictHighConsumeRepresentationApplicationService.queryList(dataCompanyRestrictHighConsumeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsume:pageQuery')")
    @Operation(summary = "分页查询企业限制高消费")
    @GetMapping("/page")
    public PageResponse<DataCompanyRestrictHighConsumeVO> pageQueryList(DataCompanyRestrictHighConsumePageQueryCommand dataCompanyRestrictHighConsumePageQueryCommand){
        dataCompanyRestrictHighConsumePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRestrictHighConsumeRepresentationApplicationService.pageQuery(dataCompanyRestrictHighConsumePageQueryCommand);
    }
}