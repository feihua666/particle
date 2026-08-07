package com.particle.crawler.domain.execution;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 爬虫结构数据存储 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Data
@Entity
public class CrawlerDataStore extends AggreateRoot {

    private CrawlerDataStoreId id;

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



    /**
     * 创建爬虫结构数据存储领域模型对象
     * @return 爬虫结构数据存储领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerDataStore create(){
        return DomainFactory.create(CrawlerDataStore.class);
    }
}
