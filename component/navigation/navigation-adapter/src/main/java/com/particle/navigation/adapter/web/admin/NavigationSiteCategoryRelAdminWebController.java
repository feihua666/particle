package com.particle.navigation.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.api.INavigationSiteCategoryRelApplicationService;
import com.particle.navigation.client.api.representation.INavigationSiteCategoryRelRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationCategoryAssignNavigationSiteCommand;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationCategoryCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 导航网站分类关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Tag(name = "导航网站分类关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_site_category_rel")
public class NavigationSiteCategoryRelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationSiteCategoryRelApplicationService iNavigationSiteCategoryRelApplicationService;
    @Autowired
    private INavigationSiteCategoryRelRepresentationApplicationService iNavigationSiteCategoryRelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:create')")
    @Operation(summary = "添加导航网站分类关系")
    @PostMapping("/create")
    @OpLog(name = "添加导航网站分类关系",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationSiteCategoryRelVO> create(@RequestBody NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand){
        return iNavigationSiteCategoryRelApplicationService.create(navigationSiteCategoryRelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:delete')")
    @Operation(summary = "删除导航网站分类关系")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航网站分类关系",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationSiteCategoryRelVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationSiteCategoryRelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:update')")
    @Operation(summary = "更新导航网站分类关系")
    @PutMapping("/update")
    @OpLog(name = "更新导航网站分类关系",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationSiteCategoryRelVO> update(@RequestBody NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand){
        navigationSiteCategoryRelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationSiteCategoryRelApplicationService.update(navigationSiteCategoryRelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:update')")
    @Operation(summary = "导航网站分类关系更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationSiteCategoryRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationSiteCategoryRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:detail')")
    @Operation(summary = "导航网站分类关系详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationSiteCategoryRelVO> queryDetail(IdCommand detailCommand){
        return iNavigationSiteCategoryRelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:queryList')")
    @Operation(summary = "列表查询导航网站分类关系")
    @GetMapping("/list")
    public MultiResponse<NavigationSiteCategoryRelVO> queryList(NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand){
        navigationSiteCategoryRelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteCategoryRelRepresentationApplicationService.queryList(navigationSiteCategoryRelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:pageQuery')")
    @Operation(summary = "分页查询导航网站分类关系")
    @GetMapping("/page")
    public PageResponse<NavigationSiteCategoryRelVO> pageQueryList(NavigationSiteCategoryRelPageQueryCommand navigationSiteCategoryRelPageQueryCommand){
        navigationSiteCategoryRelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteCategoryRelRepresentationApplicationService.pageQuery(navigationSiteCategoryRelPageQueryCommand);
    }

    @Operation(summary = "导航网站分配导航分类")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory')")
    @PostMapping("/navigationSite/assign/navigationCategory")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "导航网站分配导航分类",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.relAsign)
    public Response navigationSiteAssignNavigationCategory(@RequestBody NavigationSiteAssignNavigationCategoryCommand cf) {
        return iNavigationSiteCategoryRelApplicationService.navigationSiteAssignNavigationCategory(cf);
    }

    @Operation(summary = "根据导航网站ID查询已分配的导航分类id")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:queryNavigationCategoryIdsByNavigationSiteId')")
    @GetMapping("/queryNavigationCategoryIdsByNavigationSiteId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryNavigationCategoryIdsByNavigationSiteId(IdCommand idCommand) {
        return iNavigationSiteCategoryRelRepresentationApplicationService.queryNavigationCategoryIdsByNavigationSiteId( idCommand);
    }

    @Operation(summary = "清空导航网站下的所有导航分类")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId')")
    @DeleteMapping("/deleteByNavigationSiteId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空导航网站下的所有导航分类",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public Response deleteByNavigationSiteId(@RequestBody IdCommand idCommand) {
        return iNavigationSiteCategoryRelApplicationService.deleteByNavigationSiteId(idCommand);
    }


    @Operation(summary = "导航分类分配导航网站")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite')")
    @PostMapping("/navigationCategory/assign/navigationSite")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "导航分类分配导航网站",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.relAsign)
    public Response navigationCategoryAssignNavigationSite(@RequestBody NavigationCategoryAssignNavigationSiteCommand cf) {
        return iNavigationSiteCategoryRelApplicationService.navigationCategoryAssignNavigationSite(cf);
    }

    @Operation(summary = "根据导航分类ID查询已分配的导航网站id")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:queryNavigationSiteIdsByNavigationCategoryId')")
    @GetMapping("/queryNavigationSiteIdsByNavigationCategoryId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryByNavigationCategoryId(IdCommand idCommand) {
        return iNavigationSiteCategoryRelRepresentationApplicationService.queryNavigationSiteIdsByNavigationCategoryId( idCommand);

    }

    @Operation(summary = "清空导航分类下的所有导航网站")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId')")
    @DeleteMapping("/deleteByNavigationCategoryId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空导航分类下的所有导航网站",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public Response deleteByNavigationCategoryId(@RequestBody IdCommand idCommand) {
        return iNavigationSiteCategoryRelApplicationService.deleteByNavigationCategoryId(idCommand);
    }

}
