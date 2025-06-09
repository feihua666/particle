package com.particle.agi.infrastructure.chat.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.ai.content.Media;

/**
 * <p>
 * 智能体对话消息媒体表
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_agent_chat_message_media")
public class AgiAgentChatMessageMediaDO extends BaseDO {

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


    public static AgiAgentChatMessageMediaDO createByMedia(Media media,Long agiAgentChatMessageId) {
        AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO = new AgiAgentChatMessageMediaDO();
        agiAgentChatMessageMediaDO.agiAgentChatMessageId = agiAgentChatMessageId;
        agiAgentChatMessageMediaDO.uniqueId = media.getId();
        agiAgentChatMessageMediaDO.name = media.getName();
        agiAgentChatMessageMediaDO.mimeType = media.getMimeType().toString();
        agiAgentChatMessageMediaDO.url = media.getData().toString();
        return agiAgentChatMessageMediaDO;

    }
}
