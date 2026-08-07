package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.crawler.infrastructure.execution.structmapping.CrawlerRawStoreContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫原始数据存储内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
public class CrawlerRawStoreContentGatewayImpl extends AbstractBaseGatewayImpl<CrawlerRawStoreContentId,CrawlerRawStoreContent> implements CrawlerRawStoreContentGateway {

    private ICrawlerRawStoreContentService iCrawlerRawStoreContentService;

    @Override
    public CrawlerRawStoreContent getById(CrawlerRawStoreContentId crawlerRawStoreContentId) {
        CrawlerRawStoreContentDO byId = iCrawlerRawStoreContentService.getById(crawlerRawStoreContentId.getId());
        CrawlerRawStoreContent crawlerRawStoreContent = DomainFactory.create(CrawlerRawStoreContent.class);
        crawlerRawStoreContent = CrawlerRawStoreContentInfrastructureStructMapping.instance. crawlerRawStoreContentDOToCrawlerRawStoreContent(crawlerRawStoreContent,byId);
        return crawlerRawStoreContent;
    }

    @Override
    public boolean doSave(CrawlerRawStoreContent crawlerRawStoreContent) {
        CrawlerRawStoreContentDO crawlerRawStoreContentDO = CrawlerRawStoreContentInfrastructureStructMapping.instance.crawlerRawStoreContentToCrawlerRawStoreContentDO(crawlerRawStoreContent);
        if (crawlerRawStoreContentDO.getId() == null) {
            crawlerRawStoreContentDO.setAddControl(crawlerRawStoreContent.getAddControl());
            CrawlerRawStoreContentDO add = iCrawlerRawStoreContentService.add(crawlerRawStoreContentDO);
            crawlerRawStoreContent.setId(CrawlerRawStoreContentId.of(add.getId()));
            return add != null;
        }
        crawlerRawStoreContentDO.setUpdateControl(crawlerRawStoreContent.getUpdateControl());
        CrawlerRawStoreContentDO update = iCrawlerRawStoreContentService.update(crawlerRawStoreContentDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerRawStoreContentId crawlerRawStoreContentId) {
        return iCrawlerRawStoreContentService.deleteById(crawlerRawStoreContentId.getId());
    }

    @Override
    public boolean delete(CrawlerRawStoreContentId crawlerRawStoreContentId, IdCommand idCommand) {
        return iCrawlerRawStoreContentService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerRawStoreContentService(ICrawlerRawStoreContentService iCrawlerRawStoreContentService) {
        this.iCrawlerRawStoreContentService = iCrawlerRawStoreContentService;
    }
}
