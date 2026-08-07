package com.particle.crawler.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 爬虫定义 控制更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 14:34:19
 */
@Data
@Schema
public class CrawlerDefinitionUpdateControlCommand extends AbstractBaseUpdateCommand {


    @SetNullWhenNull
    @Schema(description = "最新发布版本流程定义id")
    private Long latestPublishCrawlerDefinitionHistoryId;

    @SetNullWhenNull
    @Schema(description = "草稿版本流程定义id")
    private Long draftCrawlerDefinitionHistoryId;

}
