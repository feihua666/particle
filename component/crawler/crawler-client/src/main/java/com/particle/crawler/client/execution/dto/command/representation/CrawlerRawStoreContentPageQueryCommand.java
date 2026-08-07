package com.particle.crawler.client.execution.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 爬虫原始数据存储内容 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Data
@Schema
public class CrawlerRawStoreContentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "爬虫原始数据存储ID")
    private Long crawlerRawStoreId;


    @Schema(description = "存储内容文本")
    private String content;









}
