package com.particle.message.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
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
@ApiModel
public class MessageUserStateVO extends AbstractBaseIdVO {

    @ApiModelProperty("消息表主键")
    private Long messageId;
    
    @ApiModelProperty("用户id")
    private Long userId;
    
    @ApiModelProperty("是否已读")
    private Boolean isRead;
    
    @ApiModelProperty("读取时间")
    private LocalDateTime readAt;
        


}
