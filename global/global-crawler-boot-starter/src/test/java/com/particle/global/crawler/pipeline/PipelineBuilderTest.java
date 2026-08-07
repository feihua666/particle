package com.particle.global.crawler.pipeline;

import com.particle.global.crawler.action.browser.BrowserClickAction;
import com.particle.global.crawler.action.browser.BrowserInputAction;
import com.particle.global.crawler.action.browser.BrowserOpenAction;
import com.particle.global.crawler.action.browser.BrowserScrollAction;
import com.particle.global.crawler.action.extract.ExtractAttrAction;
import com.particle.global.crawler.action.extract.ExtractHtmlAction;
import com.particle.global.crawler.action.extract.ExtractTextAction;
import com.particle.global.crawler.action.flow.DelayAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PipelineBuilder 测试类
 */
@DisplayName("PipelineBuilder 测试")
class PipelineBuilderTest {

    @Nested
    @DisplayName("创建测试")
    class CreateTests {

        @Test
        @DisplayName("create() 应创建新实例")
        void shouldCreateNewInstance() {
            PipelineBuilder builder = PipelineBuilder.create();

            assertNotNull(builder);
        }

        @Test
        @DisplayName("create(name) 应设置名称")
        void shouldCreateWithName() {
            PipelineBuilder builder = PipelineBuilder.create("MyPipeline");

            assertEquals("MyPipeline", builder.getPipeline().getName());
        }
    }

    @Nested
    @DisplayName("名称和描述测试")
    class NameAndDescriptionTests {

        @Test
        @DisplayName("name() 应设置名称")
        void shouldSetName() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .name("TestPipeline")
                    .build();

            assertEquals("TestPipeline", pipeline.getName());
        }

        @Test
        @DisplayName("description() 应设置描述")
        void shouldSetDescription() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .description("CrawlTestConstants Description")
                    .build();

