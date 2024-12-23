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
import com.particle.navigation.client.api.INavigationCategoryApplicationService;
import com.particle.navigation.client.api.representation.INavigationCategoryRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationCategoryCreateCommand;
import com.particle.navigation.client.dto.command.NavigationCategoryUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 导航分类后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Tag(name = "导航分类pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_category")
public class NavigationCategoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationCategoryApplicationService iNavigationCategoryApplicationService;
    @Autowired
    private INavigationCategoryRepresentationApplicationService iNavigationCategoryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:create')")
    @Operation(summary = "添加导航分类")
    @PostMapping("/create")
    @OpLog(name = "添加导航分类",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationCategoryVO> create(@RequestBody NavigationCategoryCreateCommand navigationCategoryCreateCommand){
        return iNavigationCategoryApplicationService.create(navigationCategoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:delete')")
    @Operation(summary = "删除导航分类")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航分类",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationCategoryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationCategoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:update')")
    @Operation(summary = "更新导航分类")
    @PutMapping("/update")
    @OpLog(name = "更新导航分类",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationCategoryVO> update(@RequestBody NavigationCategoryUpdateCommand navigationCategoryUpdateCommand){
        navigationCategoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationCategoryApplicationService.update(navigationCategoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:update')")
    @Operation(summary = "导航分类更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationCategoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:detail')")
    @Operation(summary = "导航分类详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationCategoryVO> queryDetail(IdCommand detailCommand){
        return iNavigationCategoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:queryList')")
    @Operation(summary = "列表查询导航分类")
    @GetMapping("/list")
    public MultiResponse<NavigationCategoryVO> queryList(NavigationCategoryQueryListCommand navigationCategoryQueryListCommand){
        navigationCategoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationCategoryRepresentationApplicationService.queryList(navigationCategoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationCategory:pageQuery')")
    @Operation(summary = "分页查询导航分类")
    @GetMapping("/page")
    public PageResponse<NavigationCategoryVO> pageQueryList(NavigationCategoryPageQueryCommand navigationCategoryPageQueryCommand){
        navigationCategoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationCategoryRepresentationApplicationService.pageQuery(navigationCategoryPageQueryCommand);
    }
}
