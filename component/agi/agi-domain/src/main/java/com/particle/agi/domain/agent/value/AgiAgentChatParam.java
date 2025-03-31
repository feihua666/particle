package com.particle.agi.domain.agent.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Data;

/**
 * <p>
 * 智能体对话参数
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public class AgiAgentChatParam extends ValueObjRoot {
    /**
     * 智能体id
     */
    private Long agiAgentId;

    /**
     * 对话id
     */
    private String chatId;
    /**
     * 用户id，是哪个用户的对话
     */
    private Long userId;
    /**
     * 消息，用户的问题消息
     */
    private String message;

    /**
     * 对话标题
     */
    private String chatTitle;

    /**
     * 对话标题备注
     */
    private String chatTitleMemo;


    public static AgiAgentChatParam create(Long agiAgentId,
                                           String chatId,
                                           String message)
    {
        AgiAgentChatParam agiAgentChatParam = new AgiAgentChatParam();
        agiAgentChatParam.agiAgentId = agiAgentId;
        agiAgentChatParam.chatId = chatId;
        agiAgentChatParam.message = message;
        return agiAgentChatParam;
    }
}
