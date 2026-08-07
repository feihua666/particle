package com.particle.crawler.app.definition.executor;

import com.particle.crawler.domain.definition.gateway.CrawlerProjectGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerProjectService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫项目 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
@Validated
public class CrawlerProjectCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerProjectGateway crawlerProjectGateway;
	private ICrawlerProjectService iCrawlerProjectService;
	/**
	 * 注入使用set方法
	 * @param crawlerProjectGateway
	 */
	@Autowired
	public void setCrawlerProjectGateway(CrawlerProjectGateway crawlerProjectGateway) {
		this.crawlerProjectGateway = crawlerProjectGateway;
	}
	@Autowired
	public void setICrawlerProjectService(ICrawlerProjectService iCrawlerProjectService) {
		this.iCrawlerProjectService = iCrawlerProjectService;
	}
}
