package com.particle.global.ai.endpoint.command;

import com.particle.global.dto.basic.QueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 对话聊天指令
 * </p>
 *
 * @author yangwei
 * @since 2025-01-06 17:15:56
 */
@Data
@Schema
public class ChatCommand extends QueryCommand {

	@NotEmpty(message = "提示内容不能为空")
	@Schema(description = "提示内容")
	private String message;

}
