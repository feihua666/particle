package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprGeograApproveAnnouncementApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementQueryListCommand;
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
 * 企业知识产权地理标识核准公告后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Tag(name = "企业知识产权地理标识核准公告pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_geogra_approve_announcement")
public class DataCompanyIprGeograApproveAnnouncementAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprGeograApproveAnnouncementApplicationService iDataCompanyIprGeograApproveAnnouncementApplicationService;
    @Autowired
    private IDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService iDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:create')")
    @Operation(summary = "添加企业知识产权地理标识核准公告")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权地理标识核准公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> create(@RequestBody DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand){
        return iDataCompanyIprGeograApproveAnnouncementApplicationService.create(dataCompanyIprGeograApproveAnnouncementCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:delete')")
    @Operation(summary = "删除企业知识产权地理标识核准公告")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权地理标识核准公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprGeograApproveAnnouncementApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:update')")
    @Operation(summary = "更新企业知识产权地理标识核准公告")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权地理标识核准公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> update(@RequestBody DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand){
        dataCompanyIprGeograApproveAnnouncementUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprGeograApproveAnnouncementApplicationService.update(dataCompanyIprGeograApproveAnnouncementUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:update')")
    @Operation(summary = "企业知识产权地理标识核准公告更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:detail')")
    @Operation(summary = "企业知识产权地理标识核准公告详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:queryList')")
    @Operation(summary = "列表查询企业知识产权地理标识核准公告")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprGeograApproveAnnouncementVO> queryList(DataCompanyIprGeograApproveAnnouncementQueryListCommand dataCompanyIprGeograApproveAnnouncementQueryListCommand){
        dataCompanyIprGeograApproveAnnouncementQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService.queryList(dataCompanyIprGeograApproveAnnouncementQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprGeograApproveAnnouncement:pageQuery')")
    @Operation(summary = "分页查询企业知识产权地理标识核准公告")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprGeograApproveAnnouncementVO> pageQueryList(DataCompanyIprGeograApproveAnnouncementPageQueryCommand dataCompanyIprGeograApproveAnnouncementPageQueryCommand){
        dataCompanyIprGeograApproveAnnouncementPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService.pageQuery(dataCompanyIprGeograApproveAnnouncementPageQueryCommand);
    }
}