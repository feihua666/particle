package com.particle.agi.client.chat.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 智能体对话 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Data
@Schema
public class AgiAgentChatPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "智能体id")
    private Long agiAgentId;


    @Schema(description = "对话id")
    private String chatId;


    @Schema(description = "用户id")
    private Long userId;


    @Like
        @Schema(description = "对话标题,左前缀匹配")
    private String title;











}
