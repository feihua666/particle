package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.crawler.infrastructure.execution.structmapping.CrawlerDataStoreInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫结构数据存储 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
public class CrawlerDataStoreGatewayImpl extends AbstractBaseGatewayImpl<CrawlerDataStoreId,CrawlerDataStore> implements CrawlerDataStoreGateway {

    private ICrawlerDataStoreService iCrawlerDataStoreService;

    @Override
    public CrawlerDataStore getById(CrawlerDataStoreId crawlerDataStoreId) {
        CrawlerDataStoreDO byId = iCrawlerDataStoreService.getById(crawlerDataStoreId.getId());
        CrawlerDataStore crawlerDataStore = DomainFactory.create(CrawlerDataStore.class);
        crawlerDataStore = CrawlerDataStoreInfrastructureStructMapping.instance. crawlerDataStoreDOToCrawlerDataStore(crawlerDataStore,byId);
        return crawlerDataStore;
    }

    @Override
    public boolean doSave(CrawlerDataStore crawlerDataStore) {
        CrawlerDataStoreDO crawlerDataStoreDO = CrawlerDataStoreInfrastructureStructMapping.instance.crawlerDataStoreToCrawlerDataStoreDO(crawlerDataStore);
        if (crawlerDataStoreDO.getId() == null) {
            crawlerDataStoreDO.setAddControl(crawlerDataStore.getAddControl());
            CrawlerDataStoreDO add = iCrawlerDataStoreService.add(crawlerDataStoreDO);
            crawlerDataStore.setId(CrawlerDataStoreId.of(add.getId()));
            return add != null;
        }
        crawlerDataStoreDO.setUpdateControl(crawlerDataStore.getUpdateControl());
        CrawlerDataStoreDO update = iCrawlerDataStoreService.update(crawlerDataStoreDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerDataStoreId crawlerDataStoreId) {
        return iCrawlerDataStoreService.deleteById(crawlerDataStoreId.getId());
    }

    @Override
    public boolean delete(CrawlerDataStoreId crawlerDataStoreId, IdCommand idCommand) {
        return iCrawlerDataStoreService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerDataStoreService(ICrawlerDataStoreService iCrawlerDataStoreService) {
        this.iCrawlerDataStoreService = iCrawlerDataStoreService;
    }
}
