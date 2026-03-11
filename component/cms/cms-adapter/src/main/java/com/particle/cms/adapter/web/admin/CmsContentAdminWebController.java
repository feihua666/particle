package com.particle.cms.adapter.web.admin;

import com.particle.cms.client.api.representation.ICmsChannelRepresentationApplicationService;
import com.particle.cms.client.api.representation.ICmsSiteRepresentationApplicationService;
import com.particle.cms.client.dto.data.*;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.common.client.dto.command.CommonAuditCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsContentApplicationService;
import com.particle.cms.client.api.representation.ICmsContentRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsContentCreateCommand;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.login.LoginUser;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ICmsChannelRepresentationApplicationService iCmsChannelRepresentationApplicationService;
    @Autowired
    private ICmsSiteRepresentationApplicationService iCmsSiteRepresentationApplicationService;

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
    public SingleResponse<CmsContentVO> delete(@RequestBody CommonIdCommand deleteCommand){
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
    public SingleResponse<CmsContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCmsContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:detail')")
    @Operation(summary = "内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsContentVO> queryDetail(CommonIdCommand detailCommand){
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

    @PreAuthorize("hasAuthority('admin:web:cmsContent:audit')")
    @Operation(summary = "审核内容")
    @PutMapping("/audit")
    @OpLog(name = "审核内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentVO> audit(@RequestBody CommonAuditCommand commonAuditCommand, @Parameter(hidden = true) LoginUser loginUser){
        commonAuditCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        commonAuditCommand.luid(loginUser.getId());
        return iCmsContentApplicationService.audit(commonAuditCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContent:public')")
    @Operation(summary = "发布内容")
    @PutMapping("/public")
    @OpLog(name = "发布内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentVO> publish(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), true);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentApplicationService.publish(commonPublicCommand);
    }
    @PreAuthorize("hasAuthority('admin:web:cmsContent:unPublic')")
    @Operation(summary = "取消发布内容")
    @PutMapping("/unPublic")
    @OpLog(name = "取消发布内容",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentVO> unPublic(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), false);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentApplicationService.publish(commonPublicCommand);
    }
    @Operation(summary = "内容首页地址")
    @GetMapping("/indexItemsUrl")
    public MultiResponse<CmsIndexItemUrlVO> indexItemsUrl(CommonIdCommand detailCommand){
        SingleResponse<CmsIndexUrlVO> cmsIndexUrlVOSingleResponse = indexUrl(detailCommand);
        CmsIndexUrlVO cmsIndexUrlVO = cmsIndexUrlVOSingleResponse.getData();

        List<CmsIndexItemUrlVO> cmsIndexItemUrlVOS = new ArrayList<>(2);
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamic(cmsIndexUrlVO.getDynamicIndexUrl()));
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamic(cmsIndexUrlVO.getDynamicPreviewIndexUrl()));
        return MultiResponse.of(cmsIndexItemUrlVOS);
    }
    @Operation(summary = "内容首页地址")
    @GetMapping("/indexUrl")
    public SingleResponse<CmsIndexUrlVO> indexUrl(CommonIdCommand detailCommand){

        // 查询内容
        SingleResponse<CmsContentVO> cmsContentVOSingleResponse = iCmsContentRepresentationApplicationService.queryDetail(detailCommand);
        CmsContentVO cmsContentVO = cmsContentVOSingleResponse.getData();
        // 查询栏目，栏目可选
        Long cmsChannelId = cmsContentVO.getCmsChannelId();
        CmsChannelVO cmsChannelVO = null;
        if (cmsChannelId != null) {
            SingleResponse<CmsChannelVO> cmsChannelVOSingleResponse = iCmsChannelRepresentationApplicationService.queryDetail(CommonIdCommand.create(cmsChannelId));
            cmsChannelVO = cmsChannelVOSingleResponse.getData();
        }
        // 查询站点
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsSiteRepresentationApplicationService.queryDetail(CommonIdCommand.create(cmsContentVO.getCmsSiteId()));
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();


        CmsTemplateModelContext publishCmsTemplateModelContext = CmsTemplateModelContext.create(true, CmsTemplateModelContext.Mode.publish);
        // 站点模板模型，动态发布模式
        CmsSiteTemplateModelVO publishCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, publishCmsTemplateModelContext);
        // 栏目模板模型，动态发布模式
        CmsChannelTemplateModelVO publishCmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, publishCmsSiteTemplateModelVO, null, publishCmsTemplateModelContext);
        // 内容模板模型，动态发布模式
        CmsContentTemplateModelVO cmsContentTemplateModelVO = CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO, publishCmsSiteTemplateModelVO, publishCmsChannelTemplateModelVO, publishCmsTemplateModelContext);
        String dynamicIndexUrl = cmsContentTemplateModelVO.getIndexUrl();

        CmsTemplateModelContext previewCmsTemplateModelContext = CmsTemplateModelContext.create(true,  CmsTemplateModelContext.Mode.preview);
        // 站点模板模型，动态预览模式
        CmsSiteTemplateModelVO previewCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, previewCmsTemplateModelContext);
        // 栏目模板模型，动态预览模式
        CmsChannelTemplateModelVO previewCmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, previewCmsSiteTemplateModelVO, null, previewCmsTemplateModelContext);
        // 内容模板模型，动态预览模式
        CmsContentTemplateModelVO previewCmsContentTemplateModelVO = CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO, previewCmsSiteTemplateModelVO, previewCmsChannelTemplateModelVO, previewCmsTemplateModelContext);
        String dynamicPreviewIndexUrl = previewCmsContentTemplateModelVO.getIndexUrl();

        CmsIndexUrlVO cmsIndexUrlVO = new CmsIndexUrlVO();
        cmsIndexUrlVO.setDynamicIndexUrl(dynamicIndexUrl);
        cmsIndexUrlVO.setDynamicPreviewIndexUrl(dynamicPreviewIndexUrl);

        return SingleResponse.of(cmsIndexUrlVO);
    }
}
