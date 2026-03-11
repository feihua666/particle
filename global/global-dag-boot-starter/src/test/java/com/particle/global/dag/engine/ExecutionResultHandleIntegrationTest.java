package com.particle.global.dag.engine;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 执行结果和执行句柄集成测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 14:45:00
 */
public class ExecutionResultHandleIntegrationTest {

    private DagEngine dagEngine;

    @BeforeEach
    void setUp() {
        dagEngine = new DefaultDagEngine();
    }

    @Test
    void testExecutionResultFromHandle() {
        // 创建简单的DAG
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("Node A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("Node B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("integration-test-dag")
                .name("Integration Test DAG")
                .nodes(Arrays.asList(nodeA, nodeB))
                .edges(new ArrayList<>())
                .build();

        // 添加边：A -> B
        dagDefinition.addEdge(new com.particle.global.dag.model.DagEdge(
                "A-B", "A", "B", null, null, 0, null));

        ExecutionContext context = new ExecutionContext();

        // 异步执行DAG并获取句柄
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        assertNotNull(handle, "Execution handle should not be null");

        // 等待执行完成并获取结果
        ExecutionResult result = handle.awaitResult();

        assertNotNull(result, "Execution result should not be null");
        assertTrue(result.isSuccessful(), "Execution should be successful");
        assertEquals(handle.getExecutionId(), result.getExecutionId(), "Execution ID should match");
    }

    @Test
    void testSynchronousExecutionWithResult() {
        // 创建DAG
        DagNode nodeA = DagNode.builder()
                .id("A")
                .name("Node A")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        DagNode nodeB = DagNode.builder()
                .id("B")
                .name("Node B")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("sync-test-dag")
                .name("Sync Test DAG")
                .nodes(Arrays.asList(nodeA, nodeB))
                .edges(new ArrayList<>())
                .build();

        // 添加边：A -> B
        dagDefinition.addEdge(new com.particle.global.dag.model.DagEdge(
                "A-B", "A", "B", null, null, 0, null));

        ExecutionContext context = new ExecutionContext();

        // 使用同步执行方法获取结果
        DefaultDagEngine defaultDagEngine = (DefaultDagEngine) dagEngine;
        ExecutionResult result = defaultDagEngine.executeAndWait(dagDefinition, context);

        assertNotNull(result, "Execution result should not be null");
        assertTrue(result.isSuccessful(), "Execution should be successful");
        assertNotNull(result.getStartTime(), "Start time should be set");
        assertNotNull(result.getEndTime(), "End time should be set");
        assertTrue(result.getDurationInMs() >= 0, "Duration should be non-negative");
    }

    @Test
    void testExecutionResultMethods() {
        // 创建DAG
        DagNode node = DagNode.builder()
                .id("single-node")
                .name("Single Node")
                .type(NodeTypeConstants.DELAY)
                .config(Map.of("delay", 10)) // 10ms delay
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("result-methods-test")
                .name("Result Methods Test")
                .nodes(Arrays.asList(node))
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();

        DefaultDagEngine defaultDagEngine = (DefaultDagEngine) dagEngine;
        ExecutionResult result = defaultDagEngine.executeAndWait(dagDefinition, context);

        assertNotNull(result, "Execution result should not be null");

        // 测试各种方法
        assertNotNull(result.getExecutionId(), "Execution ID should be set");
        assertTrue(result.isSuccessful(), "Execution should be successful");
        assertFalse(result.isFailed(), "Execution should not be failed");
        assertFalse(result.isStopped(), "Execution should not be stopped");
        assertNotNull(result.getContext(), "Context should be set");
    }
}
