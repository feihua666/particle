package com.particle.crawler.app.execution.executor;

import com.particle.crawler.domain.execution.gateway.CrawlerExecutionGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫执行实例 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
@Validated
public class CrawlerExecutionCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerExecutionGateway crawlerExecutionGateway;
	private ICrawlerExecutionService iCrawlerExecutionService;
	/**
	 * 注入使用set方法
	 * @param crawlerExecutionGateway
	 */
	@Autowired
	public void setCrawlerExecutionGateway(CrawlerExecutionGateway crawlerExecutionGateway) {
		this.crawlerExecutionGateway = crawlerExecutionGateway;
	}
	@Autowired
	public void setICrawlerExecutionService(ICrawlerExecutionService iCrawlerExecutionService) {
		this.iCrawlerExecutionService = iCrawlerExecutionService;
	}
}
