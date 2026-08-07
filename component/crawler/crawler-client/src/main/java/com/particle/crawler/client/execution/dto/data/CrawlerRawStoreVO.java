package com.particle.crawler.client.execution.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 爬虫原始数据存储 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Data
@Schema
public class CrawlerRawStoreVO extends AbstractBaseIdVO {

    @Schema(description = "爬虫执行实例ID")
    private Long crawlerExecutionId;

    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_definition, byFieldName = "crawlerDefinitionId", mapValueField = "name")
    @Schema(description = "爬虫定义名称")
    private String crawlerDefinitionIdName;

    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_definition_history, byFieldName = "crawlerDefinitionHistoryId", mapValueField = "crawlerDefinitionVersion")
    @Schema(description = "执行时使用的版本号")
    private String crawlerDefinitionHistoryVersion;

    @Schema(description = "爬虫定义名称")
    private String crawlerDefinitionName;

    @Schema(description = "页面地址")
    private String pageUrl;

    @Schema(description = "页面标题")
    private String pageTitle;

    @Schema(description = "状态字典id")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "状态字典名称")
    private String statusDictName;

    @Schema(description = "创建时间")
    private LocalDateTime createAt;

}
