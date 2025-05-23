package com.particle.scheduler.client.datatask.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 任务数据返回结果 VO
 * </p>
 *
 * @author yangwei
 * @since 2025/5/23 09:43
 */
@Data
@Schema
public class SchedulerDataTaskVO extends VO {

    @Schema(description = "唯一标识")
    private String uniqueIdentifier;

    @Schema(description = "处理状态码")
    private String handleStatusCode;

    @Schema(description = "处理状态名称")
    private String handleStatusName;

    @Schema(description = "处理结果数据")
    private Object result;

    public static SchedulerDataTaskVO create(String uniqueIdentifier, String handleStatusCode, String handleStatusName, Object result) {
        SchedulerDataTaskVO vo = new SchedulerDataTaskVO();
        vo.uniqueIdentifier = uniqueIdentifier;
        vo.handleStatusCode = handleStatusCode;
        vo.handleStatusName = handleStatusName;
        vo.result = result;
        return vo;
    }
}
