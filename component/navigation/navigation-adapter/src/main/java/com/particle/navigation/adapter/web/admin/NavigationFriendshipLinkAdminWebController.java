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
import com.particle.navigation.client.api.INavigationFriendshipLinkApplicationService;
import com.particle.navigation.client.api.representation.INavigationFriendshipLinkRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkCreateCommand;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 导航友情链接后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Tag(name = "导航友情链接pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_friendship_link")
public class NavigationFriendshipLinkAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationFriendshipLinkApplicationService iNavigationFriendshipLinkApplicationService;
    @Autowired
    private INavigationFriendshipLinkRepresentationApplicationService iNavigationFriendshipLinkRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:create')")
    @Operation(summary = "添加导航友情链接")
    @PostMapping("/create")
    @OpLog(name = "添加导航友情链接",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationFriendshipLinkVO> create(@RequestBody NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand){
        return iNavigationFriendshipLinkApplicationService.create(navigationFriendshipLinkCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:delete')")
    @Operation(summary = "删除导航友情链接")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航友情链接",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationFriendshipLinkVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationFriendshipLinkApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:update')")
    @Operation(summary = "更新导航友情链接")
    @PutMapping("/update")
    @OpLog(name = "更新导航友情链接",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationFriendshipLinkVO> update(@RequestBody NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand){
        navigationFriendshipLinkUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationFriendshipLinkApplicationService.update(navigationFriendshipLinkUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:update')")
    @Operation(summary = "导航友情链接更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationFriendshipLinkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationFriendshipLinkRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:detail')")
    @Operation(summary = "导航友情链接详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationFriendshipLinkVO> queryDetail(IdCommand detailCommand){
        return iNavigationFriendshipLinkRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:queryList')")
    @Operation(summary = "列表查询导航友情链接")
    @GetMapping("/list")
    public MultiResponse<NavigationFriendshipLinkVO> queryList(NavigationFriendshipLinkQueryListCommand navigationFriendshipLinkQueryListCommand){
        navigationFriendshipLinkQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationFriendshipLinkRepresentationApplicationService.queryList(navigationFriendshipLinkQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationFriendshipLink:pageQuery')")
    @Operation(summary = "分页查询导航友情链接")
    @GetMapping("/page")
    public PageResponse<NavigationFriendshipLinkVO> pageQueryList(NavigationFriendshipLinkPageQueryCommand navigationFriendshipLinkPageQueryCommand){
        navigationFriendshipLinkPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationFriendshipLinkRepresentationApplicationService.pageQuery(navigationFriendshipLinkPageQueryCommand);
    }
}
