package com.particle.crawler.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫定义 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Data
@Schema
public class CrawlerDefinitionUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "爬虫名称 不能为空")
        @Schema(description = "爬虫名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "项目id 不能为空")
        @Schema(description = "项目id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerProjectId;


    @Schema(description = "最新发布版本爬虫定义id")
    private Long latestPublishCrawlerDefinitionHistoryId;


    @Schema(description = "草稿版本爬虫定义id")
    private Long draftCrawlerDefinitionHistoryId;


    @Schema(description = "描述")
    private String remark;









}
