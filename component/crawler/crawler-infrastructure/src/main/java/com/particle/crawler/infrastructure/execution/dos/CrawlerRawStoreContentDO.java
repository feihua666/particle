package com.particle.crawler.infrastructure.execution.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 爬虫原始数据存储内容表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_raw_store_content")
public class CrawlerRawStoreContentDO extends BaseDO {

    /**
    * 爬虫原始数据存储ID
    */
    private Long crawlerRawStoreId;

    /**
    * 存储内容文本
    */
    private String content;


}
