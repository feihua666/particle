package com.particle.global.crawler.pipeline;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/5/14 16:13
 */
public class PipelineBuild {

    public static void main(String[] args) {
        CrawlPipeline pipeline = PipelineBuilder.create("测试 pipeline")
                .open("https://chat.deepseek.com")
                .input("textarea[name=\"search\"]", "今天天气")
                .enter(null)
                .build();

        System.out.println(pipeline.toJson());
    }
}
