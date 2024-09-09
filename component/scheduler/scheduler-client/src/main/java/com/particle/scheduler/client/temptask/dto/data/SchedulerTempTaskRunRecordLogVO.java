package com.particle.scheduler.client.temptask.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划临时任务运行记录日志 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordLogVO extends AbstractBaseIdVO {

    @Schema(description = "临时任务运行记录id")
    private Long schedulerTempTaskRunRecordId;
    
    @Schema(description = "日志级别")
    private String level;
    
    @Schema(description = "日志内容")
    private String content;

    @Schema(description = "创建时间")
    private LocalDateTime createAt;

}
