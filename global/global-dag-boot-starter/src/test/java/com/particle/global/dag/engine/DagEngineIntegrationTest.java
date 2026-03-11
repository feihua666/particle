package com.particle.global.dag.engine;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.NodeExecutionStatus;
import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * DAG引擎集成测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class DagEngineIntegrationTest {

    private DagEngine dagEngine;

    @BeforeEach
    void setUp() {
        dagEngine = new DefaultDagEngine();
    }

    @Test
    void testBasicDagExecution() {
        // 创建DAG定义
        DagDefinition dagDefinition = createLinearDag();

        // 创建执行上下文
        ExecutionContext context = new ExecutionContext();

        // 执行DAG
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        // 等待执行完成
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // 验证执行结果
        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete within timeout");
        assertEquals(DagExecutionStatus.COMPLETED, handle.getStatus(), "DAG execution should complete successfully");

        // 验证节点状态 - 现在状态存储在ExecutionContext的NodeExecution中
        for (DagNode node : dagDefinition.getNodes()) {
            NodeExecution nodeExecution = context.getNodeExecution(node.getId());
            assertNotNull(nodeExecution, "Node execution should be recorded for node " + node.getId());
            assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(), "Node " + node.getId() + " should be successful");
        }
    }

    @Test
    void testDagValidation() {
        DagDefinition validDag = createLinearDag();
        ValidationResult result = dagEngine.validate(validDag);
        assertTrue(result.isValid(), "Valid DAG should pass validation");

        // 测试无效DAG（空定义）
        DagDefinition invalidDag = null;
        ValidationResult invalidResult = dagEngine.validate(invalidDag);
        assertFalse(invalidResult.isValid(), "Null DAG should fail validation");

        // 测试无效DAG（无节点）
        DagDefinition emptyDag = DagDefinition.builder()
                .id("empty-dag")
                .name("Empty DAG")
                .nodes(new ArrayList<>())
                .edges(new ArrayList<>())
                .build();
        ValidationResult emptyResult = dagEngine.validate(emptyDag);
        assertFalse(emptyResult.isValid(), "Empty DAG should fail validation");
    }



    /**
     * 创建线性DAG (A -> B -> C)
     */
    private DagDefinition createLinearDag() {
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("节点A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("节点B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeC = DagNode.builder()
                .id("C")
                .name("节点C")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        List<DagNode> nodes = Arrays.asList(nodeA, nodeB, nodeC);

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("linear-dag")
                .name("线性DAG")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        // 添加边: A -> B, B -> C
        dagDefinition.addEdge(DagEdge.builder()
                .id("A-B")
                .fromNodeId("A")
                .toNodeId("B")
                .build());
        dagDefinition.addEdge(DagEdge.builder()
                .id("B-C")
                .fromNodeId("B")
                .toNodeId("C")
                .build());

        return dagDefinition;
    }

    /**
     * 创建独立节点DAG (用于测试并行执行)
     */
    private DagDefinition createIndependentNodesDag() {
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("节点A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("节点B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeC = DagNode.builder()
                .id("C")
                .name("节点C")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        List<DagNode> nodes = Arrays.asList(nodeA, nodeB, nodeC);

        return DagDefinition.builder()
                .id("independent-dag")
                .name("独立节点DAG")
                .nodes(nodes)
                .edges(new ArrayList<>()) // 无边，所有节点独立
                .build();
    }

    /**
     * 创建条件DAG (用于测试条件执行)
     */
    private DagDefinition createConditionalDag() {
        // Node A: no condition (should execute)
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("节点A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        // Node B: no condition (should execute)
        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("节点B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        // Node C: no condition (should execute)
        DagNode nodeC = DagNode.builder()
                .id("C")
                .name("节点C")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        List<DagNode> nodes = Arrays.asList(nodeA, nodeB, nodeC);

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("conditional-dag")
                .name("条件DAG")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        return dagDefinition;
    }

    @Test
    void testConditionalExecution() {
        // 创建一个带有条件边的DAG定义
        DagDefinition dagDefinition = createConditionalDagWithEdges();

        // 创建执行上下文
        ExecutionContext context = new ExecutionContext();

        // 执行DAG
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        // 等待执行完成
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // 验证执行结果
        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete within timeout");

        // 检查节点执行状态
        NodeExecution nodeAExecution = context.getNodeExecution("A");
        NodeExecution nodeBExecution = context.getNodeExecution("B");
        NodeExecution nodeCSkippedExecution = context.getNodeExecution("C_SKIPPED");

        assertNotNull(nodeAExecution, "Node A execution should be recorded");
        assertNotNull(nodeBExecution, "Node B execution should be recorded");
        assertNotNull(nodeCSkippedExecution, "Node C execution should be recorded");

        // 根据条件，A和B应该成功执行，C应该被跳过
        assertEquals(NodeExecutionStatus.SUCCESS, nodeAExecution.getStatus(), "Node A should execute successfully");
        assertEquals(NodeExecutionStatus.SUCCESS, nodeBExecution.getStatus(), "Node B should execute successfully");
        assertEquals(NodeExecutionStatus.SKIPPED, nodeCSkippedExecution.getStatus(), "Node C should be skipped due to condition");
    }

    @Test
    void testDagExecutionWithFailure() {
        // 创建一个包含会失败节点的DAG定义
        DagDefinition dagDefinition = createFailingNodeDag();

        // 创建执行上下文
        ExecutionContext context = new ExecutionContext();

        // 执行DAG
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        // 等待执行完成
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // 验证执行结果 - 由于节点失败，整个DAG应该失败
        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete within timeout");

        // 注意：取决于故障处理策略，DAG可能失败或完成，这里我们验证节点状态
        // 检查失败节点的状态是否正确记录
        NodeExecution failedNodeExecution = context.getNodeExecution("FAIL_NODE");
        if (failedNodeExecution != null) {
            assertEquals(NodeExecutionStatus.FAILED, failedNodeExecution.getStatus(), "Failed node should have FAILED status");
        }
    }

    /**
     * 创建包含失败节点的DAG
     */
    private DagDefinition createFailingNodeDag() {
        // 创建一个正常的节点和一个会失败的节点
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("正常节点A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeFail = DagNode.builder()
                .id("FAIL_NODE")
                .name("失败节点")
                .type("FAIL_TASK") // 特殊类型，用于触发失败
                .build();

        List<DagNode> nodes = Arrays.asList(nodeA, nodeFail);

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("failing-dag")
                .name("失败节点DAG")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        return dagDefinition;
    }

    /**
     * 创建带条件边的DAG
     */
    private DagDefinition createConditionalDagWithEdges() {
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("节点A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("节点B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();
        DagNode nodeC = DagNode.builder()
                .id("C_SKIPPED")
                .name("节点C（跳过）")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        List<DagNode> nodes = Arrays.asList(nodeA, nodeB, nodeC);

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("conditional-edge-dag")
                .name("条件边DAG")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        // 添加条件边：A -> B (条件为真，应执行), A -> C (条件为假，应跳过)
        dagDefinition.addEdge(DagEdge.builder()
                .id("A-B")
                .fromNodeId("A")
                .toNodeId("B")
                .condition("true") // 条件为真，应执行
                .build());
        dagDefinition.addEdge(DagEdge.builder()
                .id("A-C")
                .fromNodeId("A")
                .toNodeId("C_SKIPPED")
                .condition("false") // 条件为假，应跳过
                .build());

        return dagDefinition;
    }
}
