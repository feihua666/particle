package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyDeliveryAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementQueryListCommand;
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
 * 企业送达公告后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Tag(name = "企业送达公告pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_delivery_announcement")
public class DataCompanyDeliveryAnnouncementAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyDeliveryAnnouncementApplicationService iDataCompanyDeliveryAnnouncementApplicationService;
    @Autowired
    private IDataCompanyDeliveryAnnouncementRepresentationApplicationService iDataCompanyDeliveryAnnouncementRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:create')")
    @Operation(summary = "添加企业送达公告")
    @PostMapping("/create")
    @OpLog(name = "添加企业送达公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> create(@RequestBody DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand){
        return iDataCompanyDeliveryAnnouncementApplicationService.create(dataCompanyDeliveryAnnouncementCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:delete')")
    @Operation(summary = "删除企业送达公告")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业送达公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyDeliveryAnnouncementApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:update')")
    @Operation(summary = "更新企业送达公告")
    @PutMapping("/update")
    @OpLog(name = "更新企业送达公告",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> update(@RequestBody DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand){
        dataCompanyDeliveryAnnouncementUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyDeliveryAnnouncementApplicationService.update(dataCompanyDeliveryAnnouncementUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:update')")
    @Operation(summary = "企业送达公告更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyDeliveryAnnouncementRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:detail')")
    @Operation(summary = "企业送达公告详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyDeliveryAnnouncementRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:queryList')")
    @Operation(summary = "列表查询企业送达公告")
    @GetMapping("/list")
    public MultiResponse<DataCompanyDeliveryAnnouncementVO> queryList(DataCompanyDeliveryAnnouncementQueryListCommand dataCompanyDeliveryAnnouncementQueryListCommand){
        dataCompanyDeliveryAnnouncementQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDeliveryAnnouncementRepresentationApplicationService.queryList(dataCompanyDeliveryAnnouncementQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncement:pageQuery')")
    @Operation(summary = "分页查询企业送达公告")
    @GetMapping("/page")
    public PageResponse<DataCompanyDeliveryAnnouncementVO> pageQueryList(DataCompanyDeliveryAnnouncementPageQueryCommand dataCompanyDeliveryAnnouncementPageQueryCommand){
        dataCompanyDeliveryAnnouncementPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDeliveryAnnouncementRepresentationApplicationService.pageQuery(dataCompanyDeliveryAnnouncementPageQueryCommand);
    }
}