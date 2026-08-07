package com.particle.workflow.client.execution.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流节点执行实例 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Data
@Schema
public class WorkflowExecutionNodeVO extends AbstractBaseIdVO {

    @Schema(description = "工作流执行 ID")
    private Long workflowExecutionId;

    @Schema(description = "节点 ID（对应graph里的id）")
    private String nodeId;

    @Schema(description = "执行状态字典 id")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "执行状态字典名称")
    private String statusDictName;

    @Schema(description = "节点输入")
    private String inputJson;

    @Schema(description = "节点输出")
    private String outputJson;

    @Schema(description = "错误信息")
    private String errorMsg;

    @Schema(description = "重试次数")
    private Integer retryCount;

    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;



}
