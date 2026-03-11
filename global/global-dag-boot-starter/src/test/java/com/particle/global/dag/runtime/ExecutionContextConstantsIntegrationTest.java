package com.particle.global.dag.runtime;

import com.particle.global.dag.model.DagNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 执行上下文变量常量集成测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:29:00
 */
public class ExecutionContextConstantsIntegrationTest {

    @Test
    public void testConstantsUsageInExecutionContext() {
        // Create an execution context
        ExecutionContext context = new ExecutionContext();

        // Create a test node
        DagNode node = DagNode.builder()
                .id("test-node")
                .name("Test Node")
                .type("TASK")
                .build();

        String nodeId = node.getId();

        // Test setting and getting variables using constants
        String outputValue = "test output";
        context.setVariable(ExecutionContextConstants.getNodeOutputVariableName(nodeId), outputValue);

        Boolean executedValue = true;
        context.setVariable(ExecutionContextConstants.getNodeExecutedVariableName(nodeId), executedValue);

        String resultValue = "success result";
        context.setVariable(ExecutionContextConstants.getNodeResultVariableName(nodeId), resultValue);

        Boolean skippedValue = true;
        context.setVariable(ExecutionContextConstants.getNodeSkippedVariableName(nodeId), skippedValue);

        // Verify values can be retrieved correctly
        assertEquals(outputValue, context.getVariable(ExecutionContextConstants.getNodeOutputVariableName(nodeId)));
        assertEquals(executedValue, context.getVariable(ExecutionContextConstants.getNodeExecutedVariableName(nodeId)));
        assertEquals(resultValue, context.getVariable(ExecutionContextConstants.getNodeResultVariableName(nodeId)));
        assertEquals(skippedValue, context.getVariable(ExecutionContextConstants.getNodeSkippedVariableName(nodeId)));
    }

    @Test
    public void testConstantsConsistency() {
        String nodeId = "test-node";

        // Verify that the constants produce the expected variable names
        assertEquals("test-node", ExecutionContextConstants.getNodeOutputVariableName(nodeId));
        assertEquals("test-node_executed", ExecutionContextConstants.getNodeExecutedVariableName(nodeId));
        assertEquals("test-node_result", ExecutionContextConstants.getNodeResultVariableName(nodeId));
        assertEquals("test-node_skipped", ExecutionContextConstants.getNodeSkippedVariableName(nodeId));
    }

    @Test
    public void testVariableSuffixes() {
        assertEquals("_executed", ExecutionContextConstants.EXECUTED_SUFFIX);
        assertEquals("_result", ExecutionContextConstants.RESULT_SUFFIX);
        assertEquals("_skipped", ExecutionContextConstants.SKIPPED_SUFFIX);
    }
}