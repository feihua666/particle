package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsContentCategoryApplicationService;
import com.particle.cms.client.api.representation.ICmsContentCategoryRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsContentCategoryCreateCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentCategoryUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryQueryListCommand;
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
 * 内容分类后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Tag(name = "内容分类pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_content_category")
public class CmsContentCategoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsContentCategoryApplicationService iCmsContentCategoryApplicationService;
    @Autowired
    private ICmsContentCategoryRepresentationApplicationService iCmsContentCategoryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:create')")
    @Operation(summary = "添加内容分类")
    @PostMapping("/create")
    @OpLog(name = "添加内容分类",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsContentCategoryVO> create(@RequestBody CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand){
        return iCmsContentCategoryApplicationService.create(cmsContentCategoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:delete')")
    @Operation(summary = "删除内容分类")
    @DeleteMapping("/delete")
    @OpLog(name = "删除内容分类",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsContentCategoryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsContentCategoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:update')")
    @Operation(summary = "更新内容分类")
    @PutMapping("/update")
    @OpLog(name = "更新内容分类",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentCategoryVO> update(@RequestBody CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand){
        cmsContentCategoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentCategoryApplicationService.update(cmsContentCategoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:update')")
    @Operation(summary = "内容分类更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsContentCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsContentCategoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:detail')")
    @Operation(summary = "内容分类详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsContentCategoryVO> queryDetail(IdCommand detailCommand){
        return iCmsContentCategoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:queryList')")
    @Operation(summary = "列表查询内容分类")
    @GetMapping("/list")
    public MultiResponse<CmsContentCategoryVO> queryList(CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand){
        cmsContentCategoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentCategoryRepresentationApplicationService.queryList(cmsContentCategoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentCategory:pageQuery')")
    @Operation(summary = "分页查询内容分类")
    @GetMapping("/page")
    public PageResponse<CmsContentCategoryVO> pageQueryList(CmsContentCategoryPageQueryCommand cmsContentCategoryPageQueryCommand){
        cmsContentCategoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentCategoryRepresentationApplicationService.pageQuery(cmsContentCategoryPageQueryCommand);
    }
}
