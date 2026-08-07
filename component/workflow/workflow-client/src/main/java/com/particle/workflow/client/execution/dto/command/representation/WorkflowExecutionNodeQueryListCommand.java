package com.particle.workflow.client.execution.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流节点执行实例 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Data
@Schema
public class WorkflowExecutionNodeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "工作流执行ID")
    private Long workflowExecutionId;


    @Schema(description = "节点ID（对应graph里的id）")
    private String nodeId;


    @Schema(description = "状态：pending/running/success/failed")
    private Long statusDictId;




    @Schema(description = "错误信息")
    private String errorMsg;



    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    








}
