package com.particle.agi.client.chat.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 智能体对话消息工具调用 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Data
@Schema
public class AgiAgentChatMessageToolcallPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "智能体对话消息id")
    private Long agiAgentChatMessageId;


    @Schema(description = "唯一id")
    private String uniqueId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "类型")
    private String type;











}
