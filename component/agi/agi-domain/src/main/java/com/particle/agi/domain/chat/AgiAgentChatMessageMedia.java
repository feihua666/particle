package com.particle.agi.domain.chat;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 智能体对话消息媒体 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Data
@Entity
public class AgiAgentChatMessageMedia extends AggreateRoot {

    private AgiAgentChatMessageMediaId id;

    /**
    * 智能体对话消息id
    */
    private Long agiAgentChatMessageId;

    /**
    * 唯一id
    */
    private String uniqueId;

    /**
    * 名称，一般为带扩展名的文件名
    */
    private String name;

    /**
    * 媒体类型
    */
    private String mimeType;

    /**
    * 地址，地址可以是文件目录路径或者网络路径
    */
    private String url;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建智能体对话消息媒体领域模型对象
     * @return 智能体对话消息媒体领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAgentChatMessageMedia create(){
        return DomainFactory.create(AgiAgentChatMessageMedia.class);
    }
}
