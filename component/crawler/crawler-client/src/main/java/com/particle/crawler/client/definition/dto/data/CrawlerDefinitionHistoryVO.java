package com.particle.crawler.client.definition.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 爬虫定义历史 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Data
@Schema
public class CrawlerDefinitionHistoryVO extends AbstractBaseIdVO {

    @Schema(description = "爬虫定义id")
    private Long crawlerDefinitionId;

    @TransBy(tableName = TransTableNameConstants.component_crawler_definition, byFieldName = "crawlerDefinitionId", mapValueField = "name")
    @Schema(description = "爬虫定义名称")
    private String crawlerDefinitionName;

    @Schema(description = "定义版本号")
    private Integer crawlerDefinitionVersion;

    @Schema(description = "流程图数据")
    private String definitionJson;

    @Schema(description = "爬虫级配置json")
    private String configJson;

    @Schema(description = "是否发布")
    private Boolean isPublish;



}
