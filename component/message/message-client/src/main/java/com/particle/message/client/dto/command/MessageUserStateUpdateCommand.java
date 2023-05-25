package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户消息读取状态 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@ApiModel
public class MessageUserStateUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "消息表主键 不能为空")
        @ApiModelProperty(value = "消息表主键",required = true)
    private Long messageId;


    @NotNull(message = "用户id 不能为空")
        @ApiModelProperty(value = "用户id",required = true)
    private Long userId;


    @NotNull(message = "是否已读 不能为空")
        @ApiModelProperty(value = "是否已读",required = true)
    private Boolean isRead;


    @ApiModelProperty(value = "读取时间")
    private LocalDateTime readAt;
    








}
