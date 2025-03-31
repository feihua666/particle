package com.particle.agi.infrastructure.chat.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 智能体对话表
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_agent_chat")
public class AgiAgentChatDO extends BaseDO {

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


    public static AgiAgentChatDO create(Long agiAgentId, String chatId, Long userId, String title, String titleMemo, String remark) {
        AgiAgentChatDO agiAgentChatDO = new AgiAgentChatDO();
        agiAgentChatDO.setAgiAgentId(agiAgentId);
        agiAgentChatDO.setChatId(chatId);
        agiAgentChatDO.setUserId(userId);
        agiAgentChatDO.setTitle(title);
        agiAgentChatDO.setTitleMemo(titleMemo);
        agiAgentChatDO.setRemark(remark);
        return agiAgentChatDO;
    }
}
