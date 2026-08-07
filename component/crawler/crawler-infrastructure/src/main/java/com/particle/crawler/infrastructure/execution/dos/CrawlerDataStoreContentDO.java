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
 * 爬虫结构数据存储内容表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_data_store_content")
public class CrawlerDataStoreContentDO extends BaseDO {

    /**
    * 爬虫原始数据存储ID
    */
    private Long crawlerDataStoreId;

    /**
    * 存储内容文本
    */
    private String content;


}
