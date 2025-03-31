package com.particle.agi.domain.chat;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 智能体对话 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Data
@Entity
public class AgiAgentChat extends AggreateRoot {

    private AgiAgentChatId id;

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
    * 对话标题
    */
    private String title;

    /**
    * 对话标题说明，一般说明来源如：是如何设置的标题
    */
    private String titleMemo;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建智能体对话领域模型对象
     * @return 智能体对话领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAgentChat create(){
        return DomainFactory.create(AgiAgentChat.class);
    }
}
