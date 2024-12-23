package com.particle.message.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 用户消息读取状态 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@Schema
public class MessageUserStatePageQueryCommand extends AbstractBasePageQueryCommand {


    @NotNull(message = "消息Id 不能为空")
    @Schema(description = "消息Id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long messageId;


    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "是否已读")
    private Boolean isRead;


    @Schema(description = "读取时间")
    private LocalDateTime readAt;









}
