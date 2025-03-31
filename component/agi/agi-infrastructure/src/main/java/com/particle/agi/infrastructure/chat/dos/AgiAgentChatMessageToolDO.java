package com.particle.agi.infrastructure.chat.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.messages.ToolResponseMessage;

/**
 * <p>
 * 智能体对话消息工具表
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_agent_chat_message_tool")
public class AgiAgentChatMessageToolDO extends BaseDO {

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
    * 数据
    */
    private String data;

    /**
    * 描述
    */
    private String remark;


    public static AgiAgentChatMessageToolDO createByToolResponse(ToolResponseMessage.ToolResponse toolResponse,Long agiAgentChatMessageId) {

        AgiAgentChatMessageToolDO agiAgentChatMessageToolDO = new AgiAgentChatMessageToolDO();

        agiAgentChatMessageToolDO.agiAgentChatMessageId = agiAgentChatMessageId;

        agiAgentChatMessageToolDO.setUniqueId(toolResponse.id());
        agiAgentChatMessageToolDO.setName(toolResponse.name());
        agiAgentChatMessageToolDO.setData(toolResponse.responseData());
        return agiAgentChatMessageToolDO;
    }
}
