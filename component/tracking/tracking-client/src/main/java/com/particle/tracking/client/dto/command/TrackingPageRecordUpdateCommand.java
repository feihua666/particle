package com.particle.tracking.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 页面埋点记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@ApiModel
public class TrackingPageRecordUpdateCommand extends AbstractBaseUpdateCommand {

    @ApiModelProperty(value = "行为值")
    private String actionResult;

    @ApiModelProperty(value = "离开页面时间")
    private LocalDateTime leaveAt;

    @ApiModelProperty(value = "额外数据")
    private String extInfoJson;

    @ApiModelProperty(value = "描述")
    private String remark;


}
