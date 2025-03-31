package com.particle.agi.domain.chat;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 智能体对话消息工具调用 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Data
@Entity
public class AgiAgentChatMessageToolcall extends AggreateRoot {

    private AgiAgentChatMessageToolcallId id;

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



    /**
     * 创建智能体对话消息工具调用领域模型对象
     * @return 智能体对话消息工具调用领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAgentChatMessageToolcall create(){
        return DomainFactory.create(AgiAgentChatMessageToolcall.class);
    }
}
