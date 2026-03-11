package com.particle.global.dag.runtime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 执行上下文变量常量测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:26:00
 */
public class ExecutionContextConstantsTest {

    @Test
    public void testConstantsValues() {
        assertEquals("_executed", ExecutionContextConstants.EXECUTED_SUFFIX);
        assertEquals("_result", ExecutionContextConstants.RESULT_SUFFIX);
        assertEquals("_skipped", ExecutionContextConstants.SKIPPED_SUFFIX);
    }

    @Test
    public void testGetNodeVariableNames() {
        String nodeId = "testNode";

        assertEquals("testNode", ExecutionContextConstants.getNodeOutputVariableName(nodeId));
        assertEquals("testNode_executed", ExecutionContextConstants.getNodeExecutedVariableName(nodeId));
        assertEquals("testNode_result", ExecutionContextConstants.getNodeResultVariableName(nodeId));
        assertEquals("testNode_skipped", ExecutionContextConstants.getNodeSkippedVariableName(nodeId));
    }

    @Test
    public void testGetNodeVariableNamesWithSpecialCharacters() {
        String nodeId = "node-with.special.chars_123";

        assertEquals("node-with.special.chars_123_executed", ExecutionContextConstants.getNodeExecutedVariableName(nodeId));
        assertEquals("node-with.special.chars_123_result", ExecutionContextConstants.getNodeResultVariableName(nodeId));
        assertEquals("node-with.special.chars_123_skipped", ExecutionContextConstants.getNodeSkippedVariableName(nodeId));
    }

    @Test
    public void testNullNodeId() {
        // Test with null node ID - should not throw exception
        assertDoesNotThrow(() -> {
            ExecutionContextConstants.getNodeExecutedVariableName(null);
            ExecutionContextConstants.getNodeResultVariableName(null);
            ExecutionContextConstants.getNodeSkippedVariableName(null);
        });
    }
}