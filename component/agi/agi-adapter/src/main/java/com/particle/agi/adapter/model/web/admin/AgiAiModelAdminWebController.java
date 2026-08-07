package com.particle.agi.adapter.model.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.model.api.IAgiAiModelApplicationService;
import com.particle.agi.client.model.api.representation.IAgiAiModelRepresentationApplicationService;
import com.particle.agi.client.model.dto.command.AgiAiModelCreateCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.agi.client.model.dto.command.AgiAiModelUpdateCommand;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelQueryListCommand;
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
 * AI模型后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Tag(name = "AI模型pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_ai_model")
public class AgiAiModelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAiModelApplicationService iAgiAiModelApplicationService;
    @Autowired
    private IAgiAiModelRepresentationApplicationService iAgiAiModelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:create')")
    @Operation(summary = "添加AI模型")
    @PostMapping("/create")
    @OpLog(name = "添加AI模型",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAiModelVO> create(@RequestBody AgiAiModelCreateCommand agiAiModelCreateCommand){
        return iAgiAiModelApplicationService.create(agiAiModelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:delete')")
    @Operation(summary = "删除AI模型")
    @DeleteMapping("/delete")
    @OpLog(name = "删除AI模型",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAiModelVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAiModelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:update')")
    @Operation(summary = "更新AI模型")
    @PutMapping("/update")
    @OpLog(name = "更新AI模型",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAiModelVO> update(@RequestBody AgiAiModelUpdateCommand agiAiModelUpdateCommand){
        agiAiModelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAiModelApplicationService.update(agiAiModelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:update')")
    @Operation(summary = "AI模型更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAiModelVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAgiAiModelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:detail')")
    @Operation(summary = "AI模型详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAiModelVO> queryDetail(CommonIdCommand detailCommand){
        return iAgiAiModelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:queryList')")
    @Operation(summary = "列表查询AI模型")
    @GetMapping("/list")
    public MultiResponse<AgiAiModelVO> queryList(AgiAiModelQueryListCommand agiAiModelQueryListCommand){
        agiAiModelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAiModelRepresentationApplicationService.queryList(agiAiModelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAiModel:pageQuery')")
    @Operation(summary = "分页查询AI模型")
    @GetMapping("/page")
    public PageResponse<AgiAiModelVO> pageQueryList(AgiAiModelPageQueryCommand agiAiModelPageQueryCommand){
        agiAiModelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAiModelRepresentationApplicationService.pageQuery(agiAiModelPageQueryCommand);
    }
}
