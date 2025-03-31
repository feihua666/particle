package com.particle.agi.adapter.chat.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageToolcallApplicationService;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageToolcallRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallUpdateCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallQueryListCommand;
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
 * 智能体对话消息工具调用后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Tag(name = "智能体对话消息工具调用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_agent_chat_message_toolcall")
public class AgiAgentChatMessageToolcallAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAgentChatMessageToolcallApplicationService iAgiAgentChatMessageToolcallApplicationService;
    @Autowired
    private IAgiAgentChatMessageToolcallRepresentationApplicationService iAgiAgentChatMessageToolcallRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:create')")
    @Operation(summary = "添加智能体对话消息工具调用")
    @PostMapping("/create")
    @OpLog(name = "添加智能体对话消息工具调用",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAgentChatMessageToolcallVO> create(@RequestBody AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand){
        return iAgiAgentChatMessageToolcallApplicationService.create(agiAgentChatMessageToolcallCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:delete')")
    @Operation(summary = "删除智能体对话消息工具调用")
    @DeleteMapping("/delete")
    @OpLog(name = "删除智能体对话消息工具调用",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAgentChatMessageToolcallVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAgentChatMessageToolcallApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:update')")
    @Operation(summary = "更新智能体对话消息工具调用")
    @PutMapping("/update")
    @OpLog(name = "更新智能体对话消息工具调用",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAgentChatMessageToolcallVO> update(@RequestBody AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand){
        agiAgentChatMessageToolcallUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAgentChatMessageToolcallApplicationService.update(agiAgentChatMessageToolcallUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:update')")
    @Operation(summary = "智能体对话消息工具调用更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAgentChatMessageToolcallVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiAgentChatMessageToolcallRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:detail')")
    @Operation(summary = "智能体对话消息工具调用详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAgentChatMessageToolcallVO> queryDetail(IdCommand detailCommand){
        return iAgiAgentChatMessageToolcallRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:queryList')")
    @Operation(summary = "列表查询智能体对话消息工具调用")
    @GetMapping("/list")
    public MultiResponse<AgiAgentChatMessageToolcallVO> queryList(AgiAgentChatMessageToolcallQueryListCommand agiAgentChatMessageToolcallQueryListCommand){
        agiAgentChatMessageToolcallQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageToolcallRepresentationApplicationService.queryList(agiAgentChatMessageToolcallQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageToolcall:pageQuery')")
    @Operation(summary = "分页查询智能体对话消息工具调用")
    @GetMapping("/page")
    public PageResponse<AgiAgentChatMessageToolcallVO> pageQueryList(AgiAgentChatMessageToolcallPageQueryCommand agiAgentChatMessageToolcallPageQueryCommand){
        agiAgentChatMessageToolcallPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageToolcallRepresentationApplicationService.pageQuery(agiAgentChatMessageToolcallPageQueryCommand);
    }
}
