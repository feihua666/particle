package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementContentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyCourtAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentQueryListCommand;
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
 * 企业法院公告内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Tag(name = "企业法院公告内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_court_announcement_content")
public class DataCompanyCourtAnnouncementContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyCourtAnnouncementContentApplicationService iDataCompanyCourtAnnouncementContentApplicationService;
    @Autowired
    private IDataCompanyCourtAnnouncementContentRepresentationApplicationService iDataCompanyCourtAnnouncementContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:create')")
    @Operation(summary = "添加企业法院公告内容")
    @PostMapping("/create")
    @OpLog(name = "添加企业法院公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> create(@RequestBody DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand){
        return iDataCompanyCourtAnnouncementContentApplicationService.create(dataCompanyCourtAnnouncementContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:delete')")
    @Operation(summary = "删除企业法院公告内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业法院公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyCourtAnnouncementContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:update')")
    @Operation(summary = "更新企业法院公告内容")
    @PutMapping("/update")
    @OpLog(name = "更新企业法院公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> update(@RequestBody DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand){
        dataCompanyCourtAnnouncementContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyCourtAnnouncementContentApplicationService.update(dataCompanyCourtAnnouncementContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:update')")
    @Operation(summary = "企业法院公告内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyCourtAnnouncementContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:detail')")
    @Operation(summary = "企业法院公告内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyCourtAnnouncementContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:queryList')")
    @Operation(summary = "列表查询企业法院公告内容")
    @GetMapping("/list")
    public MultiResponse<DataCompanyCourtAnnouncementContentVO> queryList(DataCompanyCourtAnnouncementContentQueryListCommand dataCompanyCourtAnnouncementContentQueryListCommand){
        dataCompanyCourtAnnouncementContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCourtAnnouncementContentRepresentationApplicationService.queryList(dataCompanyCourtAnnouncementContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCourtAnnouncementContent:pageQuery')")
    @Operation(summary = "分页查询企业法院公告内容")
    @GetMapping("/page")
    public PageResponse<DataCompanyCourtAnnouncementContentVO> pageQueryList(DataCompanyCourtAnnouncementContentPageQueryCommand dataCompanyCourtAnnouncementContentPageQueryCommand){
        dataCompanyCourtAnnouncementContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCourtAnnouncementContentRepresentationApplicationService.pageQuery(dataCompanyCourtAnnouncementContentPageQueryCommand);
    }
}