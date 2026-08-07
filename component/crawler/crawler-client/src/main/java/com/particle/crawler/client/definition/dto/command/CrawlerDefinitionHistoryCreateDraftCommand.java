package com.particle.crawler.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 爬虫定义历史草稿 创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 20:47:01
 */
@Data
@Schema
public class CrawlerDefinitionHistoryCreateDraftCommand extends AbstractBaseCommand {

    @NotNull(message = "爬虫定义id 不能为空")
        @Schema(description = "爬虫定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crawlerDefinitionId;

    @Schema(description = "规则数据")
    private String definitionJson;


    @Schema(description = "爬虫级配置json")
    private String configJson;


    public static CrawlerDefinitionHistoryCreateDraftCommand create(Long crawlerDefinitionId,
                                                                     String graphDataJson,
                                                                     String configJson) {

        CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateCommand = new CrawlerDefinitionHistoryCreateDraftCommand();
        crawlerDefinitionHistoryCreateCommand.crawlerDefinitionId = crawlerDefinitionId;

        crawlerDefinitionHistoryCreateCommand.definitionJson = graphDataJson;
        crawlerDefinitionHistoryCreateCommand.configJson = configJson;
        return crawlerDefinitionHistoryCreateCommand;
    }

}
