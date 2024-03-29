package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户消息读取状态 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@Schema
public class MessageUserStateCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "消息表主键 不能为空")
        @Schema(description = "消息表主键",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long messageId;


    @NotNull(message = "用户id 不能为空")
        @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;


    @NotNull(message = "是否已读 不能为空")
        @Schema(description = "是否已读",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRead;


    @Schema(description = "读取时间")
    private LocalDateTime readAt;
    








}
