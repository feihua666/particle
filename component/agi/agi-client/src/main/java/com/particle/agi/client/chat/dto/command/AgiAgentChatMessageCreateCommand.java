package com.particle.agi.client.chat.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 智能体对话消息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Data
@Schema
public class AgiAgentChatMessageCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "智能体对话id 不能为空")
        @Schema(description = "智能体对话id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long agiAgentChatId;


    @Schema(description = "智能体id")
    private Long agiAgentId;


    @NotEmpty(message = "对话id 不能为空")
        @Schema(description = "对话id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String chatId;


    @Schema(description = "用户id")
    private Long userId;


    @NotEmpty(message = "消息类型 不能为空")
        @Schema(description = "消息类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String messageType;


    @Schema(description = "消息内容")
    private String content;

	@Schema(description = "元数据信息json")
	private String metadataJson;


    @Schema(description = "描述")
    private String remark;









}