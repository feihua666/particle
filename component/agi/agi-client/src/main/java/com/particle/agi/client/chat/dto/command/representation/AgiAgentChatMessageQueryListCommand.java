package com.particle.agi.client.chat.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 智能体对话消息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Data
@Schema
public class AgiAgentChatMessageQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "元数据信息json")
	private String metadataJson;



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











}