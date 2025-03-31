package com.particle.agi.client.chat.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 智能体对话消息工具 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Data
@Schema
public class AgiAgentChatMessageToolQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "智能体对话消息id")
    private Long agiAgentChatMessageId;


    @Schema(description = "唯一id")
    private String uniqueId;


    @Like
        @Schema(description = "名称,左前缀匹配")
    private String name;











}
