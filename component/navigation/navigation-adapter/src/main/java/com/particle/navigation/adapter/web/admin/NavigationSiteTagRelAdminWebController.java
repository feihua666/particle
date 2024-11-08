package com.particle.navigation.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.navigation.client.api.INavigationSiteTagRelApplicationService;
import com.particle.navigation.client.api.representation.INavigationSiteTagRelRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelQueryListCommand;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationSiteTagCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagAssignNavigationSiteCommand;
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
 * 导航网站标签关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Tag(name = "导航网站标签关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_site_tag_rel")
public class NavigationSiteTagRelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationSiteTagRelApplicationService iNavigationSiteTagRelApplicationService;
    @Autowired
    private INavigationSiteTagRelRepresentationApplicationService iNavigationSiteTagRelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:create')")
    @Operation(summary = "添加导航网站标签关系")
    @PostMapping("/create")
    @OpLog(name = "添加导航网站标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationSiteTagRelVO> create(@RequestBody NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand){
        return iNavigationSiteTagRelApplicationService.create(navigationSiteTagRelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:delete')")
    @Operation(summary = "删除导航网站标签关系")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航网站标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationSiteTagRelVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationSiteTagRelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:update')")
    @Operation(summary = "更新导航网站标签关系")
    @PutMapping("/update")
    @OpLog(name = "更新导航网站标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationSiteTagRelVO> update(@RequestBody NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand){
        navigationSiteTagRelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationSiteTagRelApplicationService.update(navigationSiteTagRelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:update')")
    @Operation(summary = "导航网站标签关系更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationSiteTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationSiteTagRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:detail')")
    @Operation(summary = "导航网站标签关系详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationSiteTagRelVO> queryDetail(IdCommand detailCommand){
        return iNavigationSiteTagRelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:queryList')")
    @Operation(summary = "列表查询导航网站标签关系")
    @GetMapping("/list")
    public MultiResponse<NavigationSiteTagRelVO> queryList(NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand){
        navigationSiteTagRelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteTagRelRepresentationApplicationService.queryList(navigationSiteTagRelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:pageQuery')")
    @Operation(summary = "分页查询导航网站标签关系")
    @GetMapping("/page")
    public PageResponse<NavigationSiteTagRelVO> pageQueryList(NavigationSiteTagRelPageQueryCommand navigationSiteTagRelPageQueryCommand){
        navigationSiteTagRelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSiteTagRelRepresentationApplicationService.pageQuery(navigationSiteTagRelPageQueryCommand);
    }
    
    @Operation(summary = "网站分配网站标签")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:navigationSiteAssignNavigationSiteTag')")
    @PostMapping("/navigationSite/assign/navigationSiteTag")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "网站分配网站标签",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response navigationSiteAssignNavigationSiteTag(@RequestBody NavigationSiteAssignNavigationSiteTagCommand cf) {
        return iNavigationSiteTagRelApplicationService.navigationSiteAssignNavigationSiteTag(cf);
    }

    @Operation(summary = "根据网站ID查询已分配的网站标签id")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:queryNavigationSiteTagIdsByNavigationSiteId')")
    @GetMapping("/queryNavigationSiteTagIdsByNavigationSiteId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryNavigationSiteTagIdsByNavigationSiteId(IdCommand idCommand) {
        return iNavigationSiteTagRelRepresentationApplicationService.queryNavigationSiteTagIdsByNavigationSiteId( idCommand);
    }

    @Operation(summary = "清空网站下的所有网站标签")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:deleteByNavigationSiteId')")
    @DeleteMapping("/deleteByNavigationSiteId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空网站下的所有网站标签",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByNavigationSiteId(@RequestBody IdCommand idCommand) {
        return iNavigationSiteTagRelApplicationService.deleteByNavigationSiteId(idCommand);
    }


    @Operation(summary = "网站标签分配网站")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:navigationSiteTagAssignNavigationSite')")
    @PostMapping("/navigationSiteTag/assign/navigationSite")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "网站标签分配网站",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response navigationSiteTagAssignNavigationSite(@RequestBody NavigationSiteTagAssignNavigationSiteCommand cf) {
        return iNavigationSiteTagRelApplicationService.navigationSiteTagAssignNavigationSite(cf);
    }

    @Operation(summary = "根据网站标签ID查询已分配的网站id")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:queryNavigationSiteIdsByNavigationSiteTagId')")
    @GetMapping("/queryNavigationSiteIdsByNavigationSiteTagId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryByNavigationSiteTagId(IdCommand idCommand) {
        return iNavigationSiteTagRelRepresentationApplicationService.queryNavigationSiteIdsByNavigationSiteTagId( idCommand);

    }

    @Operation(summary = "清空网站标签下的所有网站")
    @PreAuthorize("hasAuthority('admin:web:navigationSiteTagRel:deleteByNavigationSiteTagId')")
    @DeleteMapping("/deleteByNavigationSiteTagId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空网站标签下的所有网站",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByNavigationSiteTagId(@RequestBody IdCommand idCommand) {
        return iNavigationSiteTagRelApplicationService.deleteByNavigationSiteTagId(idCommand);
    }

}