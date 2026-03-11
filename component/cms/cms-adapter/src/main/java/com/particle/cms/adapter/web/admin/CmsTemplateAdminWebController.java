package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import com.particle.cms.client.api.representation.ICmsTemplateRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsTemplateCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.cms.client.dto.command.CmsTemplateUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplatePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateQueryListCommand;
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
 * 模板后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Tag(name = "模板pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_template")
public class CmsTemplateAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsTemplateApplicationService iCmsTemplateApplicationService;
    @Autowired
    private ICmsTemplateRepresentationApplicationService iCmsTemplateRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:create')")
    @Operation(summary = "添加模板")
    @PostMapping("/create")
    @OpLog(name = "添加模板",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsTemplateVO> create(@RequestBody CmsTemplateCreateCommand cmsTemplateCreateCommand){
        return iCmsTemplateApplicationService.create(cmsTemplateCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:delete')")
    @Operation(summary = "删除模板")
    @DeleteMapping("/delete")
    @OpLog(name = "删除模板",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsTemplateVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsTemplateApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:update')")
    @Operation(summary = "更新模板")
    @PutMapping("/update")
    @OpLog(name = "更新模板",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsTemplateVO> update(@RequestBody CmsTemplateUpdateCommand cmsTemplateUpdateCommand){
        cmsTemplateUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsTemplateApplicationService.update(cmsTemplateUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:update')")
    @Operation(summary = "模板更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsTemplateVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCmsTemplateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:detail')")
    @Operation(summary = "模板详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsTemplateVO> queryDetail(CommonIdCommand detailCommand){
        return iCmsTemplateRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:queryList')")
    @Operation(summary = "列表查询模板")
    @GetMapping("/list")
    public MultiResponse<CmsTemplateVO> queryList(CmsTemplateQueryListCommand cmsTemplateQueryListCommand){
        cmsTemplateQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsTemplateRepresentationApplicationService.queryList(cmsTemplateQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsTemplate:pageQuery')")
    @Operation(summary = "分页查询模板")
    @GetMapping("/page")
    public PageResponse<CmsTemplateVO> pageQueryList(CmsTemplatePageQueryCommand cmsTemplatePageQueryCommand){
        cmsTemplatePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsTemplateRepresentationApplicationService.pageQuery(cmsTemplatePageQueryCommand);
    }
}
