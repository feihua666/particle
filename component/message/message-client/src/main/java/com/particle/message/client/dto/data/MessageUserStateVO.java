package com.particle.message.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 用户消息读取状态 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@Schema
public class MessageUserStateVO extends AbstractBaseIdVO {

    @Schema(description = "消息表主键")
    private Long messageId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "是否已读")
    private Boolean isRead;

    @Schema(description = "读取时间")
    private LocalDateTime readAt;



}
