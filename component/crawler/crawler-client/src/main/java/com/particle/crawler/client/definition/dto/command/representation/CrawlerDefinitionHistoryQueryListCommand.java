package com.particle.crawler.client.definition.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫定义历史 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Data
@Schema
public class CrawlerDefinitionHistoryQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "爬虫定义id")
    private Long crawlerDefinitionId;


    @Schema(description = "定义版本号")
    private Integer crawlerDefinitionVersion;


    @Schema(description = "流程图数据")
    private String definitionJson;


    @Schema(description = "爬虫级配置json")
    private String configJson;


    @Schema(description = "是否发布")
    private Boolean isPublish;









}
