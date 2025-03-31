package com.particle.agi.domain.chat;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 智能体对话消息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Data
@Entity
public class AgiAgentChatMessage extends AggreateRoot {

    private AgiAgentChatMessageId id;

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



    /**
     * 创建智能体对话消息领域模型对象
     * @return 智能体对话消息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAgentChatMessage create(){
        return DomainFactory.create(AgiAgentChatMessage.class);
    }
}