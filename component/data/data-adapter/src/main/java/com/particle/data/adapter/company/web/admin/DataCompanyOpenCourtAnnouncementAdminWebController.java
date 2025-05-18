package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementQueryListCommand;
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
 * 企业开庭公告后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Tag(name = "企业开庭公告pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_open_court_announcement")
public class DataCompanyOpenCourtAnnouncementAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyOpenCourtAnnouncementApplicationService iDataCompanyOpenCourtAnnouncementApplicationService;
    @Autowired
    private IDataCompanyOpenCourtAnnouncementRepresentationApplicationService iDataCompanyOpenCourtAnnouncementRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:create')")
    @Operation(summary = "添加企业开庭公告")
    @PostMapping("/create")
    @OpLog(name = "添加企业开庭公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> create(@RequestBody DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand){
        return iDataCompanyOpenCourtAnnouncementApplicationService.create(dataCompanyOpenCourtAnnouncementCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:delete')")
    @Operation(summary = "删除企业开庭公告")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业开庭公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyOpenCourtAnnouncementApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:update')")
    @Operation(summary = "更新企业开庭公告")
    @PutMapping("/update")
    @OpLog(name = "更新企业开庭公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> update(@RequestBody DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand){
        dataCompanyOpenCourtAnnouncementUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyOpenCourtAnnouncementApplicationService.update(dataCompanyOpenCourtAnnouncementUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:update')")
    @Operation(summary = "企业开庭公告更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyOpenCourtAnnouncementRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:detail')")
    @Operation(summary = "企业开庭公告详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyOpenCourtAnnouncementRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:queryList')")
    @Operation(summary = "列表查询企业开庭公告")
    @GetMapping("/list")
    public MultiResponse<DataCompanyOpenCourtAnnouncementVO> queryList(DataCompanyOpenCourtAnnouncementQueryListCommand dataCompanyOpenCourtAnnouncementQueryListCommand){
        dataCompanyOpenCourtAnnouncementQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementRepresentationApplicationService.queryList(dataCompanyOpenCourtAnnouncementQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncement:pageQuery')")
    @Operation(summary = "分页查询企业开庭公告")
    @GetMapping("/page")
    public PageResponse<DataCompanyOpenCourtAnnouncementVO> pageQueryList(DataCompanyOpenCourtAnnouncementPageQueryCommand dataCompanyOpenCourtAnnouncementPageQueryCommand){
        dataCompanyOpenCourtAnnouncementPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementRepresentationApplicationService.pageQuery(dataCompanyOpenCourtAnnouncementPageQueryCommand);
    }
}