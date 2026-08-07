package com.particle.crawler.domain.definition;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 爬虫定义 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Data
@Entity
public class CrawlerDefinition extends AggreateRoot {

    private CrawlerDefinitionId id;

    /**
    * 爬虫名称
    */
    private String name;

    /**
    * 项目id
    */
    private Long crawlerProjectId;

    /**
    * 最新发布版本爬虫定义id
    */
    private Long latestPublishCrawlerDefinitionHistoryId;

    /**
    * 草稿版本爬虫定义id
    */
    private Long draftCrawlerDefinitionHistoryId;

    /**
    * 描述
    */
    private String remark;

    public void changeDraftCrawlerDefinitionHistoryId(Long draftCrawlerDefinitionHistoryId) {
        this.draftCrawlerDefinitionHistoryId = draftCrawlerDefinitionHistoryId;
    }

    public void changeLatestPublishCrawlerDefinitionHistoryId(Long latestPublishCrawlerDefinitionHistoryId) {
        this.latestPublishCrawlerDefinitionHistoryId = latestPublishCrawlerDefinitionHistoryId;
    }

    /**
     * 创建爬虫定义领域模型对象
     * @return 爬虫定义领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerDefinition create(){
        return DomainFactory.create(CrawlerDefinition.class);
    }
}
