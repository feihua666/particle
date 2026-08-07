package com.particle.global.workflow.vo;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 节点执行结果 VO
 *
 * @author particle
 * @since 2026-05-01
 */
@Data
public class NodeExecutionDetailVO extends VO {

    @Schema(description = "节点ID")
    private String nodeId;

    @Schema(description = "节点类型")
    private String nodeType;

    @Schema(description = "执行状态值")
    private String statusValue;

    @Schema(description = "执行状态名称")
    private String statusName;

    @Schema(description = "输出数据")
    private Map<String, Object> output;

    @Schema(description = "错误信息")
    private String errorMsg;

    @Schema(description = "开始时间")
    private LocalDateTime startAt;

    @Schema(description = "结束时间")
    private LocalDateTime endAt;
}
