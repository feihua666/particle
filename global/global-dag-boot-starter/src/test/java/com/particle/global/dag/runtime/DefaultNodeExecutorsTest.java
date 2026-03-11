package com.particle.global.dag.runtime;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.engine.DagEngine;
import com.particle.global.dag.engine.DefaultDagEngine;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.executor.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 默认节点执行器测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:51:00
 */
public class DefaultNodeExecutorsTest {

    private DagEngine dagEngine;

    @BeforeEach
    void setUp() {
        dagEngine = new DefaultDagEngine();
    }

    @Test
    void testHttpRequestNodeExecutorSupports() {
        NodeExecutor executor = new HttpRequestNodeExecutor();

        DagNode httpNode = DagNode.builder().id("test").name("Test").type(NodeTypeConstants.HTTP).build();
        assertTrue(executor.supports(httpNode), "Should support HTTP type");

        DagNode otherNode = DagNode.builder().id("test").name("Test").type("OTHER").build();
        assertFalse(executor.supports(otherNode), "Should not support OTHER type");

        // Test that it only supports HTTP type
        DagNode httpRequestNode = DagNode.builder().id("test").name("Test").type("HTTP_REQUEST").build();
        assertFalse(executor.supports(httpRequestNode), "Should not support HTTP_REQUEST type");

        DagNode apiCallNode = DagNode.builder().id("test").name("Test").type("API_CALL").build();
        assertFalse(executor.supports(apiCallNode), "Should not support API_CALL type");
    }

    @Test
    void testGroovyScriptNodeExecutorSupports() {
        NodeExecutor executor = new GroovyScriptNodeExecutor();

        DagNode groovyScriptNode = DagNode.builder().id("test").name("Test").type(NodeTypeConstants.GROOVY_SCRIPT).build();
        assertTrue(executor.supports(groovyScriptNode), "Should support GROOVY_SCRIPT type");

        DagNode otherNode = DagNode.builder().id("test").name("Test").type("OTHER").build();
        assertFalse(executor.supports(otherNode), "Should not support OTHER type");

        // Test that it only supports GROOVY_SCRIPT type
        DagNode scriptNode = DagNode.builder().id("test").name("Test").type("SCRIPT").build();
        assertFalse(executor.supports(scriptNode), "Should not support SCRIPT type");

        DagNode groovyNode = DagNode.builder().id("test").name("Test").type("GROOVY").build();
        assertFalse(executor.supports(groovyNode), "Should not support GROOVY type");
    }

    @Test
    void testDelayNodeExecutorSupports() {
        NodeExecutor executor = new DelayNodeExecutor();

        DagNode delayNode = DagNode.builder().id("test").name("Test").type(NodeTypeConstants.DELAY).build();
        assertTrue(executor.supports(delayNode), "Should support DELAY type");

        DagNode otherNode = DagNode.builder().id("test").name("Test").type("OTHER").build();
        assertFalse(executor.supports(otherNode), "Should not support OTHER type");

        // Test that it only supports DELAY type
        DagNode sleepNode = DagNode.builder().id("test").name("Test").type("SLEEP").build();
        assertFalse(executor.supports(sleepNode), "Should not support SLEEP type");

        DagNode waitNode = DagNode.builder().id("test").name("Test").type("WAIT").build();
        assertFalse(executor.supports(waitNode), "Should not support WAIT type");
    }

    @Test
    void testDatabaseNodeExecutorSupports() {
        // DatabaseNodeExecutor requires a JdbcTemplate instance, so we'll test the supports method
        // by creating a minimal instance without JdbcTemplate for testing purposes only
        // In practice, DatabaseNodeExecutor should be created with a JdbcTemplate

        // For this test, we'll create a test-specific instance that bypasses the JdbcTemplate requirement
        // for supports() method testing, since supports() doesn't need the JdbcTemplate
        NodeExecutor executor = new DatabaseNodeExecutor(null) {
            // Override execute to prevent actual execution that would fail without JdbcTemplate
            @Override
            public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
                return NodeExecutionResult.success("Test result");
            }
        };

        DagNode dbNode = DagNode.builder().id("test").name("Test").type(NodeTypeConstants.DATABASE).build();
        assertTrue(executor.supports(dbNode), "Should support DATABASE type");

        DagNode otherNode = DagNode.builder().id("test").name("Test").type("OTHER").build();
        assertFalse(executor.supports(otherNode), "Should not support OTHER type");

        // Test that it only supports DATABASE type
        DagNode sqlNode = DagNode.builder().id("test").name("Test").type("SQL").build();
        assertFalse(executor.supports(sqlNode), "Should not support SQL type");

        DagNode queryNode = DagNode.builder().id("test").name("Test").type("QUERY").build();
        assertFalse(executor.supports(queryNode), "Should not support QUERY type");
    }

    @Test
    void testDataProcessNodeExecutorSupports() {
        NodeExecutor executor = new DataProcessNodeExecutor();

        DagNode transformNode = DagNode.builder().id("test").name("Test").type(NodeTypeConstants.TRANSFORM).build();
        assertTrue(executor.supports(transformNode), "Should support TRANSFORM type");

        DagNode otherNode = DagNode.builder().id("test").name("Test").type("OTHER").build();
        assertFalse(executor.supports(otherNode), "Should not support OTHER type");

        // Test that it only supports TRANSFORM type
        DagNode filterNode = DagNode.builder().id("test").name("Test").type("FILTER").build();
        assertFalse(executor.supports(filterNode), "Should not support FILTER type");

        DagNode aggregateNode = DagNode.builder().id("test").name("Test").type("AGGREGATE").build();
        assertFalse(executor.supports(aggregateNode), "Should not support AGGREGATE type");

        DagNode mapNode = DagNode.builder().id("test").name("Test").type("MAP").build();
        assertFalse(executor.supports(mapNode), "Should not support MAP type");
    }

    @Test
    void testAllExecutorsRegisteredInDefaultEngine() {
        NodeExecutorRegistry registry = dagEngine.getNodeExecutorRegistry();

        // Test that all executors (except DatabaseNodeExecutor which is not registered by default) can be found for their respective types
        DagNode[] testNodes = {
            DagNode.builder().id("http").name("HTTP").type(NodeTypeConstants.HTTP).build(),
            DagNode.builder().id("groovy-script").name("Groovy Script").type(NodeTypeConstants.GROOVY_SCRIPT).build(),
            DagNode.builder().id("delay").name("Delay").type(NodeTypeConstants.DELAY).build(),
            DagNode.builder().id("transform").name("Transform").type(NodeTypeConstants.TRANSFORM).build()
        };

        for (DagNode node : testNodes) {
            assertDoesNotThrow(() -> {
                NodeExecutor executor = registry.getExecutor(node);
                assertNotNull(executor, "Should find executor for type: " + node.getType());
            }, "Should not throw exception when getting executor for type: " + node.getType());
        }

        // Test that DatabaseNodeExecutor is NOT registered by default
        DagNode databaseNode = DagNode.builder().id("database").name("Database").type(NodeTypeConstants.DATABASE).build();
        assertThrows(IllegalArgumentException.class, () -> {
            registry.getExecutor(databaseNode);
        }, "DatabaseNodeExecutor should not be registered by default");
    }
}
