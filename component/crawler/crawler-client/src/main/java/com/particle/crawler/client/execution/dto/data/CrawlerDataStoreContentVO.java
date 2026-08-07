package com.particle.crawler.client.execution.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 爬虫结构数据存储内容 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Data
@Schema
public class CrawlerDataStoreContentVO extends AbstractBaseIdVO {

    @Schema(description = "爬虫原始数据存储ID")
    private Long crawlerDataStoreId;
    
    @Schema(description = "存储内容文本")
    private String content;
    


}
