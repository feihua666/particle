package com.particle.agi.client.agent.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 智能体 对话指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-17 18:20:16
 */
@Data
@Schema
public class AgiAgentChatCommand extends AbstractBaseCommand {


    @Schema(description = "对话id,如果不指定将自动生成")
    private String chatId;

    @NotEmpty(message = "提示内容 不能为空")
    @Schema(description = "提示内容")
    private String message;

    @NotNull(message = "智能体id 不能为空")
    @Schema(description = "智能体id")
    private Long agiAgentId;
}
