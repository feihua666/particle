package com.particle.global.crawler.pipeline;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.action.browser.BrowserClickAction;
import com.particle.global.crawler.action.browser.BrowserOpenAction;
import com.particle.global.crawler.action.extract.ExtractTextAction;
import com.particle.global.crawler.action.flow.DelayAction;
import com.particle.global.crawler.storage.data.DataStorage;
import com.particle.global.crawler.storage.raw.RawStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * DefaultPipeline 测试类
 */
@DisplayName("DefaultPipeline json 测试")
class DefaultPipelineJsonTest {

    private CrawlPipeline testPipeline;

    @BeforeEach
    void setUp() {
        // 创建 Mock Storage（仅用于测试，实际不会使用）
        RawStorage mockRawStorage = mock(RawStorage.class);
        DataStorage mockDataStorage = mock(DataStorage.class);

        // 使用 PipelineBuilder 构建包含所有 Action 类型的 Pipeline
        testPipeline = PipelineBuilder.create("全功能测试Pipeline")
                .description("测试所有 Action 类型的序列化和反序列化")
                .variable("baseUrl", "https://example.com")
                .variable("searchKeyword", "测试关键词")

                // ==================== 浏览器操作 ====================
                .open("${baseUrl}")
                .newTab("https://www.baidu.com")
                .newTab()
                .closeTab()
                .click("#search-button", 3000)
                .input("#search-input", "${searchKeyword}", 5000)
                .hover(".menu-item", 2000)
                .scroll(500)
                .scrollToBottom()
                .enter("#search-input")
                .pressKey("Escape")

                // ==================== 数据提取 ====================
                .extractText(".title", "pageTitle")
                .extractHtml(".content", "pageContent")
                .extractAttr(".link", "href", "linkUrl")
                .extractTitle("extractedTitle")
                // ==================== 流程控制 ====================
                .delay(1000)
                .retry(3, 2000,
                    BrowserClickAction.create("#retry-button"),
                    DelayAction.create(500L)
                )
                .loop("itemList",
                    ExtractTextAction.create(".item-title", "currentTitle")
                )
                .conditional("${pageTitle} != null",
                    BrowserClickAction.create(".next-button"),
                    BrowserOpenAction.create("${baseUrl}")
                )
                .breakLoop()
                .continueLoop()

                // ==================== 数据存储 ====================
                // .storeRaw(mockRawStorage, "rawData")
                // .storeData(mockDataStorage, "structuredData")

                .close()
                .build();
    }

    @Nested
    @DisplayName("验证序列化和反序列化一致")
    class ValidateTests {

        @Test
        @DisplayName("验证全功能 Pipeline 的序列化和反序列化一致性")
        void testFullPipelineSerialization() throws JsonProcessingException {
            // 1. 序列化 Pipeline 为 JSON
            String originalJson = testPipeline.toJson();
            System.out.println("原始 JSON:");
            System.out.println(originalJson);
            System.out.println();

            // 2. 从 JSON 反序列化为新的 Pipeline
            CrawlPipeline deserializedPipeline = PipelineBuilder.fromJson(originalJson);
            assertNotNull(deserializedPipeline, "反序列化的 Pipeline 不应为 null");

            // 3. 将反序列化后的 Pipeline 再次序列化
            String reserializedJson = deserializedPipeline.toJson();
            System.out.println("重新序列化 JSON:");
            System.out.println(reserializedJson);
            System.out.println();

            // 4. 验证两次序列化的 JSON 是否一致
            assertEquals(originalJson, reserializedJson,
                "序列化和反序列化后的 JSON 应该完全一致");

            // 5. 验证 Pipeline 基本属性
            assertEquals(testPipeline.getName(), deserializedPipeline.getName(),
                "Pipeline 名称应该一致");
            assertEquals(testPipeline.getDescription(), deserializedPipeline.getDescription(),
                "Pipeline 描述应该一致");
            assertEquals(testPipeline.getActions().size(), deserializedPipeline.getActions().size(),
                "Action 数量应该一致");

            // 6. 验证每个 Action 的类型
            List<CrawlAction> originalActions = testPipeline.getActions();
            List<CrawlAction> deserializedActions = deserializedPipeline.getActions();

            for (int i = 0; i < originalActions.size(); i++) {
                CrawlAction originalAction = originalActions.get(i);
                CrawlAction deserializedAction = deserializedActions.get(i);

                assertEquals(originalAction.getType(), deserializedAction.getType(),
                    String.format("第 %d 个 Action 的 type 应该一致", i + 1));
            }

            System.out.println("✓ 验证通过：序列化和反序列化完全一致");
            System.out.println("✓ Action 总数: " + originalActions.size());
        }
    }
}
