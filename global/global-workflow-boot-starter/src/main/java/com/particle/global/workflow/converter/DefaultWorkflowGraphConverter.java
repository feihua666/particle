package com.particle.global.workflow.converter;

import com.particle.global.dag.model.*;
import com.particle.global.workflow.dto.WorkflowEdge;
import com.particle.global.workflow.dto.WorkflowGraphDTO;
import com.particle.global.workflow.dto.WorkflowNode;
import com.particle.global.workflow.dto.WorkflowNodePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 工作流图数据转换器默认实现
 * <p>
 * 将 {@link WorkflowGraphDTO} 转换为 {@link DagDefinition}。
 * 字段映射关系：
 * <ul>
 *   <li>WorkflowNode.id → DagNode.id</li>
 *   <li>WorkflowNode.data.name → DagNode.name</li>
 *   <li>WorkflowNode.data.nodeType → DagNode.type</li>
 *   <li>WorkflowNode.data.nodeRole → DagNode.role</li>
 *   <li>WorkflowNode.data.inputPorts → DagNode.inputPorts</li>
 *   <li>WorkflowNode.data.outputPorts → DagNode.outputPorts</li>
 *   <li>WorkflowNode.data.valuePorts → DagNode.valuePorts</li>
 *   <li>WorkflowNode.data.config → DagNode.config</li>
 *   <li>WorkflowEdge.id → DagEdge.id</li>
 *   <li>WorkflowEdge.source → DagEdge.fromNodeId</li>
 *   <li>WorkflowEdge.target → DagEdge.toNodeId</li>
 *   <li>WorkflowEdge.sourceHandle → DagEdge.fromPort</li>
 *   <li>WorkflowEdge.targetHandle → DagEdge.toPort</li>
 *   <li>WorkflowEdge.data.edgeType → DagEdge.type</li>
 *   <li>WorkflowEdge.data.condition → DagEdge.condition</li>
 * </ul>
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
public class DefaultWorkflowGraphConverter implements WorkflowGraphConverter {

    private static final Logger log = LoggerFactory.getLogger(DefaultWorkflowGraphConverter.class);

    @Override
    public DagDefinition convert(WorkflowGraphDTO graphDTO) {
        if (graphDTO == null || graphDTO.getDefinition() == null) {
            throw new IllegalArgumentException("WorkflowGraphDTO 或 definition 不能为空");
        }

        List<DagNode> dagNodes = convertNodes(graphDTO.getDefinition().getNodes());
        List<DagEdge> dagEdges = convertEdges(graphDTO.getDefinition().getEdges());

        return new DagDefinition(null, null, dagNodes, dagEdges, null);
    }

    /**
     * 转换节点列表
     */
    private List<DagNode> convertNodes(List<WorkflowNode> nodes) {
        if (nodes == null) {
            return new ArrayList<>();
        }

        List<DagNode> dagNodes = new ArrayList<>(nodes.size());
        for (WorkflowNode node : nodes) {
            dagNodes.add(convertNode(node));
        }
        return dagNodes;
    }

    /**
     * 转换单个节点
     */
    private DagNode convertNode(WorkflowNode node) {
        DagNode dagNode = new DagNode();
        dagNode.setId(node.getId());
        if (node.getData() != null) {
            dagNode.setName(node.getData().getName());
            dagNode.setType(node.getData().getNodeType());
            dagNode.setRole(node.getData().getNodeRole());
            dagNode.setConfig(node.getData().getConfig());
            dagNode.setInputPorts(convertPorts(node.getData().getInputPorts()));
            dagNode.setOutputPorts(convertPorts(node.getData().getOutputPorts()));
            dagNode.setValuePorts(convertPorts(node.getData().getValuePorts()));
        }

        return dagNode;
    }

    /**
     * 转换端口列表
     */
    private Map<String, NodePort> convertPorts(Map<String, WorkflowNodePort> ports) {
        if (ports == null) {
            return null;
        }

        Map<String, NodePort> dagPorts = new HashMap<>();
        for (Map.Entry<String, WorkflowNodePort> stringWorkflowNodePortEntry : ports.entrySet()) {
            NodePort convertedPort = convertPort(stringWorkflowNodePortEntry.getValue());
            convertedPort.setName(stringWorkflowNodePortEntry.getKey());
            dagPorts.put(convertedPort.getName(), convertedPort);
        }
        return dagPorts;
    }

    /**
     * 转换单个端口
     */
    private NodePort convertPort(WorkflowNodePort port) {
        List<DataType> dataTypes = null;
        try {
            if (port.getDataTypes() != null) {
                dataTypes = port.getDataTypes().stream().map(DataType::valueOf).toList();
            }
        } catch (IllegalArgumentException e) {
            log.warn("未知的数据类型: {}", port.getDataTypes());
            dataTypes = Collections.emptyList();
        }
        PortType portType = null;
        try {
            portType = PortType.valueOf(port.getPortType());
        } catch (IllegalArgumentException e) {
            log.warn("未知的端口类型: {}", port.getPortType());
            portType = null;
        }

        return new NodePort(port.getName(),portType, dataTypes, port.getData());
    }

    /**
     * 转换边列表
     */
    private List<DagEdge> convertEdges(List<WorkflowEdge> edges) {
        if (edges == null) {
            return new ArrayList<>();
        }

        List<DagEdge> dagEdges = new ArrayList<>(edges.size());
        for (WorkflowEdge edge : edges) {
            dagEdges.add(convertEdge(edge));
        }
        return dagEdges;
    }

    /**
     * 转换单个边
     */
    private DagEdge convertEdge(WorkflowEdge edge) {
        DagEdge dagEdge = new DagEdge();
                dagEdge.setId(edge.getId());
                dagEdge.setFromNodeId(edge.getSource());
                dagEdge.setToNodeId(edge.getTarget());
                dagEdge.setFromPort(edge.getSourceHandle());
                dagEdge.setToPort(edge.getTargetHandle());

        if (edge.getData() != null) {
            dagEdge.setType(edge.getData().getEdgeType());
            dagEdge.setCondition(edge.getData().getCondition());
        }

        return dagEdge;
    }
}
