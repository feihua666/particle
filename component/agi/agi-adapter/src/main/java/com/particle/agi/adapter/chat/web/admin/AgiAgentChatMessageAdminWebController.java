package com.particle.agi.adapter.chat.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageApplicationService;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageUpdateCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageQueryListCommand;
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
 * 智能体对话消息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Tag(name = "智能体对话消息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_agent_chat_message")
public class AgiAgentChatMessageAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAgentChatMessageApplicationService iAgiAgentChatMessageApplicationService;
    @Autowired
    private IAgiAgentChatMessageRepresentationApplicationService iAgiAgentChatMessageRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:create')")
    @Operation(summary = "添加智能体对话消息")
    @PostMapping("/create")
    @OpLog(name = "添加智能体对话消息",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAgentChatMessageVO> create(@RequestBody AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand){
        return iAgiAgentChatMessageApplicationService.create(agiAgentChatMessageCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:delete')")
    @Operation(summary = "删除智能体对话消息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除智能体对话消息",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAgentChatMessageVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAgentChatMessageApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:update')")
    @Operation(summary = "更新智能体对话消息")
    @PutMapping("/update")
    @OpLog(name = "更新智能体对话消息",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAgentChatMessageVO> update(@RequestBody AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand){
        agiAgentChatMessageUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAgentChatMessageApplicationService.update(agiAgentChatMessageUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:update')")
    @Operation(summary = "智能体对话消息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAgentChatMessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiAgentChatMessageRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:detail')")
    @Operation(summary = "智能体对话消息详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAgentChatMessageVO> queryDetail(IdCommand detailCommand){
        return iAgiAgentChatMessageRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:queryList')")
    @Operation(summary = "列表查询智能体对话消息")
    @GetMapping("/list")
    public MultiResponse<AgiAgentChatMessageVO> queryList(AgiAgentChatMessageQueryListCommand agiAgentChatMessageQueryListCommand){
        agiAgentChatMessageQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageRepresentationApplicationService.queryList(agiAgentChatMessageQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessage:pageQuery')")
    @Operation(summary = "分页查询智能体对话消息")
    @GetMapping("/page")
    public PageResponse<AgiAgentChatMessageVO> pageQueryList(AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand){
        agiAgentChatMessagePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageRepresentationApplicationService.pageQuery(agiAgentChatMessagePageQueryCommand);
    }
}
