package com.particle.workflow.infrastructure.execution.callback;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.particle.global.exception.Assert;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.workflow.dto.ExecutionDTO;
import com.particle.global.workflow.dto.NodeExecutionDTO;
import com.particle.global.workflow.dto.WorkflowGraphDTO;
import com.particle.global.workflow.repository.WorkflowExecutionRepository;
import com.particle.workflow.domain.enums.WorkflowExecutionStatus;
import com.particle.workflow.domain.enums.WorkflowExecutionTriggerType;
import com.particle.workflow.domain.gateway.WorkflowDictGateway;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 工作流执行数据访问实现
 * <p>
 * 直接实现 global-workflow 层的 WorkflowExecutionRepository 接口，
 * 直接操作数据库，不使用额外的 Gateway 层。
 * </p>
 *
 * @author particle
 * @since 2026-05-02
 */
@Slf4j
@Component
public class WorkflowExecutionRepositoryImpl implements WorkflowExecutionRepository {

    @Autowired
    private IWorkflowExecutionService workflowExecutionService;
    @Autowired
    private IWorkflowDefinitionService iWorkflowDefinitionService;
    @Autowired
    private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;
    @Autowired
    private IWorkflowExecutionNodeService workflowExecutionNodeService;
    @Autowired
    private WorkflowDictGateway workflowDictGateway;

    // ==================== 定义 ====================

    @Override
    public WorkflowGraphDTO getWorkflowGraph(Long definitionId, Long historyId) {
        if (historyId == null) {
            WorkflowDefinitionDO definitionDO = iWorkflowDefinitionService.getById(definitionId);
            historyId = definitionDO.getLatestPublishWorkflowDefinitionHistoryId();
        }
        WorkflowDefinitionHistoryDO historyDO = iWorkflowDefinitionHistoryService.getById(historyId);

        String graphDataJson = historyDO.getGraphDataJson();
        if (graphDataJson == null || graphDataJson.isBlank()) {
            return null;
        }
        try {
            return JsonTool.getObjectMapper().readValue(graphDataJson, WorkflowGraphDTO.class);
        } catch (Exception e) {
            log.error("反序列化 graphDataJson 失败: definitionId={}", definitionId, e);
            throw new RuntimeException("反序列化 graphDataJson 失败", e);
        }
    }

    // ==================== 执行记录 ====================

    @Override
    public Long createExecution(Long definitionId, Long historyId,
                                String triggerType, Long copiedExecutionId) {
        if (historyId == null) {
            WorkflowDefinitionDO definitionDO = iWorkflowDefinitionService.getById(definitionId);
            historyId = definitionDO.getLatestPublishWorkflowDefinitionHistoryId();
        }
        Assert.notNull(historyId, "没有可用的流程定义版本");

        WorkflowExecutionDO executionDO = new WorkflowExecutionDO();
        executionDO.setWorkflowDefinitionId(definitionId);
        executionDO.setWorkflowDefinitionHistoryId(historyId);
        executionDO.setStatusDictId(getExecuteStatusDictId(WorkflowExecutionStatus.PENDING.itemValue()));
        executionDO.setTriggerTypeDictId(getTriggerTypeDictId(triggerType));
        executionDO.setContextJson("{}");
        executionDO.setStartAt(LocalDateTime.now());

        // 如果有数据来源，拷贝数据和节点状态
        if (copiedExecutionId != null) {
            executionDO.setCopiedWorkflowExecutionId(copiedExecutionId);
            WorkflowExecutionDO sourceDO = workflowExecutionService.getById(copiedExecutionId);
            if (sourceDO != null) {
                executionDO.setContextJson(sourceDO.getContextJson());
            }
        }

        workflowExecutionService.save(executionDO);
        copyNodeExecutions(copiedExecutionId, executionDO.getId());
        log.info("创建执行记录: id={}, definitionId={}, triggerType={}, copiedFrom={}",
                executionDO.getId(), definitionId, triggerType, copiedExecutionId);
        return executionDO.getId();
    }

