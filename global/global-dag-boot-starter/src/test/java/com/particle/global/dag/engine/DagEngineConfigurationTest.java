package com.particle.global.dag.engine;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.runtime.executor.NodeExecutorRegistry;
import com.particle.global.dag.runtime.NodeExecutionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * DAG引擎配置测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class DagEngineConfigurationTest {

    private DagEngine dagEngine;

    @BeforeEach
    void setUp() {
        dagEngine = new DefaultDagEngine();
    }

    @Test
    void testGetNodeExecutorRegistry() {
        // Test getting the node executor registry
        NodeExecutorRegistry registry = dagEngine.getNodeExecutorRegistry();

        assertNotNull(registry, "NodeExecutorRegistry should not be null");

        // Verify that a default executor is registered
        DagNode delayNode = DagNode.builder()
                .id("test-node")
                .name("Test Node")
                .type(NodeTypeConstants.DELAY) // This should be supported by DelayNodeExecutor
                .build();

        // This should not throw an exception, meaning a suitable executor exists
        assertDoesNotThrow(() -> {
            NodeExecutor executor = registry.getExecutor(delayNode);
            assertNotNull(executor, "Executor should be found for DELAY type node");
        });
    }

    @Test
    void testSetCustomExecutorService() {
        // Create a custom executor service
        var customExecutorService = Executors.newFixedThreadPool(2);

        // Cast to DefaultDagEngine to access the setter
        DefaultDagEngine defaultDagEngine = (DefaultDagEngine) dagEngine;

        // Set the custom executor service
        defaultDagEngine.setExecutorService(customExecutorService);

        // Verify that the engine still works correctly with custom executor
        DagDefinition dagDefinition = createSimpleDag();
        ExecutionContext context = new ExecutionContext();

        ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

        // Wait for execution to complete
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        assertTrue(handle.getStatus().isFinal(), "DAG execution should complete within timeout");
    }

    @Test
    void testGetExecutionHandleReturnsNull() {
        // Since the engine no longer maintains a registry of execution handles,
        // the getExecutionHandle method returns null for all inputs
        ExecutionHandle nullHandle = dagEngine.getExecutionHandle(null);
        assertNull(nullHandle, "Should return null for null execution ID");

        // Test with a non-existent ID
        ExecutionHandle nonExistentHandle = dagEngine.getExecutionHandle("any-id");
        assertNull(nonExistentHandle, "Should return null for any execution ID");

        // Execute a DAG
        DagDefinition dagDefinition = createSimpleDag();
        ExecutionContext context = new ExecutionContext();
        ExecutionHandle initialHandle = dagEngine.execute(dagDefinition, context);

        // Even with a valid execution running, getExecutionHandle should return null
        // because the engine doesn't maintain an internal registry
        ExecutionHandle retrievedHandle = dagEngine.getExecutionHandle(initialHandle.getExecutionId());
        assertNull(retrievedHandle, "Should return null even for active execution ID");
    }

    @Test
    void testRegisterCustomNodeExecutor() {
        // Get the registry and register a custom executor
        NodeExecutorRegistry registry = dagEngine.getNodeExecutorRegistry();

        // Register a custom executor for a new node type
        registry.register(new CustomNodeExecutor());

        // Verify the custom executor is available
        DagNode customNode = DagNode.builder()
                .id("custom-node")
                .name("Custom Node")
                .type("CUSTOM") // This should be supported by CustomNodeExecutor
                .build();

        assertDoesNotThrow(() -> {
            NodeExecutor executor = registry.getExecutor(customNode);
            assertNotNull(executor, "Custom executor should be found for CUSTOM type node");
            assertTrue(executor instanceof CustomNodeExecutor, "Should return CustomNodeExecutor");
        });
    }

    /**
     * Creates a simple linear DAG for testing
     */
    private DagDefinition createSimpleDag() {
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

        List<DagNode> nodes = Arrays.asList(nodeA, nodeB);

        DagDefinition dagDefinition = DagDefinition.builder()
                .id("simple-dag")
                .name("简单DAG")
                .nodes(nodes)
                .edges(new ArrayList<>())
                .build();

        // Add edge: A -> B
        dagDefinition.addEdge(DagEdge.builder()
                .id("A-B")
                .fromNodeId("A")
                .toNodeId("B")
                .build());

        return dagDefinition;
    }

    /**
     * Custom node executor for testing
     */
    private static class CustomNodeExecutor implements NodeExecutor {
        @Override
        public boolean supports(DagNode node) {
            return "CUSTOM".equals(node.getType());
        }

        @Override
        public NodeExecutionResult execute(DagNode node, ExecutionContext context) {
            // Simulate execution
            try {
                Thread.sleep(100); // Simulate work
                return NodeExecutionResult.success("Custom execution result for " + node.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return NodeExecutionResult.failure(e);
            }
        }
    }
}
