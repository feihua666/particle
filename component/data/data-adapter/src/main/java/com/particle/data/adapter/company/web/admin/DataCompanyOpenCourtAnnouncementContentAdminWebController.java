package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementContentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentQueryListCommand;
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
 * 企业开庭公告内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Tag(name = "企业开庭公告内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_open_court_announcement_content")
public class DataCompanyOpenCourtAnnouncementContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyOpenCourtAnnouncementContentApplicationService iDataCompanyOpenCourtAnnouncementContentApplicationService;
    @Autowired
    private IDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService iDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:create')")
    @Operation(summary = "添加企业开庭公告内容")
    @PostMapping("/create")
    @OpLog(name = "添加企业开庭公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> create(@RequestBody DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand){
        return iDataCompanyOpenCourtAnnouncementContentApplicationService.create(dataCompanyOpenCourtAnnouncementContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:delete')")
    @Operation(summary = "删除企业开庭公告内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业开庭公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyOpenCourtAnnouncementContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:update')")
    @Operation(summary = "更新企业开庭公告内容")
    @PutMapping("/update")
    @OpLog(name = "更新企业开庭公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> update(@RequestBody DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand){
        dataCompanyOpenCourtAnnouncementContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyOpenCourtAnnouncementContentApplicationService.update(dataCompanyOpenCourtAnnouncementContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:update')")
    @Operation(summary = "企业开庭公告内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:detail')")
    @Operation(summary = "企业开庭公告内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:queryList')")
    @Operation(summary = "列表查询企业开庭公告内容")
    @GetMapping("/list")
    public MultiResponse<DataCompanyOpenCourtAnnouncementContentVO> queryList(DataCompanyOpenCourtAnnouncementContentQueryListCommand dataCompanyOpenCourtAnnouncementContentQueryListCommand){
        dataCompanyOpenCourtAnnouncementContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService.queryList(dataCompanyOpenCourtAnnouncementContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyOpenCourtAnnouncementContent:pageQuery')")
    @Operation(summary = "分页查询企业开庭公告内容")
    @GetMapping("/page")
    public PageResponse<DataCompanyOpenCourtAnnouncementContentVO> pageQueryList(DataCompanyOpenCourtAnnouncementContentPageQueryCommand dataCompanyOpenCourtAnnouncementContentPageQueryCommand){
        dataCompanyOpenCourtAnnouncementContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService.pageQuery(dataCompanyOpenCourtAnnouncementContentPageQueryCommand);
    }
}