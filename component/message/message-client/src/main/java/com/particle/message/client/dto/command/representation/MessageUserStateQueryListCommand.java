package com.particle.message.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
/**
 * <p>
 * 用户消息读取状态 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Data
@ApiModel
public class MessageUserStateQueryListCommand extends AbstractBaseQueryCommand {



    @NotNull(message = "消息Id 不能为空")
    @ApiModelProperty(value = "消息Id",required = true)
    private Long messageId;


    @ApiModelProperty(value = "用户id")
    private Long userId;


    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;


    @ApiModelProperty(value = "读取时间")
    private LocalDateTime readAt;

}
