package com.particle.agi.adapter.agent.web.admin;

import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import com.particle.agi.client.agent.api.representation.IAgiAgentRepresentationApplicationService;
import com.particle.agi.client.agent.dto.command.AgiAgentCreateCommand;
import com.particle.agi.client.agent.dto.command.AgiAgentUpdateCommand;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentPageQueryCommand;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentQueryListCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 智能体后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Tag(name = "智能体pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_agent")
public class AgiAgentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAgentApplicationService iAgiAgentApplicationService;
    @Autowired
    private IAgiAgentRepresentationApplicationService iAgiAgentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAgent:create')")
    @Operation(summary = "添加智能体")
    @PostMapping("/create")
    @OpLog(name = "添加智能体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAgentVO> create(@RequestBody AgiAgentCreateCommand agiAgentCreateCommand){
        return iAgiAgentApplicationService.create(agiAgentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:delete')")
    @Operation(summary = "删除智能体")
    @DeleteMapping("/delete")
    @OpLog(name = "删除智能体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAgentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAgentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:update')")
    @Operation(summary = "更新智能体")
    @PutMapping("/update")
    @OpLog(name = "更新智能体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAgentVO> update(@RequestBody AgiAgentUpdateCommand agiAgentUpdateCommand){
        agiAgentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAgentApplicationService.update(agiAgentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:update')")
    @Operation(summary = "智能体更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAgentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiAgentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:detail')")
    @Operation(summary = "智能体详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAgentVO> queryDetail(IdCommand detailCommand){
        return iAgiAgentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:queryList')")
    @Operation(summary = "列表查询智能体")
    @GetMapping("/list")
    public MultiResponse<AgiAgentVO> queryList(AgiAgentQueryListCommand agiAgentQueryListCommand){
        agiAgentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentRepresentationApplicationService.queryList(agiAgentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgent:pageQuery')")
    @Operation(summary = "分页查询智能体")
    @GetMapping("/page")
    public PageResponse<AgiAgentVO> pageQueryList(AgiAgentPageQueryCommand agiAgentPageQueryCommand){
        agiAgentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentRepresentationApplicationService.pageQuery(agiAgentPageQueryCommand);
    }

}
