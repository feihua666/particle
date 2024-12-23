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
import com.particle.navigation.client.api.INavigationSiteApplicationService;
import com.particle.navigation.client.api.INavigationSubmitApplicationService;
import com.particle.navigation.client.api.representation.INavigationSubmitRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationSubmitCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSubmitUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 导航提交后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Tag(name = "导航提交pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_submit")
public class NavigationSubmitAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationSubmitApplicationService iNavigationSubmitApplicationService;
    @Autowired
    private INavigationSubmitRepresentationApplicationService iNavigationSubmitRepresentationApplicationService;

    @Autowired
    private INavigationSiteApplicationService iNavigationSiteApplicationService;

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:create')")
    @Operation(summary = "添加导航提交")
    @PostMapping("/create")
    @OpLog(name = "添加导航提交",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationSubmitVO> create(@RequestBody NavigationSubmitCreateCommand navigationSubmitCreateCommand){
        return iNavigationSubmitApplicationService.create(navigationSubmitCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:delete')")
    @Operation(summary = "删除导航提交")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航提交",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationSubmitVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationSubmitApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:update')")
    @Operation(summary = "更新导航提交")
    @PutMapping("/update")
    @OpLog(name = "更新导航提交",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationSubmitVO> update(@RequestBody NavigationSubmitUpdateCommand navigationSubmitUpdateCommand){
        navigationSubmitUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationSubmitApplicationService.update(navigationSubmitUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:update')")
    @Operation(summary = "导航提交更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationSubmitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationSubmitRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:detail')")
    @Operation(summary = "导航提交详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationSubmitVO> queryDetail(IdCommand detailCommand){
        return iNavigationSubmitRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:queryList')")
    @Operation(summary = "列表查询导航提交")
    @GetMapping("/list")
    public MultiResponse<NavigationSubmitVO> queryList(NavigationSubmitQueryListCommand navigationSubmitQueryListCommand){
        navigationSubmitQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSubmitRepresentationApplicationService.queryList(navigationSubmitQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:pageQuery')")
    @Operation(summary = "分页查询导航提交")
    @GetMapping("/page")
    public PageResponse<NavigationSubmitVO> pageQueryList(NavigationSubmitPageQueryCommand navigationSubmitPageQueryCommand){
        navigationSubmitPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationSubmitRepresentationApplicationService.pageQuery(navigationSubmitPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationSubmit:sureSubmit')")
    @Operation(summary = "确认导航提交")
    @PostMapping("/sureSubmit")
    @OpLog(name = "确认导航提交",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public Response sureSubmit(@RequestBody IdCommand idCommand){
        return iNavigationSubmitApplicationService.sureSubmit(idCommand);
    }
}
