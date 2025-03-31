package com.particle.agi.domain.agent.value;

import com.particle.agi.domain.enums.AiMessageType;
import com.particle.agi.domain.values.AgiMedia;
import com.particle.global.dto.basic.VO;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智能体对话消息基类
 * 参考{@link org.springframework.ai.chat.messages.AbstractMessage}
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public abstract class AgiAgentChatBaseMessageDTO extends VO {

    /**
     * 消息类型
     * 参考{@link AiMessageType} 的name()
     */
    private String messageType;
    /**
     * 消息文本
     */
    private String text;

    /**
     * 元数据信息
     */
    private Map<String, Object> metadata;
}
