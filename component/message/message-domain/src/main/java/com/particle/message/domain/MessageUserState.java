package com.particle.message.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 用户消息读取状态 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@Entity
public class MessageUserState extends AggreateRoot {

    private MessageUserStateId id;

    /**
    * 消息表主键
    */
    private Long messageId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 是否已读，1=已读，0=未读
    */
    private Boolean isRead;

    /**
    * 读取时间
    */
    private LocalDateTime readAt;



    /**
     * 创建用户消息读取状态领域模型对象
     * @return 用户消息读取状态领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static MessageUserState create(){
        return DomainFactory.create(MessageUserState.class);
    }
}
