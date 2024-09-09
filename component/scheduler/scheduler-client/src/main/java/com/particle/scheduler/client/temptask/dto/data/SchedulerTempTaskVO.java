package com.particle.scheduler.client.temptask.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划临时任务 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Data
@Schema
public class SchedulerTempTaskVO extends AbstractBaseIdVO {

    @Schema(description = "临时任务编码")
    private String code;
    
    @Schema(description = "临时任务名称")
    private String name;


    @Schema(description = "创建时间")
    private LocalDateTime createAt;


}
