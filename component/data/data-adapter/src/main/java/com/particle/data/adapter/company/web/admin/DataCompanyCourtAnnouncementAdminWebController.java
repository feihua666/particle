package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyCourtAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementQueryListCommand;
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
 * 企业法院公告后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Tag(name = "企业法院公告pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_court_announcement")
public class DataCompanyCourtAnnouncementAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyCourtAnnouncementApplicationService iDataCompanyCourtAnnouncementApplicationService;
    @Autowired
    private IDataCompanyCourtAnnouncementRepresentationApplicationService iDataCompanyCourtAnnouncementRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:create')")
    @Operation(summary = "添加企业法院公告")
    @PostMapping("/create")
    @OpLog(name = "添加企业法院公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyCourtAnnouncementVO> create(@RequestBody DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand){
        return iDataCompanyCourtAnnouncementApplicationService.create(dataCompanyCourtAnnouncementCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:delete')")
    @Operation(summary = "删除企业法院公告")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业法院公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyCourtAnnouncementVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyCourtAnnouncementApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:update')")
    @Operation(summary = "更新企业法院公告")
    @PutMapping("/update")
    @OpLog(name = "更新企业法院公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyCourtAnnouncementVO> update(@RequestBody DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand){
        dataCompanyCourtAnnouncementUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyCourtAnnouncementApplicationService.update(dataCompanyCourtAnnouncementUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:update')")
    @Operation(summary = "企业法院公告更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyCourtAnnouncementRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:detail')")
    @Operation(summary = "企业法院公告详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyCourtAnnouncementVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyCourtAnnouncementRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:queryList')")
    @Operation(summary = "列表查询企业法院公告")
    @GetMapping("/list")
    public MultiResponse<DataCompanyCourtAnnouncementVO> queryList(DataCompanyCourtAnnouncementQueryListCommand dataCompanyCourtAnnouncementQueryListCommand){
        dataCompanyCourtAnnouncementQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCourtAnnouncementRepresentationApplicationService.queryList(dataCompanyCourtAnnouncementQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncement:pageQuery')")
    @Operation(summary = "分页查询企业法院公告")
    @GetMapping("/page")
    public PageResponse<DataCompanyCourtAnnouncementVO> pageQueryList(DataCompanyCourtAnnouncementPageQueryCommand dataCompanyCourtAnnouncementPageQueryCommand){
        dataCompanyCourtAnnouncementPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCourtAnnouncementRepresentationApplicationService.pageQuery(dataCompanyCourtAnnouncementPageQueryCommand);
    }
}