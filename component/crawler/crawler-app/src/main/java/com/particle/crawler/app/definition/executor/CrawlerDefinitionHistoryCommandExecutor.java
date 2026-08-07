package com.particle.crawler.app.definition.executor;

import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionHistoryGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫定义历史 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
@Validated
public class CrawlerDefinitionHistoryCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway;
	private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;
	/**
	 * 注入使用set方法
	 * @param crawlerDefinitionHistoryGateway
	 */
	@Autowired
	public void setCrawlerDefinitionHistoryGateway(CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway) {
		this.crawlerDefinitionHistoryGateway = crawlerDefinitionHistoryGateway;
	}
	@Autowired
	public void setICrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService) {
		this.iCrawlerDefinitionHistoryService = iCrawlerDefinitionHistoryService;
	}
}
