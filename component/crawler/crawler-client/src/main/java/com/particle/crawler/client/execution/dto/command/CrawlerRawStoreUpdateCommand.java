package com.particle.crawler.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫原始数据存储 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Data
@Schema
public class CrawlerRawStoreUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "爬虫执行实例ID")
    private Long crawlerExecutionId;


    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;


    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;


    @NotEmpty(message = "爬虫定义名称 不能为空")
        @Schema(description = "爬虫定义名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String crawlerDefinitionName;


    @Schema(description = "页面地址")
    private String pageUrl;


    @Schema(description = "页面标题")
    private String pageTitle;


    @NotNull(message = "状态字典id：stored/processed/processing 不能为空")
        @Schema(description = "状态字典id：stored/processed/processing",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;









}
