package com.particle.openplatform.adapter.openapi.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiLimitRuleRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleUpdateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRulePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRuleQueryListCommand;
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
 * 开放平台开放接口限制规则后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Tag(name = "开放平台开放接口限制规则pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiLimitRuleApplicationService iOpenplatformOpenapiLimitRuleApplicationService;
    @Autowired
    private IOpenplatformOpenapiLimitRuleRepresentationApplicationService iOpenplatformOpenapiLimitRuleRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:create')")
    @Operation(summary = "添加开放平台开放接口限制规则")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台开放接口限制规则",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> create(@RequestBody OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand){
        return iOpenplatformOpenapiLimitRuleApplicationService.create(openplatformOpenapiLimitRuleCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:delete')")
    @Operation(summary = "删除开放平台开放接口限制规则")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台开放接口限制规则",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiLimitRuleApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:update')")
    @Operation(summary = "更新开放平台开放接口限制规则")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台开放接口限制规则",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> update(@RequestBody OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand){
        openplatformOpenapiLimitRuleUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiLimitRuleApplicationService.update(openplatformOpenapiLimitRuleUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:update')")
    @Operation(summary = "开放平台开放接口限制规则更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiLimitRuleRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:detail')")
    @Operation(summary = "开放平台开放接口限制规则详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiLimitRuleRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:queryList')")
    @Operation(summary = "列表查询开放平台开放接口限制规则")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiLimitRuleVO> queryList(OpenplatformOpenapiLimitRuleQueryListCommand openplatformOpenapiLimitRuleQueryListCommand){
        openplatformOpenapiLimitRuleQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiLimitRuleRepresentationApplicationService.queryList(openplatformOpenapiLimitRuleQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiLimitRule:pageQuery')")
    @Operation(summary = "分页查询开放平台开放接口限制规则")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiLimitRuleVO> pageQueryList(OpenplatformOpenapiLimitRulePageQueryCommand openplatformOpenapiLimitRulePageQueryCommand){
        openplatformOpenapiLimitRulePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiLimitRuleRepresentationApplicationService.pageQuery(openplatformOpenapiLimitRulePageQueryCommand);
    }
}