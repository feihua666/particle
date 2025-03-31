package com.particle.agi.client.agent.dto.data;

import com.particle.global.dto.basic.VO;
import com.particle.global.dto.basic.Value;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 主要是将函数调用的结果封装成dto
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatToolCallDTO} 的前端响应对象
 * </p>
 *
 * @author yangwei
 * @since 2025-02-24 14:18:23
 */
@Schema
@Data
public class AgiAgentChatToolCallVO extends VO {

    @Schema(description = "唯一标识符，用于区分不同的工具调用。")
    private String id;

    @Schema(description = "工具调用的类型，例如 API 调用、脚本执行等。")
    private String type;

    @Schema(description = "工具调用的名称，用于描述调用的功能或目标。")
    private String name;

    @Schema(description = "工具调用的参数，通常为 JSON 格式。")
    private String arguments;

    public static AgiAgentChatToolCallVO create(String id, String type, String name, String arguments) {
        AgiAgentChatToolCallVO aiToolCall = new AgiAgentChatToolCallVO();
        aiToolCall.id = id;
        aiToolCall.type = type;
        aiToolCall.name = name;
        aiToolCall.arguments = arguments;
        return aiToolCall;
    }
}
