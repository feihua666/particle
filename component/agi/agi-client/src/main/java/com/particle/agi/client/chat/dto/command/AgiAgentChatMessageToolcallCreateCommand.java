package com.particle.agi.client.chat.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 智能体对话消息工具调用 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Data
@Schema
public class AgiAgentChatMessageToolcallCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "智能体对话消息id 不能为空")
        @Schema(description = "智能体对话消息id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long agiAgentChatMessageId;


    @NotEmpty(message = "唯一id 不能为空")
        @Schema(description = "唯一id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uniqueId;


    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "类型 不能为空")
        @Schema(description = "类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;


    @Schema(description = "参数")
    private String arguments;


    @Schema(description = "描述")
    private String remark;









}
