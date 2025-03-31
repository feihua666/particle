package com.particle.agi.infrastructure.chat.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.messages.AssistantMessage;

/**
 * <p>
 * 智能体对话消息工具调用表
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_agent_chat_message_toolcall")
public class AgiAgentChatMessageToolcallDO extends BaseDO {

    /**
    * 智能体对话消息id
    */
    private Long agiAgentChatMessageId;

    /**
    * 唯一id
    */
    private String uniqueId;

    /**
    * 名称
    */
    private String name;

    /**
    * 类型
    */
    private String type;

    /**
    * 参数
    */
    private String arguments;

    /**
    * 描述
    */
    private String remark;


    public static AgiAgentChatMessageToolcallDO createByToolCall(AssistantMessage.ToolCall toolCall,Long agiAgentChatMessageId) {
        AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO = new AgiAgentChatMessageToolcallDO();
        agiAgentChatMessageToolcallDO.agiAgentChatMessageId = (agiAgentChatMessageId);
        agiAgentChatMessageToolcallDO.uniqueId = toolCall.id();
        agiAgentChatMessageToolcallDO.name = toolCall.name();
        agiAgentChatMessageToolcallDO.type = toolCall.type();
        agiAgentChatMessageToolcallDO.arguments = toolCall.arguments();
        return agiAgentChatMessageToolcallDO;
    }
}
