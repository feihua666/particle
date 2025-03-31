package com.particle.agi.client.chat.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 智能体对话 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Data
@Schema
public class AgiAgentChatVO extends AbstractBaseIdVO {

    @Schema(description = "智能体id")
    private Long agiAgentId;

    @Schema(description = "对话id")
    private String chatId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "对话标题")
    private String title;

    @Schema(description = "对话标题说明")
    private String titleMemo;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "创建时间")
    private String createAt;

}
