package com.particle.crawler.infrastructure.definition.gateway.impl;

import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistoryId;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionHistoryGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.structmapping.CrawlerDefinitionHistoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫定义历史 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
public class CrawlerDefinitionHistoryGatewayImpl extends AbstractBaseGatewayImpl<CrawlerDefinitionHistoryId,CrawlerDefinitionHistory> implements CrawlerDefinitionHistoryGateway {

    private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;

    @Override
    public CrawlerDefinitionHistory getById(CrawlerDefinitionHistoryId crawlerDefinitionHistoryId) {
        CrawlerDefinitionHistoryDO byId = iCrawlerDefinitionHistoryService.getById(crawlerDefinitionHistoryId.getId());
        CrawlerDefinitionHistory crawlerDefinitionHistory = DomainFactory.create(CrawlerDefinitionHistory.class);
        crawlerDefinitionHistory = CrawlerDefinitionHistoryInfrastructureStructMapping.instance. crawlerDefinitionHistoryDOToCrawlerDefinitionHistory(crawlerDefinitionHistory,byId);
        return crawlerDefinitionHistory;
    }

    @Override
    public boolean doSave(CrawlerDefinitionHistory crawlerDefinitionHistory) {
        CrawlerDefinitionHistoryDO crawlerDefinitionHistoryDO = CrawlerDefinitionHistoryInfrastructureStructMapping.instance.crawlerDefinitionHistoryToCrawlerDefinitionHistoryDO(crawlerDefinitionHistory);
        if (crawlerDefinitionHistoryDO.getId() == null) {
            crawlerDefinitionHistoryDO.setAddControl(crawlerDefinitionHistory.getAddControl());
            CrawlerDefinitionHistoryDO add = iCrawlerDefinitionHistoryService.add(crawlerDefinitionHistoryDO);
            crawlerDefinitionHistory.setId(CrawlerDefinitionHistoryId.of(add.getId()));
            return add != null;
        }
        crawlerDefinitionHistoryDO.setUpdateControl(crawlerDefinitionHistory.getUpdateControl());
        CrawlerDefinitionHistoryDO update = iCrawlerDefinitionHistoryService.update(crawlerDefinitionHistoryDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerDefinitionHistoryId crawlerDefinitionHistoryId) {
        return iCrawlerDefinitionHistoryService.deleteById(crawlerDefinitionHistoryId.getId());
    }

    @Override
    public boolean delete(CrawlerDefinitionHistoryId crawlerDefinitionHistoryId, IdCommand idCommand) {
        return iCrawlerDefinitionHistoryService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService) {
        this.iCrawlerDefinitionHistoryService = iCrawlerDefinitionHistoryService;
    }
}
