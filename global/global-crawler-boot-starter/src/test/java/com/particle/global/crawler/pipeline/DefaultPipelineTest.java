package com.particle.global.crawler.pipeline;

import com.particle.global.crawler.action.ActionResult;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.action.browser.BrowserOpenAction;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.runtime.RuntimeContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * DefaultPipeline 测试类
 */
@DisplayName("DefaultPipeline 测试")
class DefaultPipelineTest {

    private DefaultPipeline pipeline;
    private RuntimeContext mockRuntimeContext;
    private CrawlDriver mockDriver;

    @BeforeEach
    void setUp() {
        pipeline = new DefaultPipeline();

        mockDriver = mock(CrawlDriver.class);
        mockRuntimeContext = mock(RuntimeContext.class);
        when(mockRuntimeContext.getDriver()).thenReturn(mockDriver);
        when(mockRuntimeContext.getVariables()).thenReturn(new ConcurrentHashMap<>());
    }

    @Nested
    @DisplayName("基本属性测试")
    class BasicPropertyTests {

        @Test
        @DisplayName("构造函数应正确设置名称")
        void shouldSetNameInConstructor() {
            DefaultPipeline namedPipeline = new DefaultPipeline("TestPipeline");

            assertEquals("TestPipeline", namedPipeline.getName());
        }

        @Test
        @DisplayName("name() 方法应返回 this")
        void nameMethodShouldReturnThis() {
            CrawlPipeline returned = pipeline.name("CrawlTestConstants");

            assertSame(pipeline, returned);
            assertEquals("CrawlTestConstants", pipeline.getName());
        }

        @Test
        @DisplayName("description() 方法应返回 this")
        void descriptionMethodShouldReturnThis() {
            CrawlPipeline returned = pipeline.description("CrawlTestConstants Description");

            assertSame(pipeline, returned);
            assertEquals("CrawlTestConstants Description", pipeline.getDescription());
        }
    }

    @Nested
    @DisplayName("Action 管理测试")
    class ActionManagementTests {

        @Test
        @DisplayName("addAction() 应添加 Action")
        void shouldAddAction() {
            CrawlAction action = mock(CrawlAction.class);
            pipeline.addAction(action);

            assertEquals(1, pipeline.getActions().size());
            assertSame(action, pipeline.getActions().get(0));
        }

        @Test
        @DisplayName("addAction() 应返回 this")
        void addActionShouldReturnThis() {
            CrawlAction action = mock(CrawlAction.class);
            CrawlPipeline returned = pipeline.addAction(action);

            assertSame(pipeline, returned);
        }

        @Test
        @DisplayName("应支持多个 Action")
        void shouldSupportMultipleActions() {
            pipeline.addAction(mock(CrawlAction.class));
            pipeline.addAction(mock(CrawlAction.class));
            pipeline.addAction(mock(CrawlAction.class));

            assertEquals(3, pipeline.getActions().size());
        }

        @Test
        @DisplayName("初始 Actions 列表不应为 null")
        void initialActionsShouldNotBeNull() {
            DefaultPipeline emptyPipeline = new DefaultPipeline();

            assertNotNull(emptyPipeline.getActions());
            assertTrue(emptyPipeline.getActions().isEmpty());
        }
    }

    @Nested
    @DisplayName("变量管理测试")
    class VariableManagementTests {

        @Test
        @DisplayName("variables() 应设置变量")
        void shouldSetVariables() {
            Map<String, Object> vars = new HashMap<>();
            vars.put("key1", "value1");

            pipeline.variables(vars);

            assertSame(vars, pipeline.getVariables());
        }

        @Test
        @DisplayName("variables() 应返回 this")
        void variablesShouldReturnThis() {
            CrawlPipeline returned = pipeline.variables(new HashMap<>());

            assertSame(pipeline, returned);
        }

        @Test
        @DisplayName("初始变量不应为 null")
        void initialVariablesShouldNotBeNull() {
            assertNotNull(pipeline.getVariables());
        }
    }
}