    @Override
    public ExecutionDTO getExecution(Long executionId) {
        WorkflowExecutionDO executionDO = workflowExecutionService.getById(executionId);
        if (executionDO == null) {
            return null;
        }
        ExecutionDTO dto = new ExecutionDTO();
        dto.setExecutionId(executionDO.getId());
        dto.setDefinitionId(executionDO.getWorkflowDefinitionId());
        dto.setHistoryId(executionDO.getWorkflowDefinitionHistoryId());
        dto.setStatusValue(getExecuteStatusValue(executionDO.getStatusDictId()));
        dto.setStatusName(getExecuteStatusName(executionDO.getStatusDictId()));
        dto.setTriggerTypeValue(getTriggerTypeValue(executionDO.getTriggerTypeDictId()));
        dto.setCurrentNodeId(executionDO.getNodeId());
        dto.setStartAt(executionDO.getStartAt());
        dto.setEndAt(executionDO.getFinishAt());
        dto.setErrorMsg(executionDO.getErrorMsg());
        dto.setCopiedExecutionId(executionDO.getCopiedWorkflowExecutionId());

        // context JSON -> Map
        if (executionDO.getContextJson() != null) {
            try {
                dto.setContext(JsonTool.getObjectMapper().readValue(executionDO.getContextJson(),
                        new TypeReference<Map<String, Object>>() {}));
            } catch (Exception e) {
                log.error("反序列化上下文失败: executionId={}", executionId, e);
                dto.setContext(Collections.emptyMap());
            }
        } else {
            dto.setContext(Collections.emptyMap());
        }
        return dto;
    }

    @Override
    public void updateExecutionStatus(Long executionId, String status) {
        WorkflowExecutionDO executionDO = workflowExecutionService.getById(executionId);
        Assert.notNull(executionDO, "执行记录不存在: " + executionId);
        executionDO.setStatusDictId(getExecuteStatusDictId(status));
        if (WorkflowExecutionStatus.COMPLETED.itemValue().equals(status)
                || WorkflowExecutionStatus.FAILED.itemValue().equals(status)
                || WorkflowExecutionStatus.STOPPED.itemValue().equals(status)) {
            executionDO.setFinishAt(LocalDateTime.now());
        }
        workflowExecutionService.updateById(executionDO);
    }

    @Override
    public void updateExecutionContext(Long executionId, Map<String, Object> context) {
        WorkflowExecutionDO executionDO = workflowExecutionService.getById(executionId);
        Assert.notNull(executionDO, "执行记录不存在: " + executionId);
        try {
            executionDO.setContextJson(JsonTool.getObjectMapper().writeValueAsString(context));
        } catch (Exception e) {
            log.error("序列化上下文失败: executionId={}", executionId, e);
        }
        workflowExecutionService.updateById(executionDO);
    }

    // ==================== 节点执行 ====================

    @Override
    public NodeExecutionDTO getNodeExecution(Long executionId, String nodeId) {
        WorkflowExecutionNodeDO nodeDO = workflowExecutionNodeService.getOne(
                Wrappers.<WorkflowExecutionNodeDO>lambdaQuery()
                        .eq(WorkflowExecutionNodeDO::getWorkflowExecutionId, executionId)
                        .eq(WorkflowExecutionNodeDO::getNodeId, nodeId));
        return nodeDO != null ? toNodeExecutionDTO(nodeDO) : null;
    }

    @Override
    public List<NodeExecutionDTO> getNodeExecutions(Long executionId) {
        List<WorkflowExecutionNodeDO> nodeList = workflowExecutionNodeService.list(
                Wrappers.<WorkflowExecutionNodeDO>lambdaQuery()
                        .eq(WorkflowExecutionNodeDO::getWorkflowExecutionId, executionId)
                        .orderByAsc(WorkflowExecutionNodeDO::getStartAt));
        return nodeList.stream().map(this::toNodeExecutionDTO).toList();
    }

    @Override
    public void updateNodeExecution(Long executionId, String nodeId, String status,
                                    Map<String, Object> input,
                                    Map<String, Object> output,
                                    String errorMsg) {
        WorkflowExecutionNodeDO existing = workflowExecutionNodeService.getOne(
                Wrappers.<WorkflowExecutionNodeDO>lambdaQuery()
                        .eq(WorkflowExecutionNodeDO::getWorkflowExecutionId, executionId)
                        .eq(WorkflowExecutionNodeDO::getNodeId, nodeId));

        if (existing != null) {
            // 更新现有记录
            existing.setStatusDictId(getExecuteStatusDictId(status));
            if (input != null) {
                try { existing.setInputJson(JsonTool.getObjectMapper().writeValueAsString(input)); } catch (Exception ignored) {}
            }
            if (output != null) {
                try { existing.setOutputJson(JsonTool.getObjectMapper().writeValueAsString(output)); } catch (Exception ignored) {}
            }
            if (errorMsg != null) {
                existing.setErrorMsg(errorMsg);
            }
            if (WorkflowExecutionStatus.COMPLETED.itemValue().equals(status)
                    || WorkflowExecutionStatus.FAILED.itemValue().equals(status)) {
                existing.setFinishAt(LocalDateTime.now());
            }
            workflowExecutionNodeService.updateById(existing);
        } else {
            // 创建新记录（upsert 语义）
            WorkflowExecutionNodeDO nodeDO = new WorkflowExecutionNodeDO();
            nodeDO.setWorkflowExecutionId(executionId);
            nodeDO.setNodeId(nodeId);
            nodeDO.setStatusDictId(getExecuteStatusDictId(status));
            if (input != null) {
                try { nodeDO.setInputJson(JsonTool.getObjectMapper().writeValueAsString(input)); } catch (Exception ignored) {}
            }
            if (output != null) {
                try { nodeDO.setOutputJson(JsonTool.getObjectMapper().writeValueAsString(output)); } catch (Exception ignored) {}
            }
            if (errorMsg != null) {
                nodeDO.setErrorMsg(errorMsg);
            }
            nodeDO.setStartAt(LocalDateTime.now());
            if (WorkflowExecutionStatus.COMPLETED.itemValue().equals(status)
                    || WorkflowExecutionStatus.FAILED.itemValue().equals(status)) {
                nodeDO.setFinishAt(LocalDateTime.now());
            }
            workflowExecutionNodeService.save(nodeDO);
        }
    }

