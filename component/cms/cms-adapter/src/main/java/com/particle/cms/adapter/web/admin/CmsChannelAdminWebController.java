package com.particle.cms.adapter.web.admin;

import com.particle.cms.client.api.ICmsChannelApplicationService;
import com.particle.cms.client.api.representation.ICmsChannelRepresentationApplicationService;
import com.particle.cms.client.api.representation.ICmsSiteRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsChannelCreateCommand;
import com.particle.cms.client.dto.command.CmsChannelUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsIndexItemUrlVO;
import com.particle.cms.client.dto.data.CmsIndexUrlVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ICmsSiteRepresentationApplicationService iCmsSiteRepresentationApplicationService;

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
    public SingleResponse<CmsChannelVO> delete(@RequestBody CommonIdCommand deleteCommand){
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
    public SingleResponse<CmsChannelVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCmsChannelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:detail')")
    @Operation(summary = "栏目详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsChannelVO> queryDetail(CommonIdCommand detailCommand){
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

    @PreAuthorize("hasAuthority('admin:web:cmsChannel:public')")
    @Operation(summary = "发布栏目")
    @PutMapping("/public")
    @OpLog(name = "发布栏目",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsChannelVO> publish(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), true);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsChannelApplicationService.publish(commonPublicCommand);
    }
    @PreAuthorize("hasAuthority('admin:web:cmsChannel:unPublic')")
    @Operation(summary = "取消发布栏目")
    @PutMapping("/unPublic")
    @OpLog(name = "取消发布栏目",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsChannelVO> unPublic(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), false);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsChannelApplicationService.publish(commonPublicCommand);
    }
    @Operation(summary = "栏目首页地址")
    @GetMapping("/indexItemsUrl")
    public MultiResponse<CmsIndexItemUrlVO> indexItemsUrl(CommonIdCommand detailCommand){
        SingleResponse<CmsIndexUrlVO> cmsIndexUrlVOSingleResponse = channelIndexUrlVO(detailCommand);
        CmsIndexUrlVO cmsIndexUrlVO = cmsIndexUrlVOSingleResponse.getData();

        List<CmsIndexItemUrlVO> cmsIndexItemUrlVOS = new ArrayList<>(2);
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamic(cmsIndexUrlVO.getDynamicIndexUrl()));
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamic(cmsIndexUrlVO.getDynamicPreviewIndexUrl()));
        return MultiResponse.of(cmsIndexItemUrlVOS);
    }
    @Operation(summary = "栏目首页地址")
    @GetMapping("/indexUrl")
    public SingleResponse<CmsIndexUrlVO> channelIndexUrlVO(CommonIdCommand detailCommand){

        // 查询栏目
        SingleResponse<CmsChannelVO> cmsChannelVOSingleResponse = iCmsChannelRepresentationApplicationService.queryDetail(detailCommand);
        CmsChannelVO cmsChannelVO = cmsChannelVOSingleResponse.getData();
        // 查询站点
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsSiteRepresentationApplicationService.queryDetail(CommonIdCommand.create(cmsChannelVO.getCmsSiteId()));
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();



        CmsTemplateModelContext publishCmsTemplateModelContext = CmsTemplateModelContext.create(true, CmsTemplateModelContext.Mode.publish);
        // 站点模板模型，动态发布模式
        CmsSiteTemplateModelVO publishCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, publishCmsTemplateModelContext);
        // 栏目模板模型，动态发布模式
        CmsChannelTemplateModelVO publishCmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, publishCmsSiteTemplateModelVO, null, publishCmsTemplateModelContext);
        String dynamicIndexUrl = publishCmsChannelTemplateModelVO.getIndexUrl();

        CmsTemplateModelContext previewCmsTemplateModelContext = CmsTemplateModelContext.create(true,  CmsTemplateModelContext.Mode.preview);
        // 站点模板模型，动态预览模式
        CmsSiteTemplateModelVO previewCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, previewCmsTemplateModelContext);
        // 栏目模板模型，动态预览模式
        CmsChannelTemplateModelVO previewCmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, previewCmsSiteTemplateModelVO, null, previewCmsTemplateModelContext);
        String dynamicPreviewIndexUrl = previewCmsChannelTemplateModelVO.getIndexUrl();

        // 结果封装
        CmsIndexUrlVO cmsIndexUrlVO = new CmsIndexUrlVO();
        cmsIndexUrlVO.setDynamicIndexUrl(dynamicIndexUrl);
        cmsIndexUrlVO.setDynamicPreviewIndexUrl(dynamicPreviewIndexUrl);

        return SingleResponse.of(cmsIndexUrlVO);
    }
}
