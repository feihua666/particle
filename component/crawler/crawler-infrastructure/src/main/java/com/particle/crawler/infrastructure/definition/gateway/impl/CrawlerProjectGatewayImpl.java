package com.particle.crawler.infrastructure.definition.gateway.impl;

import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
import com.particle.crawler.domain.definition.gateway.CrawlerProjectGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerProjectService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.crawler.infrastructure.definition.structmapping.CrawlerProjectInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 爬虫项目 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
public class CrawlerProjectGatewayImpl extends AbstractBaseGatewayImpl<CrawlerProjectId,CrawlerProject> implements CrawlerProjectGateway {

    private ICrawlerProjectService iCrawlerProjectService;

    @Override
    public CrawlerProject getById(CrawlerProjectId crawlerProjectId) {
        CrawlerProjectDO byId = iCrawlerProjectService.getById(crawlerProjectId.getId());
        CrawlerProject crawlerProject = DomainFactory.create(CrawlerProject.class);
        crawlerProject = CrawlerProjectInfrastructureStructMapping.instance. crawlerProjectDOToCrawlerProject(crawlerProject,byId);
        return crawlerProject;
    }

    @Override
    public boolean doSave(CrawlerProject crawlerProject) {
        CrawlerProjectDO crawlerProjectDO = CrawlerProjectInfrastructureStructMapping.instance.crawlerProjectToCrawlerProjectDO(crawlerProject);
        if (crawlerProjectDO.getId() == null) {
            crawlerProjectDO.setAddControl(crawlerProject.getAddControl());
            CrawlerProjectDO add = iCrawlerProjectService.add(crawlerProjectDO);
            crawlerProject.setId(CrawlerProjectId.of(add.getId()));
            return add != null;
        }
        crawlerProjectDO.setUpdateControl(crawlerProject.getUpdateControl());
        CrawlerProjectDO update = iCrawlerProjectService.update(crawlerProjectDO);
        return update != null;
    }

    @Override
    public boolean delete(CrawlerProjectId crawlerProjectId) {
        return iCrawlerProjectService.deleteById(crawlerProjectId.getId());
    }

    @Override
    public boolean delete(CrawlerProjectId crawlerProjectId, IdCommand idCommand) {
        return iCrawlerProjectService.deleteById(idCommand);
    }

    @Autowired
    public void setICrawlerProjectService(ICrawlerProjectService iCrawlerProjectService) {
        this.iCrawlerProjectService = iCrawlerProjectService;
    }
}
