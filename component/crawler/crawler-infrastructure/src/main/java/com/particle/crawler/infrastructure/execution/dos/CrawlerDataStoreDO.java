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
 * 爬虫结构数据存储表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_data_store")
public class CrawlerDataStoreDO extends BaseDO {

    /**
    * 爬虫原始数据存储ID
    */
    private Long crawlerRawStoreId;

    /**
    * 爬虫执行实例ID
    */
    private Long crawlerExecutionId;

    /**
    * 爬虫定义ID
    */
    private Long crawlerDefinitionId;

    /**
    * 执行时使用的版本ID
    */
    private Long crawlerDefinitionHistoryId;

    /**
    * 爬虫定义名称
    */
    private String crawlerDefinitionName;


}
