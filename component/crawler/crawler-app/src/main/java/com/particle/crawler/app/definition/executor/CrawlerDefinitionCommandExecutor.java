package com.particle.crawler.app.definition.executor;

import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫定义 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
@Validated
public class CrawlerDefinitionCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionGateway crawlerDefinitionGateway;
	private ICrawlerDefinitionService iCrawlerDefinitionService;
	/**
	 * 注入使用set方法
	 * @param crawlerDefinitionGateway
	 */
	@Autowired
	public void setCrawlerDefinitionGateway(CrawlerDefinitionGateway crawlerDefinitionGateway) {
		this.crawlerDefinitionGateway = crawlerDefinitionGateway;
	}
	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
}
