package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import com.particle.cms.client.api.representation.ICmsTemplateContentRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsTemplateContentCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsTemplateContentUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentQueryListCommand;
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
 * 模板内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Tag(name = "模板内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_template_content")
public class CmsTemplateContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsTemplateContentApplicationService iCmsTemplateContentApplicationService;
    @Autowired
    private ICmsTemplateContentRepresentationApplicationService iCmsTemplateContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:create')")
    @Operation(summary = "添加模板内容")
    @PostMapping("/create")
    @OpLog(name = "添加模板内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsTemplateContentVO> create(@RequestBody CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand){
        return iCmsTemplateContentApplicationService.create(cmsTemplateContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:delete')")
    @Operation(summary = "删除模板内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除模板内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsTemplateContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsTemplateContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:update')")
    @Operation(summary = "更新模板内容")
    @PutMapping("/update")
    @OpLog(name = "更新模板内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsTemplateContentVO> update(@RequestBody CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand){
        cmsTemplateContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsTemplateContentApplicationService.update(cmsTemplateContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:update')")
    @Operation(summary = "模板内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsTemplateContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsTemplateContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:detail')")
    @Operation(summary = "模板内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsTemplateContentVO> queryDetail(IdCommand detailCommand){
        return iCmsTemplateContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:queryList')")
    @Operation(summary = "列表查询模板内容")
    @GetMapping("/list")
    public MultiResponse<CmsTemplateContentVO> queryList(CmsTemplateContentQueryListCommand cmsTemplateContentQueryListCommand){
        cmsTemplateContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsTemplateContentRepresentationApplicationService.queryList(cmsTemplateContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplateContent:pageQuery')")
    @Operation(summary = "分页查询模板内容")
    @GetMapping("/page")
    public PageResponse<CmsTemplateContentVO> pageQueryList(CmsTemplateContentPageQueryCommand cmsTemplateContentPageQueryCommand){
        cmsTemplateContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsTemplateContentRepresentationApplicationService.pageQuery(cmsTemplateContentPageQueryCommand);
    }
}
