package com.particle.crawler.client.execution.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 爬虫执行实例 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Data
@Schema
public class CrawlerExecutionExecuteVO extends CrawlerExecutionVO {

    @Schema(description = "结果数据")
    private Map<String,Object> resultData;
}
