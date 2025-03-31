package com.particle.agi.client.chat.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 智能体对话消息工具 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Data
@Schema
public class AgiAgentChatMessageToolVO extends AbstractBaseIdVO {

    @Schema(description = "智能体对话消息id")
    private Long agiAgentChatMessageId;
    
    @Schema(description = "唯一id")
    private String uniqueId;
    
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "数据")
    private String data;
    
    @Schema(description = "描述")
    private String remark;
    


}
