package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.infrastructure.execution.structmapping.CrawlerExecutionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫执行实例 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
public class CrawlerExecutionGatewayImpl extends AbstractBaseGatewayImpl<CrawlerExecutionId,CrawlerExecution> implements CrawlerExecutionGateway {

    private ICrawlerExecutionService iCrawlerExecutionService;

    @Override
    public CrawlerExecution getById(CrawlerExecutionId crawlerExecutionId) {
        CrawlerExecutionDO byId = iCrawlerExecutionService.getById(crawlerExecutionId.getId());
        CrawlerExecution crawlerExecution = DomainFactory.create(CrawlerExecution.class);
        crawlerExecution = CrawlerExecutionInfrastructureStructMapping.instance. crawlerExecutionDOToCrawlerExecution(crawlerExecution,byId);
        return crawlerExecution;
    }

    @Override
    public boolean doSave(CrawlerExecution crawlerExecution) {
        CrawlerExecutionDO crawlerExecutionDO = CrawlerExecutionInfrastructureStructMapping.instance.crawlerExecutionToCrawlerExecutionDO(crawlerExecution);
        if (crawlerExecutionDO.getId() == null) {
            crawlerExecutionDO.setAddControl(crawlerExecution.getAddControl());
            CrawlerExecutionDO add = iCrawlerExecutionService.add(crawlerExecutionDO);
            crawlerExecution.setId(CrawlerExecutionId.of(add.getId()));
            return add != null;
        }
        crawlerExecutionDO.setUpdateControl(crawlerExecution.getUpdateControl());
        CrawlerExecutionDO update = iCrawlerExecutionService.update(crawlerExecutionDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerExecutionId crawlerExecutionId) {
        return iCrawlerExecutionService.deleteById(crawlerExecutionId.getId());
    }

    @Override
    public boolean delete(CrawlerExecutionId crawlerExecutionId, IdCommand idCommand) {
        return iCrawlerExecutionService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerExecutionService(ICrawlerExecutionService iCrawlerExecutionService) {
        this.iCrawlerExecutionService = iCrawlerExecutionService;
    }
}
