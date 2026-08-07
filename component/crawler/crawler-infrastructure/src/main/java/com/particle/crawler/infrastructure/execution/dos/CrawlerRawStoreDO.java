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
 * 爬虫原始数据存储表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_raw_store")
public class CrawlerRawStoreDO extends BaseDO {

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

    /**
    * 页面地址
    */
    private String pageUrl;

    /**
    * 页面标题
    */
    private String pageTitle;

    /**
    * 状态字典id：stored/processed/processing
    */
    private Long statusDictId;


}
