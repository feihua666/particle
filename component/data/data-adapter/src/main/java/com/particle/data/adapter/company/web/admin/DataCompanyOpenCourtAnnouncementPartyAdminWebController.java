package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementPartyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyQueryListCommand;
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
 * 企业开庭公告当事人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Tag(name = "企业开庭公告当事人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_open_court_announcement_party")
public class DataCompanyOpenCourtAnnouncementPartyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyOpenCourtAnnouncementPartyApplicationService iDataCompanyOpenCourtAnnouncementPartyApplicationService;
    @Autowired
    private IDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService iDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:create')")
    @Operation(summary = "添加企业开庭公告当事人")
    @PostMapping("/create")
    @OpLog(name = "添加企业开庭公告当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> create(@RequestBody DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand){
        return iDataCompanyOpenCourtAnnouncementPartyApplicationService.create(dataCompanyOpenCourtAnnouncementPartyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:delete')")
    @Operation(summary = "删除企业开庭公告当事人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业开庭公告当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyOpenCourtAnnouncementPartyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:update')")
    @Operation(summary = "更新企业开庭公告当事人")
    @PutMapping("/update")
    @OpLog(name = "更新企业开庭公告当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> update(@RequestBody DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand){
        dataCompanyOpenCourtAnnouncementPartyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyOpenCourtAnnouncementPartyApplicationService.update(dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:update')")
    @Operation(summary = "企业开庭公告当事人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:detail')")
    @Operation(summary = "企业开庭公告当事人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:queryList')")
    @Operation(summary = "列表查询企业开庭公告当事人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryList(DataCompanyOpenCourtAnnouncementPartyQueryListCommand dataCompanyOpenCourtAnnouncementPartyQueryListCommand){
        dataCompanyOpenCourtAnnouncementPartyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService.queryList(dataCompanyOpenCourtAnnouncementPartyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementParty:pageQuery')")
    @Operation(summary = "分页查询企业开庭公告当事人")
    @GetMapping("/page")
    public PageResponse<DataCompanyOpenCourtAnnouncementPartyVO> pageQueryList(DataCompanyOpenCourtAnnouncementPartyPageQueryCommand dataCompanyOpenCourtAnnouncementPartyPageQueryCommand){
        dataCompanyOpenCourtAnnouncementPartyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService.pageQuery(dataCompanyOpenCourtAnnouncementPartyPageQueryCommand);
    }
}