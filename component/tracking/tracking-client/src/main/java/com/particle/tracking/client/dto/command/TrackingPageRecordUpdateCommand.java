package com.particle.tracking.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class TrackingPageRecordUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "行为值")
    private String actionResult;

    @Schema(description = "离开页面时间")
    private LocalDateTime leaveAt;

    @Schema(description = "额外数据")
    private String extInfoJson;

    @Schema(description = "描述")
    private String remark;


}