            assertEquals("CrawlTestConstants Description", pipeline.getDescription());
        }

        @Test
        @DisplayName("应支持链式调用")
        void shouldSupportChainCall() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .name("CrawlTestConstants")
                    .description("Desc")
                    .build();

            assertEquals("CrawlTestConstants", pipeline.getName());
            assertEquals("Desc", pipeline.getDescription());
        }
    }

    @Nested
    @DisplayName("变量测试")
    class VariableTests {

        @Test
        @DisplayName("variable() 应添加单个变量")
        void shouldAddSingleVariable() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .variable("key1", "value1")
                    .variable("key2", 123)
                    .build();

            Map<String, Object> vars = pipeline.getVariables();
            assertEquals("value1", vars.get("key1"));
            assertEquals(123, vars.get("key2"));
        }

        @Test
        @DisplayName("variables() 应设置多个变量")
        void shouldSetMultipleVariables() {
            Map<String, Object> vars = Map.of("key1", "value1", "key2", "value2");

            CrawlPipeline pipeline = PipelineBuilder.create()
                    .variables(vars)
                    .build();

            assertEquals(vars, pipeline.getVariables());
        }
    }

    @Nested
    @DisplayName("浏览器操作测试")
    class BrowserActionTests {

        @Test
        @DisplayName("open() 应添加 BrowserOpenAction")
        void openShouldAddBrowserOpenAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .open("https://example.com")
                    .build();

            assertEquals(1, pipeline.getActions().size());
            assertInstanceOf(BrowserOpenAction.class, pipeline.getActions().get(0));
        }

        @Test
        @DisplayName("click() 应添加 BrowserClickAction")
        void clickShouldAddBrowserClickAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .click(".btn")
                    .build();

            BrowserClickAction action = (BrowserClickAction) pipeline.getActions().get(0);
            assertEquals(".btn", action.getSelector());
            assertEquals(5000, action.getTimeout());
        }

        @Test
        @DisplayName("click() 带超时参数")
        void clickWithTimeoutShouldWork() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .click(".btn", 3000)
                    .build();

            BrowserClickAction action = (BrowserClickAction) pipeline.getActions().get(0);
            assertEquals(3000, action.getTimeout());
        }

        @Test
        @DisplayName("input() 应添加 BrowserInputAction")
        void inputShouldAddBrowserInputAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .input(".search", "keyword")
                    .build();

            BrowserInputAction action = (BrowserInputAction) pipeline.getActions().get(0);
            assertEquals(".search", action.getSelector());
            assertEquals("keyword", action.getText());
        }

        @Test
        @DisplayName("hover() 应添加 BrowserHoverAction")
        void hoverShouldAddBrowserHoverAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .hover(".menu")
                    .build();

            assertEquals(1, pipeline.getActions().size());
        }

        @Test
        @DisplayName("scroll() 应添加 BrowserScrollAction")
        void scrollShouldAddBrowserScrollAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .scroll(500)
                    .build();

            BrowserScrollAction action = (BrowserScrollAction) pipeline.getActions().get(0);
            assertEquals(500, action.getPixels());
        }

        @Test
        @DisplayName("scrollToBottom() 应滚动到最大像素")
        void scrollToBottomShouldScrollMaxPixels() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .scrollToBottom()
                    .build();

            BrowserScrollAction action = (BrowserScrollAction) pipeline.getActions().get(0);
            assertEquals(Integer.MAX_VALUE, action.getPixels());
        }
    }

    @Nested
    @DisplayName("提取操作测试")
    class ExtractActionTests {

        @Test
        @DisplayName("extractText() 应添加 ExtractTextAction")
        void extractTextShouldAddExtractTextAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .extractText(".title")
                    .build();

            ExtractTextAction action = (ExtractTextAction) pipeline.getActions().get(0);
            assertEquals(".title", action.getSelector());
        }

        @Test
        @DisplayName("extractText() 带变量名")
        void extractTextWithVariableShouldWork() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .extractText(".title", "myTitle")
                    .build();

            ExtractTextAction action = (ExtractTextAction) pipeline.getActions().get(0);
            assertEquals("myTitle", action.getResultKey());
        }

        @Test
        @DisplayName("extractHtml() 应添加 ExtractHtmlAction")
        void extractHtmlShouldAddExtractHtmlAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .extractHtml(".content")
                    .build();

            assertInstanceOf(ExtractHtmlAction.class, pipeline.getActions().get(0));
        }

        @Test
        @DisplayName("extractAttr() 应添加 ExtractAttrAction")
        void extractAttrShouldAddExtractAttrAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .extractAttr(".img", "src")
                    .build();

            ExtractAttrAction action = (ExtractAttrAction) pipeline.getActions().get(0);
            assertEquals(".img", action.getSelector());
            assertEquals("src", action.getAttrName());
        }
    }

    @Nested
    @DisplayName("流程控制测试")
    class FlowControlTests {

        @Test
        @DisplayName("delay() 应添加 DelayAction")
        void delayShouldAddDelayAction() {
            CrawlPipeline pipeline = PipelineBuilder.create()
                    .delay(1000)
                    .build();

            DelayAction action = (DelayAction) pipeline.getActions().get(0);
            assertEquals(1000, action.getMillis());
        }
    }

    @Nested
    @DisplayName("完整构建测试")
    class CompleteBuildTests {

        @Test
        @DisplayName("应能构建复杂 Pipeline")
        void shouldBuildComplexPipeline() {
            CrawlPipeline pipeline = PipelineBuilder.create("ProductSpider")
                    .description("商品爬虫")
                    .open("https://example.com/products")
                    .click(".category-btn")
                    .input(".search-input", "手机")
                    .click(".search-btn")
                    .delay(1000)
                    .scrollToBottom()
                    .delay(500)
                    // .store() 已移除，需要使用 storeRaw 或 storeData 并传入 Storage 实例
                    .build();

            assertEquals("ProductSpider", pipeline.getName());
            assertEquals("商品爬虫", pipeline.getDescription());
            assertTrue(pipeline.getActions().size() >= 8);
        }

        @Test
        @DisplayName("build() 应返回正确类型")
        void buildShouldReturnCorrectType() {
            CrawlPipeline pipeline = PipelineBuilder.create().build();

            assertInstanceOf(DefaultPipeline.class, pipeline);
        }
    }
}
