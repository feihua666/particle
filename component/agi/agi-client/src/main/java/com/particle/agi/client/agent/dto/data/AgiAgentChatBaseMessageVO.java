package com.particle.agi.client.agent.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 智能体对话消息基类
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatBaseMessageDTO} 的前端响应结果
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Schema
@Data
public abstract class AgiAgentChatBaseMessageVO extends VO {

    @Schema(description = "消息类型")
    private String messageType;

    @Schema(description = "消息文本")
    private String text;

    @Schema(description = "元数据信息")
    private Map<String, Object> metadata;
}
