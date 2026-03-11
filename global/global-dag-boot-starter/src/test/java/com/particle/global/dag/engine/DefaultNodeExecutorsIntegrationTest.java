package com.particle.global.dag.engine;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 默认节点执行器集成测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:55:00
 */
public class DefaultNodeExecutorsIntegrationTest {

    private DagEngine dagEngine;

    @BeforeEach
    void setUp() {
        dagEngine = new DefaultDagEngine();
    }

    @Test
    void testHttpRequestNodeExecutorIntegration() {
        // Create a mock HTTP request configuration
        Map<String, Object> config = new HashMap<>();
        config.put("url", "https://httpbin.org/get");
        config.put("method", "GET");

        DagNode httpNode = DagNode.builder()
                .id("http-node")
                .name("HTTP Request Node")
                .type(NodeTypeConstants.HTTP)
                .config(config)
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("http-dag")
                .name("HTTP DAG")
                .nodes(Arrays.asList(httpNode))
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        waitForExecutionComplete(handle);

        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete");
        assertEquals(com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED, handle.getStatus(), "DAG should complete successfully");

        // Verify node execution
        var nodeExecution = context.getNodeExecution("http-node");
        assertNotNull(nodeExecution, "Node execution should be recorded");
        assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(), "HTTP node should execute successfully");
    }

    @Test
    void testGroovyScriptNodeExecutorIntegration() {
        // Create a simple Groovy script that returns a value
        Map<String, Object> config = new HashMap<>();
        config.put("script", "def result = ['message': 'Hello from groovy script', 'value': 42]; result;");

        DagNode scriptNode = DagNode.builder()
                .id("groovy-script-node")
                .name("Groovy Script Node")
                .type(NodeTypeConstants.GROOVY_SCRIPT)
                .config(config)
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("groovy-script-dag")
                .name("Groovy Script DAG")
                .nodes(Arrays.asList(scriptNode))
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        waitForExecutionComplete(handle);

        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete");
        assertEquals(com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED, handle.getStatus(), "DAG should complete successfully");

        // Verify node execution
        var nodeExecution = context.getNodeExecution("groovy-script-node");
        assertNotNull(nodeExecution, "Node execution should be recorded");
        assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(), "Groovy script node should execute successfully");
    }

    @Test
    void testDelayNodeExecutorIntegration() {
        Map<String, Object> config = new HashMap<>();
        config.put("delay", 50); // 50ms delay

        DagNode delayNode = DagNode.builder()
                .id("delay-node")
                .name("Delay Node")
                .type(NodeTypeConstants.DELAY)
                .config(config)
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("delay-dag")
                .name("Delay DAG")
                .nodes(Arrays.asList(delayNode))
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        long startTime = System.currentTimeMillis();
        waitForExecutionComplete(handle);
        long actualExecutionTime = System.currentTimeMillis() - startTime;

        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete");
        assertEquals(com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED, handle.getStatus(), "DAG should complete successfully");

        // The actual execution time should be at least the delay time (with some buffer for overhead)
        assertTrue(actualExecutionTime >= 40, "Execution should take at least the delay time (actual: " + actualExecutionTime + "ms)");

        // Verify node execution
        var nodeExecution = context.getNodeExecution("delay-node");
        assertNotNull(nodeExecution, "Node execution should be recorded");
        assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(), "Delay node should execute successfully");
    }

    @Test
    void testDataProcessNodeExecutorIntegration() {
        Map<String, Object> config = new HashMap<>();
        config.put("operation", "transform");
        config.put("input", Map.of("name", "test", "value", 100));
        config.put("expression", "uppercase");

        DagNode dataProcessNode = DagNode.builder()
                .id("data-process-node")
                .name("Data Process Node")
                .type(NodeTypeConstants.TRANSFORM)
                .config(config)
                .build();

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("data-process-dag")
                .name("Data Process DAG")
                .nodes(Arrays.asList(dataProcessNode))
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        waitForExecutionComplete(handle);

        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete");
        assertEquals(com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED, handle.getStatus(), "DAG should complete successfully");

        // Verify node execution
        var nodeExecution = context.getNodeExecution("data-process-node");
        assertNotNull(nodeExecution, "Node execution should be recorded");
        assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(), "Data process node should execute successfully");
    }

    // DatabaseNodeExecutor is not registered by default, so this test is not applicable
    // It requires explicit registration with a JdbcTemplate instance

    @Test
    void testComplexWorkflowWithMultipleExecutors() {
        // Create a complex DAG with multiple types of executors
        List<DagNode> nodes = new ArrayList<>();

        // Data Process node
        Map<String, Object> transformConfig = new HashMap<>();
        transformConfig.put("operation", "transform");
        transformConfig.put("input", Map.of("name", "test", "value", 100));
        transformConfig.put("expression", "uppercase");
        nodes.add(DagNode.builder()
                .id("transform-node")
                .name("Transform Node")
                .type(NodeTypeConstants.TRANSFORM)
                .config(transformConfig)
                .build());

        // Delay node
        Map<String, Object> delayConfig = new HashMap<>();
        delayConfig.put("delay", 50);
        nodes.add(DagNode.builder()
                .id("delay-node")
                .name("Delay Node")
                .type(NodeTypeConstants.DELAY)
                .config(delayConfig)
                .build());

        // Groovy Script node
        Map<String, Object> scriptConfig = new HashMap<>();
        scriptConfig.put("script", "def result = ['processed': true, 'timestamp': new Date().getTime()]; result;");
        nodes.add(DagNode.builder()
                .id("groovy-script-node")
                .name("Groovy Script Node")
                .type(NodeTypeConstants.GROOVY_SCRIPT)
                .config(scriptConfig)
                .build());

        // Add another delay node to maintain complexity
        Map<String, Object> delayConfig2 = new HashMap<>();
        delayConfig2.put("delay", 25);
        nodes.add(DagNode.builder()
                .id("delay-node-2")
                .name("Delay Node 2")
                .type(NodeTypeConstants.DELAY)
                .config(delayConfig2)
                .build());

        // Build DAG definition
        DagDefinition dagDefinition = DagDefinition.builder()
                .id("complex-workflow")
                .name("Complex Workflow")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        ExecutionContext context = new ExecutionContext();
        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        waitForExecutionComplete(handle);

        assertTrue(handle.getStatus().isFinal(), "Complex DAG execution should complete");
        assertEquals(com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED, handle.getStatus(), "Complex DAG should complete successfully");

        // Verify all nodes executed successfully
        for (DagNode node : nodes) {
            var nodeExecution = context.getNodeExecution(node.getId());
            assertNotNull(nodeExecution, "Node execution should be recorded for: " + node.getId());
            assertEquals(NodeExecutionStatus.SUCCESS, nodeExecution.getStatus(),
                "Node " + node.getId() + " should execute successfully");
        }
    }

    @Test
    void testExecutorRegistrationInEngine() {
        // Verify that all executors are registered in the engine
        var registry = dagEngine.getNodeExecutorRegistry();

        // Test nodes for each executor type (excluding DATABASE since it's not registered by default)
        DagNode[] testNodes = {
            DagNode.builder().id("http").name("HTTP").type(NodeTypeConstants.HTTP).build(),
            DagNode.builder().id("groovy-script").name("Groovy Script").type(NodeTypeConstants.GROOVY_SCRIPT).build(),
            DagNode.builder().id("delay").name("Delay").type(NodeTypeConstants.DELAY).build(),
            DagNode.builder().id("transform").name("Transform").type(NodeTypeConstants.TRANSFORM).build()
        };

        for (DagNode node : testNodes) {
            assertDoesNotThrow(() -> {
                var executor = registry.getExecutor(node);
                assertNotNull(executor, "Executor should be found for node type: " + node.getType());
            }, "Should not throw exception when getting executor for type: " + node.getType());
        }
    }

    private DagDefinition createSingleNodeDag(String nodeId, String dagName, String nodeType) {
        DagNode node = DagNode.builder()
                .id(nodeId)
                .name(nodeId + " Node")
                .type(nodeType)
                .build();

        return DagDefinition.builder()
                .id(nodeId + "-dag")
                .name(dagName)
                .nodes(Arrays.asList(node))
                .edges(new ArrayList<>())
                .build();
    }

    private void waitForExecutionComplete(ExecutionHandle handle) {
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}