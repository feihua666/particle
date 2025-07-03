package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsContentMultimediaApplicationService;
import com.particle.cms.client.api.representation.ICmsContentMultimediaRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsContentMultimediaCreateCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentMultimediaUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaQueryListCommand;
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
 * 内容多媒体后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Tag(name = "内容多媒体pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_content_multimedia")
public class CmsContentMultimediaAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsContentMultimediaApplicationService iCmsContentMultimediaApplicationService;
    @Autowired
    private ICmsContentMultimediaRepresentationApplicationService iCmsContentMultimediaRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:create')")
    @Operation(summary = "添加内容多媒体")
    @PostMapping("/create")
    @OpLog(name = "添加内容多媒体",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsContentMultimediaVO> create(@RequestBody CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand){
        return iCmsContentMultimediaApplicationService.create(cmsContentMultimediaCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:delete')")
    @Operation(summary = "删除内容多媒体")
    @DeleteMapping("/delete")
    @OpLog(name = "删除内容多媒体",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsContentMultimediaVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsContentMultimediaApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:update')")
    @Operation(summary = "更新内容多媒体")
    @PutMapping("/update")
    @OpLog(name = "更新内容多媒体",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentMultimediaVO> update(@RequestBody CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand){
        cmsContentMultimediaUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentMultimediaApplicationService.update(cmsContentMultimediaUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:update')")
    @Operation(summary = "内容多媒体更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsContentMultimediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsContentMultimediaRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:detail')")
    @Operation(summary = "内容多媒体详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsContentMultimediaVO> queryDetail(IdCommand detailCommand){
        return iCmsContentMultimediaRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:queryList')")
    @Operation(summary = "列表查询内容多媒体")
    @GetMapping("/list")
    public MultiResponse<CmsContentMultimediaVO> queryList(CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand){
        cmsContentMultimediaQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentMultimediaRepresentationApplicationService.queryList(cmsContentMultimediaQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentMultimedia:pageQuery')")
    @Operation(summary = "分页查询内容多媒体")
    @GetMapping("/page")
    public PageResponse<CmsContentMultimediaVO> pageQueryList(CmsContentMultimediaPageQueryCommand cmsContentMultimediaPageQueryCommand){
        cmsContentMultimediaPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentMultimediaRepresentationApplicationService.pageQuery(cmsContentMultimediaPageQueryCommand);
    }
}
