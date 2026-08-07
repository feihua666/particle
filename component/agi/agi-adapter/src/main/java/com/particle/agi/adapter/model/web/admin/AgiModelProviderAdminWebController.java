package com.particle.agi.adapter.model.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.model.api.IAgiModelProviderApplicationService;
import com.particle.agi.client.model.api.representation.IAgiModelProviderRepresentationApplicationService;
import com.particle.agi.client.model.dto.command.AgiModelProviderCreateCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.agi.client.model.dto.command.AgiModelProviderUpdateCommand;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderQueryListCommand;
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
 * AI模型提供商后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Tag(name = "AI模型提供商pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_model_provider")
public class AgiModelProviderAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiModelProviderApplicationService iAgiModelProviderApplicationService;
    @Autowired
    private IAgiModelProviderRepresentationApplicationService iAgiModelProviderRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:create')")
    @Operation(summary = "添加AI模型提供商")
    @PostMapping("/create")
    @OpLog(name = "添加AI模型提供商",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiModelProviderVO> create(@RequestBody AgiModelProviderCreateCommand agiModelProviderCreateCommand){
        return iAgiModelProviderApplicationService.create(agiModelProviderCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:delete')")
    @Operation(summary = "删除AI模型提供商")
    @DeleteMapping("/delete")
    @OpLog(name = "删除AI模型提供商",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiModelProviderVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiModelProviderApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:update')")
    @Operation(summary = "更新AI模型提供商")
    @PutMapping("/update")
    @OpLog(name = "更新AI模型提供商",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiModelProviderVO> update(@RequestBody AgiModelProviderUpdateCommand agiModelProviderUpdateCommand){
        agiModelProviderUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiModelProviderApplicationService.update(agiModelProviderUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:update')")
    @Operation(summary = "AI模型提供商更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiModelProviderVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAgiModelProviderRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:detail')")
    @Operation(summary = "AI模型提供商详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiModelProviderVO> queryDetail(CommonIdCommand detailCommand){
        return iAgiModelProviderRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:queryList')")
    @Operation(summary = "列表查询AI模型提供商")
    @GetMapping("/list")
    public MultiResponse<AgiModelProviderVO> queryList(AgiModelProviderQueryListCommand agiModelProviderQueryListCommand){
        agiModelProviderQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiModelProviderRepresentationApplicationService.queryList(agiModelProviderQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiModelProvider:pageQuery')")
    @Operation(summary = "分页查询AI模型提供商")
    @GetMapping("/page")
    public PageResponse<AgiModelProviderVO> pageQueryList(AgiModelProviderPageQueryCommand agiModelProviderPageQueryCommand){
        agiModelProviderPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiModelProviderRepresentationApplicationService.pageQuery(agiModelProviderPageQueryCommand);
    }
}
