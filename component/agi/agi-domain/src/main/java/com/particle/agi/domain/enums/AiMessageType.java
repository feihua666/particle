package com.particle.agi.domain.enums;

/**
 * <p>
 * 参考：{@link org.springframework.ai.chat.messages.MessageType} 方便引用
 * 需要保证该枚举的name和{@link org.springframework.ai.chat.messages.MessageType} value 保持一致
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 17:26
 */
public enum AiMessageType {

    user,

    assistant,

    system,

    tool;
}
