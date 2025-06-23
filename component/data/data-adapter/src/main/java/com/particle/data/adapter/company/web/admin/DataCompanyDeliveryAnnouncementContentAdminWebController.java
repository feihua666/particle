package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementContentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyDeliveryAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentQueryListCommand;
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
 * 企业送达公告内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Tag(name = "企业送达公告内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_delivery_announcement_content")
public class DataCompanyDeliveryAnnouncementContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyDeliveryAnnouncementContentApplicationService iDataCompanyDeliveryAnnouncementContentApplicationService;
    @Autowired
    private IDataCompanyDeliveryAnnouncementContentRepresentationApplicationService iDataCompanyDeliveryAnnouncementContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:create')")
    @Operation(summary = "添加企业送达公告内容")
    @PostMapping("/create")
    @OpLog(name = "添加企业送达公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> create(@RequestBody DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand){
        return iDataCompanyDeliveryAnnouncementContentApplicationService.create(dataCompanyDeliveryAnnouncementContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:delete')")
    @Operation(summary = "删除企业送达公告内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业送达公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyDeliveryAnnouncementContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:update')")
    @Operation(summary = "更新企业送达公告内容")
    @PutMapping("/update")
    @OpLog(name = "更新企业送达公告内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> update(@RequestBody DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand){
        dataCompanyDeliveryAnnouncementContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyDeliveryAnnouncementContentApplicationService.update(dataCompanyDeliveryAnnouncementContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:update')")
    @Operation(summary = "企业送达公告内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyDeliveryAnnouncementContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:detail')")
    @Operation(summary = "企业送达公告内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyDeliveryAnnouncementContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:queryList')")
    @Operation(summary = "列表查询企业送达公告内容")
    @GetMapping("/list")
    public MultiResponse<DataCompanyDeliveryAnnouncementContentVO> queryList(DataCompanyDeliveryAnnouncementContentQueryListCommand dataCompanyDeliveryAnnouncementContentQueryListCommand){
        dataCompanyDeliveryAnnouncementContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDeliveryAnnouncementContentRepresentationApplicationService.queryList(dataCompanyDeliveryAnnouncementContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDeliveryAnnouncementContent:pageQuery')")
    @Operation(summary = "分页查询企业送达公告内容")
    @GetMapping("/page")
    public PageResponse<DataCompanyDeliveryAnnouncementContentVO> pageQueryList(DataCompanyDeliveryAnnouncementContentPageQueryCommand dataCompanyDeliveryAnnouncementContentPageQueryCommand){
        dataCompanyDeliveryAnnouncementContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDeliveryAnnouncementContentRepresentationApplicationService.pageQuery(dataCompanyDeliveryAnnouncementContentPageQueryCommand);
    }
}