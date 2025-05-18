package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumePartyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyRestrictHighConsumePartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyQueryListCommand;
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
 * 企业限制高消费当事人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Tag(name = "企业限制高消费当事人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_restrict_high_consume_party")
public class DataCompanyRestrictHighConsumePartyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyRestrictHighConsumePartyApplicationService iDataCompanyRestrictHighConsumePartyApplicationService;
    @Autowired
    private IDataCompanyRestrictHighConsumePartyRepresentationApplicationService iDataCompanyRestrictHighConsumePartyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:create')")
    @Operation(summary = "添加企业限制高消费当事人")
    @PostMapping("/create")
    @OpLog(name = "添加企业限制高消费当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> create(@RequestBody DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand){
        return iDataCompanyRestrictHighConsumePartyApplicationService.create(dataCompanyRestrictHighConsumePartyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:delete')")
    @Operation(summary = "删除企业限制高消费当事人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业限制高消费当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyRestrictHighConsumePartyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:update')")
    @Operation(summary = "更新企业限制高消费当事人")
    @PutMapping("/update")
    @OpLog(name = "更新企业限制高消费当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> update(@RequestBody DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand){
        dataCompanyRestrictHighConsumePartyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyRestrictHighConsumePartyApplicationService.update(dataCompanyRestrictHighConsumePartyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:update')")
    @Operation(summary = "企业限制高消费当事人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyRestrictHighConsumePartyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:detail')")
    @Operation(summary = "企业限制高消费当事人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyRestrictHighConsumePartyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:queryList')")
    @Operation(summary = "列表查询企业限制高消费当事人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyRestrictHighConsumePartyVO> queryList(DataCompanyRestrictHighConsumePartyQueryListCommand dataCompanyRestrictHighConsumePartyQueryListCommand){
        dataCompanyRestrictHighConsumePartyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRestrictHighConsumePartyRepresentationApplicationService.queryList(dataCompanyRestrictHighConsumePartyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyRestrictHighConsumeParty:pageQuery')")
    @Operation(summary = "分页查询企业限制高消费当事人")
    @GetMapping("/page")
    public PageResponse<DataCompanyRestrictHighConsumePartyVO> pageQueryList(DataCompanyRestrictHighConsumePartyPageQueryCommand dataCompanyRestrictHighConsumePartyPageQueryCommand){
        dataCompanyRestrictHighConsumePartyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRestrictHighConsumePartyRepresentationApplicationService.pageQuery(dataCompanyRestrictHighConsumePartyPageQueryCommand);
    }
}