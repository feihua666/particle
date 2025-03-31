package com.particle.agi.client.chat.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 智能体对话消息媒体 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Data
@Schema
public class AgiAgentChatMessageMediaUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "智能体对话消息id 不能为空")
        @Schema(description = "智能体对话消息id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long agiAgentChatMessageId;


    @NotEmpty(message = "唯一id 不能为空")
        @Schema(description = "唯一id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uniqueId;


    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "媒体类型 不能为空")
        @Schema(description = "媒体类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String mimeType;


    @NotEmpty(message = "地址 不能为空")
        @Schema(description = "地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;


    @Schema(description = "描述")
    private String remark;









}
