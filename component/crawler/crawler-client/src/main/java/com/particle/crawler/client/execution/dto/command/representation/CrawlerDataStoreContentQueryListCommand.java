package com.particle.crawler.client.execution.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫结构数据存储内容 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Data
@Schema
public class CrawlerDataStoreContentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "爬虫原始数据存储ID")
    private Long crawlerDataStoreId;


    @Schema(description = "存储内容文本")
    private String content;









}
