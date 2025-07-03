package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import com.particle.cms.client.api.representation.ICmsChannelViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsChannelViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsChannelViewRecordUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordQueryListCommand;
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
 * 栏目访问记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Tag(name = "栏目访问记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_channel_view_record")
public class CmsChannelViewRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsChannelViewRecordApplicationService iCmsChannelViewRecordApplicationService;
    @Autowired
    private ICmsChannelViewRecordRepresentationApplicationService iCmsChannelViewRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:create')")
    @Operation(summary = "添加栏目访问记录")
    @PostMapping("/create")
    @OpLog(name = "添加栏目访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsChannelViewRecordVO> create(@RequestBody CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand){
        return iCmsChannelViewRecordApplicationService.create(cmsChannelViewRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:delete')")
    @Operation(summary = "删除栏目访问记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除栏目访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsChannelViewRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsChannelViewRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:update')")
    @Operation(summary = "更新栏目访问记录")
    @PutMapping("/update")
    @OpLog(name = "更新栏目访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsChannelViewRecordVO> update(@RequestBody CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand){
        cmsChannelViewRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsChannelViewRecordApplicationService.update(cmsChannelViewRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:update')")
    @Operation(summary = "栏目访问记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsChannelViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsChannelViewRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:detail')")
    @Operation(summary = "栏目访问记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsChannelViewRecordVO> queryDetail(IdCommand detailCommand){
        return iCmsChannelViewRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:queryList')")
    @Operation(summary = "列表查询栏目访问记录")
    @GetMapping("/list")
    public MultiResponse<CmsChannelViewRecordVO> queryList(CmsChannelViewRecordQueryListCommand cmsChannelViewRecordQueryListCommand){
        cmsChannelViewRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsChannelViewRecordRepresentationApplicationService.queryList(cmsChannelViewRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannelViewRecord:pageQuery')")
    @Operation(summary = "分页查询栏目访问记录")
    @GetMapping("/page")
    public PageResponse<CmsChannelViewRecordVO> pageQueryList(CmsChannelViewRecordPageQueryCommand cmsChannelViewRecordPageQueryCommand){
        cmsChannelViewRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsChannelViewRecordRepresentationApplicationService.pageQuery(cmsChannelViewRecordPageQueryCommand);
    }
}
