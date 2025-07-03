package com.particle.cms.adapter.web.admin;

import com.particle.cms.client.dto.command.CmsContentPublicCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsContentApplicationService;
import com.particle.cms.client.api.representation.ICmsContentRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsContentCreateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentQueryListCommand;
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
 * 内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Tag(name = "内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_content")
public class CmsContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsContentApplicationService iCmsContentApplicationService;
    @Autowired
    private ICmsContentRepresentationApplicationService iCmsContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsContent:create')")
    @Operation(summary = "添加内容")
    @PostMapping("/create")
    @OpLog(name = "添加内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsContentVO> create(@RequestBody CmsContentCreateCommand cmsContentCreateCommand){
        return iCmsContentApplicationService.create(cmsContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:delete')")
    @Operation(summary = "删除内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:update')")
    @Operation(summary = "更新内容")
    @PutMapping("/update")
    @OpLog(name = "更新内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentVO> update(@RequestBody CmsContentUpdateCommand cmsContentUpdateCommand){
        cmsContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentApplicationService.update(cmsContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:update')")
    @Operation(summary = "内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:detail')")
    @Operation(summary = "内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsContentVO> queryDetail(IdCommand detailCommand){
        return iCmsContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:queryList')")
    @Operation(summary = "列表查询内容")
    @GetMapping("/list")
    public MultiResponse<CmsContentVO> queryList(CmsContentQueryListCommand cmsContentQueryListCommand){
        cmsContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentRepresentationApplicationService.queryList(cmsContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:pageQuery')")
    @Operation(summary = "分页查询内容")
    @GetMapping("/page")
    public PageResponse<CmsContentVO> pageQueryList(CmsContentPageQueryCommand cmsContentPageQueryCommand){
        cmsContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentRepresentationApplicationService.pageQuery(cmsContentPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:public')")
    @Operation(summary = "发布内容")
    @PutMapping("/public")
    @OpLog(name = "发布内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentVO> publish(@RequestBody CmsContentPublicCommand cmsContentPublicCommand){
        cmsContentPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentApplicationService.publish(cmsContentPublicCommand);
    }
}
