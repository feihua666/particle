package com.particle.crawler.client.definition.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫定义 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Data
@Schema
public class CrawlerDefinitionVO extends AbstractBaseIdVO {

    @Schema(description = "爬虫名称")
    private String name;

    @Schema(description = "项目id")
    private Long crawlerProjectId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_project, byFieldName = "crawlerProjectId", mapValueField = "name")
    @Schema(description = "项目名称")
    private String crawlerProjectName;

    @Schema(description = "最新发布版本流程定义id")
    private Long latestPublishCrawlerDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_definition_history, byFieldName = "latestPublishCrawlerDefinitionHistoryId", mapValueField = "crawlerDefinitionVersion")
    @Schema(description = "最新发布版本流程定义版本")
    private String latestPublishCrawlerDefinitionHistoryVersion;

    @Schema(description = "草稿版本流程定义id")
    private Long draftCrawlerDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_definition_history, byFieldName = "draftCrawlerDefinitionHistoryId", mapValueField = "crawlerDefinitionVersion")
    @Schema(description = "草稿版本流程定义版本")
    private String draftCrawlerDefinitionHistoryVersion;

    @Schema(description = "描述")
    private String remark;



}
