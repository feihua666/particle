package com.particle.agi.adapter.chat.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageToolApplicationService;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageToolRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolUpdateCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolQueryListCommand;
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
 * 智能体对话消息工具后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Tag(name = "智能体对话消息工具pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_agent_chat_message_tool")
public class AgiAgentChatMessageToolAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAgentChatMessageToolApplicationService iAgiAgentChatMessageToolApplicationService;
    @Autowired
    private IAgiAgentChatMessageToolRepresentationApplicationService iAgiAgentChatMessageToolRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:create')")
    @Operation(summary = "添加智能体对话消息工具")
    @PostMapping("/create")
    @OpLog(name = "添加智能体对话消息工具",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAgentChatMessageToolVO> create(@RequestBody AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand){
        return iAgiAgentChatMessageToolApplicationService.create(agiAgentChatMessageToolCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:delete')")
    @Operation(summary = "删除智能体对话消息工具")
    @DeleteMapping("/delete")
    @OpLog(name = "删除智能体对话消息工具",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAgentChatMessageToolVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAgentChatMessageToolApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:update')")
    @Operation(summary = "更新智能体对话消息工具")
    @PutMapping("/update")
    @OpLog(name = "更新智能体对话消息工具",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAgentChatMessageToolVO> update(@RequestBody AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand){
        agiAgentChatMessageToolUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAgentChatMessageToolApplicationService.update(agiAgentChatMessageToolUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:update')")
    @Operation(summary = "智能体对话消息工具更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAgentChatMessageToolVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiAgentChatMessageToolRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:detail')")
    @Operation(summary = "智能体对话消息工具详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAgentChatMessageToolVO> queryDetail(IdCommand detailCommand){
        return iAgiAgentChatMessageToolRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:queryList')")
    @Operation(summary = "列表查询智能体对话消息工具")
    @GetMapping("/list")
    public MultiResponse<AgiAgentChatMessageToolVO> queryList(AgiAgentChatMessageToolQueryListCommand agiAgentChatMessageToolQueryListCommand){
        agiAgentChatMessageToolQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageToolRepresentationApplicationService.queryList(agiAgentChatMessageToolQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageTool:pageQuery')")
    @Operation(summary = "分页查询智能体对话消息工具")
    @GetMapping("/page")
    public PageResponse<AgiAgentChatMessageToolVO> pageQueryList(AgiAgentChatMessageToolPageQueryCommand agiAgentChatMessageToolPageQueryCommand){
        agiAgentChatMessageToolPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageToolRepresentationApplicationService.pageQuery(agiAgentChatMessageToolPageQueryCommand);
    }
}
