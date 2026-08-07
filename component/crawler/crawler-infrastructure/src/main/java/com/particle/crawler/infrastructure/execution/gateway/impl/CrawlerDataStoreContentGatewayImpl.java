package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import com.particle.crawler.infrastructure.execution.structmapping.CrawlerDataStoreContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫结构数据存储内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
public class CrawlerDataStoreContentGatewayImpl extends AbstractBaseGatewayImpl<CrawlerDataStoreContentId,CrawlerDataStoreContent> implements CrawlerDataStoreContentGateway {

    private ICrawlerDataStoreContentService iCrawlerDataStoreContentService;

    @Override
    public CrawlerDataStoreContent getById(CrawlerDataStoreContentId crawlerDataStoreContentId) {
        CrawlerDataStoreContentDO byId = iCrawlerDataStoreContentService.getById(crawlerDataStoreContentId.getId());
        CrawlerDataStoreContent crawlerDataStoreContent = DomainFactory.create(CrawlerDataStoreContent.class);
        crawlerDataStoreContent = CrawlerDataStoreContentInfrastructureStructMapping.instance. crawlerDataStoreContentDOToCrawlerDataStoreContent(crawlerDataStoreContent,byId);
        return crawlerDataStoreContent;
    }

    @Override
    public boolean doSave(CrawlerDataStoreContent crawlerDataStoreContent) {
        CrawlerDataStoreContentDO crawlerDataStoreContentDO = CrawlerDataStoreContentInfrastructureStructMapping.instance.crawlerDataStoreContentToCrawlerDataStoreContentDO(crawlerDataStoreContent);
        if (crawlerDataStoreContentDO.getId() == null) {
            crawlerDataStoreContentDO.setAddControl(crawlerDataStoreContent.getAddControl());
            CrawlerDataStoreContentDO add = iCrawlerDataStoreContentService.add(crawlerDataStoreContentDO);
            crawlerDataStoreContent.setId(CrawlerDataStoreContentId.of(add.getId()));
            return add != null;
        }
        crawlerDataStoreContentDO.setUpdateControl(crawlerDataStoreContent.getUpdateControl());
        CrawlerDataStoreContentDO update = iCrawlerDataStoreContentService.update(crawlerDataStoreContentDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerDataStoreContentId crawlerDataStoreContentId) {
        return iCrawlerDataStoreContentService.deleteById(crawlerDataStoreContentId.getId());
    }

    @Override
    public boolean delete(CrawlerDataStoreContentId crawlerDataStoreContentId, IdCommand idCommand) {
        return iCrawlerDataStoreContentService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerDataStoreContentService(ICrawlerDataStoreContentService iCrawlerDataStoreContentService) {
        this.iCrawlerDataStoreContentService = iCrawlerDataStoreContentService;
    }
}
