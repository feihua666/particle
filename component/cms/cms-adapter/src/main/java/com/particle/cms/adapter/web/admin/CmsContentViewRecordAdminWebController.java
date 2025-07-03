package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsContentViewRecordApplicationService;
import com.particle.cms.client.api.representation.ICmsContentViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsContentViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentViewRecordUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordQueryListCommand;
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
 * 内容访问记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Tag(name = "内容访问记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_content_view_record")
public class CmsContentViewRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsContentViewRecordApplicationService iCmsContentViewRecordApplicationService;
    @Autowired
    private ICmsContentViewRecordRepresentationApplicationService iCmsContentViewRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:create')")
    @Operation(summary = "添加内容访问记录")
    @PostMapping("/create")
    @OpLog(name = "添加内容访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsContentViewRecordVO> create(@RequestBody CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand){
        return iCmsContentViewRecordApplicationService.create(cmsContentViewRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:delete')")
    @Operation(summary = "删除内容访问记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除内容访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsContentViewRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsContentViewRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:update')")
    @Operation(summary = "更新内容访问记录")
    @PutMapping("/update")
    @OpLog(name = "更新内容访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsContentViewRecordVO> update(@RequestBody CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand){
        cmsContentViewRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsContentViewRecordApplicationService.update(cmsContentViewRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:update')")
    @Operation(summary = "内容访问记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsContentViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsContentViewRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:detail')")
    @Operation(summary = "内容访问记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsContentViewRecordVO> queryDetail(IdCommand detailCommand){
        return iCmsContentViewRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:queryList')")
    @Operation(summary = "列表查询内容访问记录")
    @GetMapping("/list")
    public MultiResponse<CmsContentViewRecordVO> queryList(CmsContentViewRecordQueryListCommand cmsContentViewRecordQueryListCommand){
        cmsContentViewRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentViewRecordRepresentationApplicationService.queryList(cmsContentViewRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsContentViewRecord:pageQuery')")
    @Operation(summary = "分页查询内容访问记录")
    @GetMapping("/page")
    public PageResponse<CmsContentViewRecordVO> pageQueryList(CmsContentViewRecordPageQueryCommand cmsContentViewRecordPageQueryCommand){
        cmsContentViewRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsContentViewRecordRepresentationApplicationService.pageQuery(cmsContentViewRecordPageQueryCommand);
    }
}
