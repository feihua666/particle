package com.particle.message.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 用户消息读取状态表
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@TableName("component_message_user_state")
public class MessageUserStateDO extends BaseDO {

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
    

}
