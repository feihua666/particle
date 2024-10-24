package com.particle.navigation.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.navigation.client.api.INavigationSiteApplicationService;
import com.particle.navigation.client.api.representation.INavigationSiteRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.navigation.client.dto.command.NavigationSiteUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSitePageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteQueryListCommand;
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
 * 导航网站后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Tag(name = "导航网站pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_site")
public class NavigationSiteAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationSiteApplicationService iNavigationSiteApplicationService;
    @Autowired
    private INavigationSiteRepresentationApplicationService iNavigationSiteRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationSite:create')")
    @Operation(summary = "添加导航网站")
    @PostMapping("/create")
    @OpLog(name = "添加导航网站",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationSiteVO> create(@RequestBody NavigationSiteCreateCommand navigationSiteCreateCommand){
        return iNavigationSiteApplicationService.create(navigationSiteCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:delete')")
    @Operation(summary = "删除导航网站")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航网站",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationSiteVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationSiteApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:update')")
    @Operation(summary = "更新导航网站")
    @PutMapping("/update")
    @OpLog(name = "更新导航网站",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationSiteVO> update(@RequestBody NavigationSiteUpdateCommand navigationSiteUpdateCommand){
        navigationSiteUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationSiteApplicationService.update(navigationSiteUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:update')")
    @Operation(summary = "导航网站更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationSiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationSiteRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:detail')")
    @Operation(summary = "导航网站详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationSiteVO> queryDetail(IdCommand detailCommand){
        return iNavigationSiteRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:queryList')")
    @Operation(summary = "列表查询导航网站")
    @GetMapping("/list")
    public MultiResponse<NavigationSiteVO> queryList(NavigationSiteQueryListCommand navigationSiteQueryListCommand){
        navigationSiteQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteRepresentationApplicationService.queryList(navigationSiteQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSite:pageQuery')")
    @Operation(summary = "分页查询导航网站")
    @GetMapping("/page")
    public PageResponse<NavigationSiteVO> pageQueryList(NavigationSitePageQueryCommand navigationSitePageQueryCommand){
        navigationSitePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteRepresentationApplicationService.pageQuery(navigationSitePageQueryCommand);
    }
}
