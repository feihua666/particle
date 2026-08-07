package com.particle.crawler.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫定义历史 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Data
@Schema
public class CrawlerDefinitionHistoryCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "爬虫定义id 不能为空")
        @Schema(description = "爬虫定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionId;


    @NotNull(message = "定义版本号 不能为空")
        @Schema(description = "定义版本号",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer crawlerDefinitionVersion;


    @Schema(description = "流程图数据")
    private String definitionJson;


    @Schema(description = "爬虫级配置json")
    private String configJson;


    @NotNull(message = "是否发布 不能为空")
        @Schema(description = "是否发布",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublish;









}
