package com.particle.cms.adapter.web.admin;

import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.api.ICmsSiteApplicationService;
import com.particle.cms.client.api.representation.ICmsSiteRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsSiteCreateCommand;
import com.particle.cms.client.dto.command.CmsSiteUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsSitePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteQueryListCommand;
import com.particle.cms.client.dto.data.CmsIndexItemUrlVO;
import com.particle.cms.client.dto.data.CmsIndexUrlVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 站点后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Tag(name = "站点pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_site")
public class CmsSiteAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsSiteApplicationService iCmsSiteApplicationService;
    @Autowired
    private ICmsSiteRepresentationApplicationService iCmsSiteRepresentationApplicationService;

    @Autowired
    protected ICmsDynamicApplicationService iCmsDynamicApplicationService;
    @PreAuthorize("hasAuthority('admin:web:cmsSite:create')")
    @Operation(summary = "添加站点")
    @PostMapping("/create")
    @OpLog(name = "添加站点",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsSiteVO> create(@RequestBody CmsSiteCreateCommand cmsSiteCreateCommand){
        return iCmsSiteApplicationService.create(cmsSiteCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:delete')")
    @Operation(summary = "删除站点")
    @DeleteMapping("/delete")
    @OpLog(name = "删除站点",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsSiteVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsSiteApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:update')")
    @Operation(summary = "更新站点")
    @PutMapping("/update")
    @OpLog(name = "更新站点",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsSiteVO> update(@RequestBody CmsSiteUpdateCommand cmsSiteUpdateCommand){
        cmsSiteUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsSiteApplicationService.update(cmsSiteUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:update')")
    @Operation(summary = "站点更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsSiteVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCmsSiteRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:detail')")
    @Operation(summary = "站点详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsSiteVO> queryDetail(CommonIdCommand detailCommand){
        return iCmsSiteRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:queryList')")
    @Operation(summary = "列表查询站点")
    @GetMapping("/list")
    public MultiResponse<CmsSiteVO> queryList(CmsSiteQueryListCommand cmsSiteQueryListCommand){
        cmsSiteQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsSiteRepresentationApplicationService.queryList(cmsSiteQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:pageQuery')")
    @Operation(summary = "分页查询站点")
    @GetMapping("/page")
    public PageResponse<CmsSiteVO> pageQueryList(CmsSitePageQueryCommand cmsSitePageQueryCommand){
        cmsSitePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsSiteRepresentationApplicationService.pageQuery(cmsSitePageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSite:public')")
    @Operation(summary = "发布站点")
    @PutMapping("/public")
    @OpLog(name = "发布站点",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsSiteVO> publish(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), true);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsSiteApplicationService.publish(commonPublicCommand);
    }
    @PreAuthorize("hasAuthority('admin:web:cmsSite:unPublic')")
    @Operation(summary = "取消发布站点")
    @PutMapping("/unPublic")
    @OpLog(name = "取消发布站点",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsSiteVO> unPublic(@RequestBody CommonIdCommand commonIdCommand){
        CommonPublicCommand commonPublicCommand = CommonPublicCommand.create(commonIdCommand.getId(), false);
        commonPublicCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsSiteApplicationService.publish(commonPublicCommand);
    }
    @Operation(summary = "站点首页地址")
    @GetMapping("/indexItemsUrl")
    public MultiResponse<CmsIndexItemUrlVO> indexItemsUrl(CommonIdCommand detailCommand){
        SingleResponse<CmsIndexUrlVO> cmsIndexUrlVOSingleResponse = indexUrl(detailCommand);
        CmsIndexUrlVO cmsIndexUrlVO = cmsIndexUrlVOSingleResponse.getData();

        List<CmsIndexItemUrlVO> cmsIndexItemUrlVOS = new ArrayList<>(2);
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamic(cmsIndexUrlVO.getDynamicIndexUrl()));
        cmsIndexItemUrlVOS.add(CmsIndexItemUrlVO.createDynamicPreview(cmsIndexUrlVO.getDynamicPreviewIndexUrl()));
        return MultiResponse.of(cmsIndexItemUrlVOS);
    }

    @Operation(summary = "站点首页地址")
    @GetMapping("/indexUrl")
    public SingleResponse<CmsIndexUrlVO> indexUrl(CommonIdCommand detailCommand){

        // 查询站点
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsSiteRepresentationApplicationService.queryDetail(detailCommand);
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();



        CmsTemplateModelContext publishCmsTemplateModelContext = CmsTemplateModelContext.create(true, CmsTemplateModelContext.Mode.publish);
        // 站点模板模型，动态发布模式
        CmsSiteTemplateModelVO publishCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, publishCmsTemplateModelContext);
        String dynamicIndexUrl = publishCmsSiteTemplateModelVO.getIndexUrl();


        CmsTemplateModelContext previewCmsTemplateModelContext = CmsTemplateModelContext.create(true,  CmsTemplateModelContext.Mode.preview);
        // 站点模板模型，动态预览模式
        CmsSiteTemplateModelVO previewCmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, previewCmsTemplateModelContext);
        String dynamicPreviewIndexUrl = previewCmsSiteTemplateModelVO.getIndexUrl();

        // 结果封装
        CmsIndexUrlVO cmsIndexUrlVO = new CmsIndexUrlVO();
        cmsIndexUrlVO.setDynamicIndexUrl(dynamicIndexUrl);
        cmsIndexUrlVO.setDynamicPreviewIndexUrl(dynamicPreviewIndexUrl);

        return SingleResponse.of(cmsIndexUrlVO);
    }
}