    // ==================== 内部方法 ====================

    /**
     * 拷贝节点执行数据
     */
    private void copyNodeExecutions(Long fromExecutionId, Long toExecutionId) {
        List<WorkflowExecutionNodeDO> sourceNodes = workflowExecutionNodeService.list(
                Wrappers.<WorkflowExecutionNodeDO>lambdaQuery()
                        .eq(WorkflowExecutionNodeDO::getWorkflowExecutionId, fromExecutionId));
        for (WorkflowExecutionNodeDO sourceNode : sourceNodes) {
            WorkflowExecutionNodeDO targetNode = new WorkflowExecutionNodeDO();
            targetNode.setWorkflowExecutionId(toExecutionId);
            targetNode.setNodeId(sourceNode.getNodeId());
            targetNode.setStatusDictId(sourceNode.getStatusDictId());
            targetNode.setInputJson(sourceNode.getInputJson());
            targetNode.setOutputJson(sourceNode.getOutputJson());
            targetNode.setErrorMsg(sourceNode.getErrorMsg());
            targetNode.setStartAt(sourceNode.getStartAt());
            targetNode.setFinishAt(sourceNode.getFinishAt());
            workflowExecutionNodeService.save(targetNode);
        }
    }

    /**
     * 将 DO 转换为 NodeExecutionDTO
     */
    private NodeExecutionDTO toNodeExecutionDTO(WorkflowExecutionNodeDO nodeDO) {
        NodeExecutionDTO dto = new NodeExecutionDTO();
        dto.setNodeId(nodeDO.getNodeId());
        dto.setStatusValue(getExecuteStatusValue(nodeDO.getStatusDictId()));
        dto.setStatusName(getExecuteStatusName(nodeDO.getStatusDictId()));
        dto.setErrorMsg(nodeDO.getErrorMsg());
        dto.setStartAt(nodeDO.getStartAt());
        dto.setEndAt(nodeDO.getFinishAt());
        if (nodeDO.getOutputJson() != null) {
            try {
                dto.setOutput(JsonTool.getObjectMapper().readValue(nodeDO.getOutputJson(),
                        new TypeReference<Map<String, Object>>() {}));
            } catch (Exception e) {
                log.warn("反序列化节点输出失败: nodeId={}", nodeDO.getNodeId(), e);
            }
        }
        return dto;
    }

    /**
     * 获取执行状态字典 ID
     */
    private Long getExecuteStatusDictId(String status) {
        return workflowDictGateway.getDictIdByGroupCodeAndItemValue(
                WorkflowExecutionStatus.Group.workflow_execution_status.groupCode(), status);
    }

    /**
     * 获取执行状态值
     */
    private String getExecuteStatusValue(Long dictId) {
        return workflowDictGateway.getDictValueById(dictId);
    }

    /**
     * 获取状态中文名
     */
    private String getExecuteStatusName(Long dictId) {
        return workflowDictGateway.getDictNameById(dictId);
    }

    /**
     * 获取触发类型字典 ID
     */
    private Long getTriggerTypeDictId(String triggerType) {
        return workflowDictGateway.getDictIdByGroupCodeAndItemValue(
                WorkflowExecutionTriggerType.Group.workflow_execution_trigger_type.groupCode(), triggerType);
    }

    /**
     * 获取触发类型值
     */
    private String getTriggerTypeValue(Long dictId) {
        return workflowDictGateway.getDictValueById(dictId);
    }

}
