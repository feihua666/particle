package com.particle.crawler.domain.definition;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 爬虫定义历史 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Data
@Entity
public class CrawlerDefinitionHistory extends AggreateRoot {

    private CrawlerDefinitionHistoryId id;

    /**
    * 爬虫定义id
    */
    private Long crawlerDefinitionId;

    /**
    * 定义版本号，从1开始递增
    */
    private Integer crawlerDefinitionVersion;

    /**
    * 流程图数据
    */
    private String definitionJson;

    /**
    * 爬虫级配置json
    */
    private String configJson;

    /**
    * 是否发布，1=已发布，0=未发布，草稿
    */
    private Boolean isPublish;

    public void initForAdd() {
        changeCrawlerDefinitionVersion(1);
        changeUnPublish();
    }

    public void changeCrawlerDefinitionVersion(Integer crawlerDefinitionVersion) {
        this.crawlerDefinitionVersion = crawlerDefinitionVersion;
    }
    public void changePublish() {
        changeIsPublish(true);
    }
    public void changeUnPublish() {
        changeIsPublish(false);
    }
    private void changeIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }


    /**
     * 创建爬虫定义历史领域模型对象
     * @return 爬虫定义历史领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerDefinitionHistory create(){
        return DomainFactory.create(CrawlerDefinitionHistory.class);
    }
}
