package com.particle.crawler.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫结构数据存储内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Data
@Schema
public class CrawlerDataStoreContentCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "爬虫原始数据存储ID 不能为空")
        @Schema(description = "爬虫原始数据存储ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDataStoreId;


    @Schema(description = "存储内容文本")
    private String content;









}
