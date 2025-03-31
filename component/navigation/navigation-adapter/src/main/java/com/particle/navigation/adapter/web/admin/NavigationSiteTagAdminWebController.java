package com.particle.navigation.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import com.particle.navigation.client.api.INavigationSiteTagApplicationService;
import com.particle.navigation.client.api.representation.INavigationSiteTagRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteTagCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 导航网站标签后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@OrderBy("seq")
@Tag(name = "导航网站标签pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_site_tag")
public class NavigationSiteTagAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationSiteTagApplicationService iNavigationSiteTagApplicationService;
    @Autowired
    private INavigationSiteTagRepresentationApplicationService iNavigationSiteTagRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:create')")
    @Operation(summary = "添加导航网站标签")
    @PostMapping("/create")
    @OpLog(name = "添加导航网站标签",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationSiteTagVO> create(@RequestBody NavigationSiteTagCreateCommand navigationSiteTagCreateCommand){
        return iNavigationSiteTagApplicationService.create(navigationSiteTagCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:delete')")
    @Operation(summary = "删除导航网站标签")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航网站标签",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationSiteTagVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationSiteTagApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:update')")
    @Operation(summary = "更新导航网站标签")
    @PutMapping("/update")
    @OpLog(name = "更新导航网站标签",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationSiteTagVO> update(@RequestBody NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand){
        navigationSiteTagUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationSiteTagApplicationService.update(navigationSiteTagUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:update')")
    @Operation(summary = "导航网站标签更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationSiteTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationSiteTagRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:detail')")
    @Operation(summary = "导航网站标签详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationSiteTagVO> queryDetail(IdCommand detailCommand){
        return iNavigationSiteTagRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:queryList')")
    @Operation(summary = "列表查询导航网站标签")
    @GetMapping("/list")
    public MultiResponse<NavigationSiteTagVO> queryList(NavigationSiteTagQueryListCommand navigationSiteTagQueryListCommand){
        navigationSiteTagQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteTagRepresentationApplicationService.queryList(navigationSiteTagQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTag:pageQuery')")
    @Operation(summary = "分页查询导航网站标签")
    @GetMapping("/page")
    public PageResponse<NavigationSiteTagVO> pageQueryList(NavigationSiteTagPageQueryCommand navigationSiteTagPageQueryCommand){
        navigationSiteTagPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteTagRepresentationApplicationService.pageQuery(navigationSiteTagPageQueryCommand);
    }
}
