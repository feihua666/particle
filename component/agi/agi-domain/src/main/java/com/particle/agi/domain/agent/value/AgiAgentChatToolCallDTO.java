package com.particle.agi.domain.agent.value;

import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * 主要是将函数调用的结果封装成dto
 * 参考{@link org.springframework.ai.chat.messages.AssistantMessage.ToolCall}
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 17:32
 */
@Data
public class AgiAgentChatToolCallDTO extends Value {
    /**
     * 唯一标识符，用于区分不同的工具调用。
     */
    private String id;

    /**
     * 工具调用的类型，例如 API 调用、脚本执行等。
     */
    private String type;

    /**
     * 工具调用的名称，用于描述调用的功能或目标。
     */
    private String name;

    /**
     * 工具调用的参数，通常为 JSON 格式。
     */
    private String arguments;

    public static AgiAgentChatToolCallDTO create(String id, String type, String name, String arguments) {
        AgiAgentChatToolCallDTO aiToolCall = new AgiAgentChatToolCallDTO();
        aiToolCall.id = id;
        aiToolCall.type = type;
        aiToolCall.name = name;
        aiToolCall.arguments = arguments;
        return aiToolCall;
    }
}
