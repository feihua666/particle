package com.particle.agi.client.chat.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 智能体对话消息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Data
@Schema
public class AgiAgentChatMessageVO extends AbstractBaseIdVO {

    @Schema(description = "智能体对话id")
    private Long agiAgentChatId;
    
    @Schema(description = "智能体id")
    private Long agiAgentId;
    
    @Schema(description = "对话id")
    private String chatId;
    
    @Schema(description = "用户id")
    private Long userId;
    
    @Schema(description = "消息类型")
    private String messageType;
    
    @Schema(description = "消息内容")
    private String content;

	@Schema(description = "元数据信息json")
	private String metadataJson;
    
    @Schema(description = "描述")
    private String remark;
    


}