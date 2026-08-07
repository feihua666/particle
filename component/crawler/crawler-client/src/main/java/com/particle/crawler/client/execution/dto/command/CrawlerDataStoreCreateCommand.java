package com.particle.crawler.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

/**
 * <p>
 * 爬虫结构数据存储 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Data
@Schema
public class CrawlerDataStoreCreateCommand extends AbstractBaseCommand {



    @Schema(description = "爬虫原始数据存储ID")
    private Long crawlerRawStoreId;


    @Schema(description = "爬虫执行实例ID")
    private Long crawlerExecutionId;


    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;


    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;


    @NotEmpty(message = "爬虫定义名称 不能为空")
        @Schema(description = "爬虫定义名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String crawlerDefinitionName;









}
