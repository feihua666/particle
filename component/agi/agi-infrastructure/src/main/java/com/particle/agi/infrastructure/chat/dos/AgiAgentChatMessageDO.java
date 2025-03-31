package com.particle.agi.infrastructure.chat.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.agi.domain.agent.value.AgiAgentPresetDialogue;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.messages.*;

/**
 * <p>
 * 智能体对话消息表
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_agent_chat_message")
public class AgiAgentChatMessageDO extends BaseDO {

    /**
    * 智能体对话id
    */
    private Long agiAgentChatId;

    /**
    * 智能体id，不强制依赖智能体
    */
    private Long agiAgentId;

    /**
    * 对话id，唯一
    */
    private String chatId;

    /**
    * 用户id，是哪个用户的对话
    */
    private Long userId;

    /**
    * 消息类型，用来标识是谁的消息如：user、assistant、system
    */
    private String messageType;

    /**
    * 消息内容
    */
    private String content;

	/**
	 * 元数据信息json
	 */
	private String metadataJson;

    /**
    * 描述
    */
    private String remark;


    public static AgiAgentChatMessageDO createByAbstractMessage(AbstractMessage abstractMessage,
                                                                Long agiAgentChatId,
                                                                Long agiAgentId,
                                                                String chatId,
                                                                Long userId) {
        AgiAgentChatMessageDO agiAgentChatMessageDO = new AgiAgentChatMessageDO();
        agiAgentChatMessageDO.agiAgentChatId = agiAgentChatId;
        agiAgentChatMessageDO.agiAgentId = agiAgentId;
        agiAgentChatMessageDO.chatId = chatId;
        agiAgentChatMessageDO.userId = userId;

        agiAgentChatMessageDO.messageType = abstractMessage.getMessageType().getValue();
        agiAgentChatMessageDO.content = abstractMessage.getText();
        agiAgentChatMessageDO.metadataJson = JsonTool.toJsonStr(abstractMessage.getMetadata());

        return agiAgentChatMessageDO;
    }

    public static AgiAgentChatMessageDO createBySystemMessage(SystemMessage systemMessage,
                                                              Long agiAgentChatId,
                                                              Long agiAgentId,
                                                              String chatId,
                                                              Long userId){
        AgiAgentChatMessageDO agiAgentChatMessageDO = createByAbstractMessage(systemMessage,
                agiAgentChatId,
                agiAgentId,
                chatId,
                userId);
        return agiAgentChatMessageDO;
    }

    public static AgiAgentChatMessageDO createByToolResponseMessage(ToolResponseMessage toolResponseMessage,
                                                              Long agiAgentChatId,
                                                              Long agiAgentId,
                                                              String chatId,
                                                              Long userId){
        AgiAgentChatMessageDO agiAgentChatMessageDO = createByAbstractMessage(toolResponseMessage,
                agiAgentChatId,
                agiAgentId,
                chatId,
                userId);
        return agiAgentChatMessageDO;
    }

    public static AgiAgentChatMessageDO createByUserMessage(UserMessage userMessage,
                                                            Long agiAgentChatId,
                                                            Long agiAgentId,
                                                            String chatId,
                                                            Long userId){
        AgiAgentChatMessageDO agiAgentChatMessageDO = createByAbstractMessage(userMessage,
                agiAgentChatId,
                agiAgentId,
                chatId,
                userId);

        return agiAgentChatMessageDO;
    }
    public static AgiAgentChatMessageDO createByAssistantMessage(AssistantMessage assistantMessage,
                                                                 Long agiAgentChatId,
                                                                 Long agiAgentId,
                                                                 String chatId,
                                                                 Long userId){
        AgiAgentChatMessageDO agiAgentChatMessageDO = createByAbstractMessage(assistantMessage,
                agiAgentChatId,
                agiAgentId,
                chatId,
                userId);

        return agiAgentChatMessageDO;
    }
    public static AgiAgentChatMessageDO createByAgiAgentPresetDialogueMessage(AgiAgentPresetDialogue.AgiAgentPresetDialogueMessage message,
                                                                              Long agiAgentChatId,
                                                                              Long agiAgentId,
                                                                              String chatId,
                                                                              Long userId) {
        AgiAgentChatMessageDO agiAgentChatMessageDO = new AgiAgentChatMessageDO();
        agiAgentChatMessageDO.agiAgentChatId = agiAgentChatId;
        agiAgentChatMessageDO.agiAgentId = agiAgentId;
        agiAgentChatMessageDO.chatId = chatId;
        agiAgentChatMessageDO.userId = userId;

        agiAgentChatMessageDO.messageType = message.getMessageType();
        agiAgentChatMessageDO.content = message.getText();

        return agiAgentChatMessageDO;
    }
}
