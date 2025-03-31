package com.particle.agi.adapter.chat.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageMediaApplicationService;
import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageMediaRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaUpdateCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaQueryListCommand;
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
 * 智能体对话消息媒体后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Tag(name = "智能体对话消息媒体pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_agent_chat_message_media")
public class AgiAgentChatMessageMediaAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiAgentChatMessageMediaApplicationService iAgiAgentChatMessageMediaApplicationService;
    @Autowired
    private IAgiAgentChatMessageMediaRepresentationApplicationService iAgiAgentChatMessageMediaRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:create')")
    @Operation(summary = "添加智能体对话消息媒体")
    @PostMapping("/create")
    @OpLog(name = "添加智能体对话消息媒体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiAgentChatMessageMediaVO> create(@RequestBody AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand){
        return iAgiAgentChatMessageMediaApplicationService.create(agiAgentChatMessageMediaCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:delete')")
    @Operation(summary = "删除智能体对话消息媒体")
    @DeleteMapping("/delete")
    @OpLog(name = "删除智能体对话消息媒体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiAgentChatMessageMediaVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiAgentChatMessageMediaApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:update')")
    @Operation(summary = "更新智能体对话消息媒体")
    @PutMapping("/update")
    @OpLog(name = "更新智能体对话消息媒体",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiAgentChatMessageMediaVO> update(@RequestBody AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand){
        agiAgentChatMessageMediaUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiAgentChatMessageMediaApplicationService.update(agiAgentChatMessageMediaUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:update')")
    @Operation(summary = "智能体对话消息媒体更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiAgentChatMessageMediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiAgentChatMessageMediaRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:detail')")
    @Operation(summary = "智能体对话消息媒体详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiAgentChatMessageMediaVO> queryDetail(IdCommand detailCommand){
        return iAgiAgentChatMessageMediaRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:queryList')")
    @Operation(summary = "列表查询智能体对话消息媒体")
    @GetMapping("/list")
    public MultiResponse<AgiAgentChatMessageMediaVO> queryList(AgiAgentChatMessageMediaQueryListCommand agiAgentChatMessageMediaQueryListCommand){
        agiAgentChatMessageMediaQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageMediaRepresentationApplicationService.queryList(agiAgentChatMessageMediaQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiAgentChatMessageMedia:pageQuery')")
    @Operation(summary = "分页查询智能体对话消息媒体")
    @GetMapping("/page")
    public PageResponse<AgiAgentChatMessageMediaVO> pageQueryList(AgiAgentChatMessageMediaPageQueryCommand agiAgentChatMessageMediaPageQueryCommand){
        agiAgentChatMessageMediaPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiAgentChatMessageMediaRepresentationApplicationService.pageQuery(agiAgentChatMessageMediaPageQueryCommand);
    }
}
