package com.particle.global.workflow.converter;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.workflow.dto.WorkflowGraphDTO;

/**
 * 工作流图数据转换器
 * <p>
 * 将 {@link WorkflowGraphDTO}（前端 graphDataJson 反序列化结果）
 * 转换为 {@link DagDefinition}（DAG 引擎执行用）。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
public interface WorkflowGraphConverter {

    /**
     * 将工作流图数据转换为 DAG 定义
     *
     * @param graphDTO 工作流图数据
     * @return DAG 定义
     */
    DagDefinition convert(WorkflowGraphDTO graphDTO);
}
