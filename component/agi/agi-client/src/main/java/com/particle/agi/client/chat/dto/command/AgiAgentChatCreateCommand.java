package com.particle.agi.client.chat.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

/**
 * <p>
 * 智能体对话 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Data
@Schema
public class AgiAgentChatCreateCommand extends AbstractBaseCommand {



    @Schema(description = "智能体id")
    private Long agiAgentId;


    @NotEmpty(message = "对话id 不能为空")
        @Schema(description = "对话id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String chatId;


    @Schema(description = "用户id")
    private Long userId;


    @NotEmpty(message = "对话标题 不能为空")
        @Schema(description = "对话标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @Schema(description = "对话标题说明")
    private String titleMemo;


    @Schema(description = "描述")
    private String remark;









}
