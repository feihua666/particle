package com.particle.agi.domain.agent.value;

import cn.hutool.json.JSONUtil;
import com.particle.agi.domain.enums.AiMessageType;
import com.particle.common.domain.ValueObjRoot;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 预设对话
 * </p>
 *
 * @author yangwei
 * @since 2025/2/20 13:02
 */
@Data
public class AgiAgentPresetDialogue extends ValueObjRoot {

    private List<AgiAgentPresetDialogueMessage> messages;

    /**
     * 添加消息
     * @param agiAgentPresetDialogueMessage
     */
    public void addMessage(AgiAgentPresetDialogueMessage agiAgentPresetDialogueMessage) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(agiAgentPresetDialogueMessage);
    }
    public static AgiAgentPresetDialogue create(List<AgiAgentPresetDialogueMessage> messages) {
        AgiAgentPresetDialogue agiAgentPresetDialogue = new AgiAgentPresetDialogue();
        agiAgentPresetDialogue.setMessages(messages);
        return agiAgentPresetDialogue;
    }

    /**
     * 根据json字符串创建
     * @param jsonStr
     * @return
     */
    public static AgiAgentPresetDialogue createFromJsonStr(String jsonStr) {
        AgiAgentPresetDialogue presetDialogue = JSONUtil.toBean(jsonStr, AgiAgentPresetDialogue.class);
        return presetDialogue;
    }
    /**
     * 预设对话消息
     */
    @Data
    public static class AgiAgentPresetDialogueMessage {
        /**
         * 消息类型
         * 参考{@link AiMessageType} 的name()
         */
        private String messageType;
        /**
         * 消息文本
         */
        private String text;

        public static AgiAgentPresetDialogueMessage create(String messageType, String text) {

            AgiAgentPresetDialogueMessage agiAgentPresetDialogueMessage = new AgiAgentPresetDialogueMessage();
            agiAgentPresetDialogueMessage.setMessageType(messageType);
            agiAgentPresetDialogueMessage.setText(text);
            return agiAgentPresetDialogueMessage;
        }
    }
}
