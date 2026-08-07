package com.particle.crawler.client.execution.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫原始数据存储 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Data
@Schema
public class CrawlerRawStorePageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "爬虫执行实例ID")
    private Long crawlerExecutionId;


    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;


    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;


    @Like
    @Schema(description = "爬虫定义名称，左前缀匹配")
    private String crawlerDefinitionName;

    @Like
    @Schema(description = "页面地址，左前缀匹配")
    private String pageUrl;

    @Like
    @Schema(description = "页面标题，左前缀匹配")
    private String pageTitle;


    @Schema(description = "状态字典id")
    private Long statusDictId;









}
