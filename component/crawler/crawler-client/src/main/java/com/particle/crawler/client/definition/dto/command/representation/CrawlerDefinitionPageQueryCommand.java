package com.particle.crawler.client.definition.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫定义 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Data
@Schema
public class CrawlerDefinitionPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like
    @Schema(description = "爬虫名称，左前缀匹配")
    private String name;


    @Schema(description = "项目id")
    private Long crawlerProjectId;


    @Schema(description = "最新发布版本爬虫定义id")
    private Long latestPublishCrawlerDefinitionHistoryId;


    @Schema(description = "草稿版本爬虫定义id")
    private Long draftCrawlerDefinitionHistoryId;










}
