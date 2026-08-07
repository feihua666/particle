package com.particle.crawler.domain.execution;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 爬虫原始数据存储内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Data
@Entity
public class CrawlerRawStoreContent extends AggreateRoot {

    private CrawlerRawStoreContentId id;

    /**
    * 爬虫原始数据存储ID
    */
    private Long crawlerRawStoreId;

    /**
    * 存储内容文本
    */
    private String content;



    /**
     * 创建爬虫原始数据存储内容领域模型对象
     * @return 爬虫原始数据存储内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerRawStoreContent create(){
        return DomainFactory.create(CrawlerRawStoreContent.class);
    }
}
