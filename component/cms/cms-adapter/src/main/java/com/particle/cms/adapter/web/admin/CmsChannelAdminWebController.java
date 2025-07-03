package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsChannelApplicationService;
import com.particle.cms.client.api.representation.ICmsChannelRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsChannelCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsChannelUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
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
 * 栏目后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Tag(name = "栏目pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_channel")
public class CmsChannelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsChannelApplicationService iCmsChannelApplicationService;
    @Autowired
    private ICmsChannelRepresentationApplicationService iCmsChannelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:create')")
    @Operation(summary = "添加栏目")
    @PostMapping("/create")
    @OpLog(name = "添加栏目",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsChannelVO> create(@RequestBody CmsChannelCreateCommand cmsChannelCreateCommand){
        return iCmsChannelApplicationService.create(cmsChannelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:delete')")
    @Operation(summary = "删除栏目")
    @DeleteMapping("/delete")
    @OpLog(name = "删除栏目",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsChannelVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsChannelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:update')")
    @Operation(summary = "更新栏目")
    @PutMapping("/update")
    @OpLog(name = "更新栏目",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsChannelVO> update(@RequestBody CmsChannelUpdateCommand cmsChannelUpdateCommand){
        cmsChannelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsChannelApplicationService.update(cmsChannelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:update')")
    @Operation(summary = "栏目更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsChannelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsChannelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:detail')")
    @Operation(summary = "栏目详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsChannelVO> queryDetail(IdCommand detailCommand){
        return iCmsChannelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:queryList')")
    @Operation(summary = "列表查询栏目")
    @GetMapping("/list")
    public MultiResponse<CmsChannelVO> queryList(CmsChannelQueryListCommand cmsChannelQueryListCommand){
        cmsChannelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsChannelRepresentationApplicationService.queryList(cmsChannelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:pageQuery')")
    @Operation(summary = "分页查询栏目")
    @GetMapping("/page")
    public PageResponse<CmsChannelVO> pageQueryList(CmsChannelPageQueryCommand cmsChannelPageQueryCommand){
        cmsChannelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsChannelRepresentationApplicationService.pageQuery(cmsChannelPageQueryCommand);
    }
}
