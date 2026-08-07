package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.crawler.infrastructure.execution.structmapping.CrawlerRawStoreInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫原始数据存储 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Component
public class CrawlerRawStoreGatewayImpl extends AbstractBaseGatewayImpl<CrawlerRawStoreId,CrawlerRawStore> implements CrawlerRawStoreGateway {

    private ICrawlerRawStoreService iCrawlerRawStoreService;

    @Override
    public CrawlerRawStore getById(CrawlerRawStoreId crawlerRawStoreId) {
        CrawlerRawStoreDO byId = iCrawlerRawStoreService.getById(crawlerRawStoreId.getId());
        CrawlerRawStore crawlerRawStore = DomainFactory.create(CrawlerRawStore.class);
        crawlerRawStore = CrawlerRawStoreInfrastructureStructMapping.instance. crawlerRawStoreDOToCrawlerRawStore(crawlerRawStore,byId);
        return crawlerRawStore;
    }

    @Override
    public boolean doSave(CrawlerRawStore crawlerRawStore) {
        CrawlerRawStoreDO crawlerRawStoreDO = CrawlerRawStoreInfrastructureStructMapping.instance.crawlerRawStoreToCrawlerRawStoreDO(crawlerRawStore);
        if (crawlerRawStoreDO.getId() == null) {
            crawlerRawStoreDO.setAddControl(crawlerRawStore.getAddControl());
            CrawlerRawStoreDO add = iCrawlerRawStoreService.add(crawlerRawStoreDO);
            crawlerRawStore.setId(CrawlerRawStoreId.of(add.getId()));
            return add != null;
        }
        crawlerRawStoreDO.setUpdateControl(crawlerRawStore.getUpdateControl());
        CrawlerRawStoreDO update = iCrawlerRawStoreService.update(crawlerRawStoreDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerRawStoreId crawlerRawStoreId) {
        return iCrawlerRawStoreService.deleteById(crawlerRawStoreId.getId());
    }

    @Override
    public boolean delete(CrawlerRawStoreId crawlerRawStoreId, IdCommand idCommand) {
        return iCrawlerRawStoreService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerRawStoreService(ICrawlerRawStoreService iCrawlerRawStoreService) {
        this.iCrawlerRawStoreService = iCrawlerRawStoreService;
    }
}
