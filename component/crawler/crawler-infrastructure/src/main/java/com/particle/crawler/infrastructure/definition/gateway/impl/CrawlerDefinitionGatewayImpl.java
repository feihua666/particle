package com.particle.crawler.infrastructure.definition.gateway.impl;

import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.structmapping.CrawlerDefinitionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫定义 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
public class CrawlerDefinitionGatewayImpl extends AbstractBaseGatewayImpl<CrawlerDefinitionId,CrawlerDefinition> implements CrawlerDefinitionGateway {

    private ICrawlerDefinitionService iCrawlerDefinitionService;

    @Override
    public CrawlerDefinition getById(CrawlerDefinitionId crawlerDefinitionId) {
        CrawlerDefinitionDO byId = iCrawlerDefinitionService.getById(crawlerDefinitionId.getId());
        CrawlerDefinition crawlerDefinition = DomainFactory.create(CrawlerDefinition.class);
        crawlerDefinition = CrawlerDefinitionInfrastructureStructMapping.instance. crawlerDefinitionDOToCrawlerDefinition(crawlerDefinition,byId);
        return crawlerDefinition;
    }

    @Override
    public boolean doSave(CrawlerDefinition crawlerDefinition) {
        CrawlerDefinitionDO crawlerDefinitionDO = CrawlerDefinitionInfrastructureStructMapping.instance.crawlerDefinitionToCrawlerDefinitionDO(crawlerDefinition);
        if (crawlerDefinitionDO.getId() == null) {
            crawlerDefinitionDO.setAddControl(crawlerDefinition.getAddControl());
            CrawlerDefinitionDO add = iCrawlerDefinitionService.add(crawlerDefinitionDO);
            crawlerDefinition.setId(CrawlerDefinitionId.of(add.getId()));
            return add != null;
        }
        crawlerDefinitionDO.setUpdateControl(crawlerDefinition.getUpdateControl());
        CrawlerDefinitionDO update = iCrawlerDefinitionService.update(crawlerDefinitionDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerDefinitionId crawlerDefinitionId) {
        return iCrawlerDefinitionService.deleteById(crawlerDefinitionId.getId());
    }

    @Override
    public boolean delete(CrawlerDefinitionId crawlerDefinitionId, IdCommand idCommand) {
        return iCrawlerDefinitionService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
        this.iCrawlerDefinitionService = iCrawlerDefinitionService;
    }
}
