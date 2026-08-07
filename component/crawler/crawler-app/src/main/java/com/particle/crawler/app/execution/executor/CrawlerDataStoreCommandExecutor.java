package com.particle.crawler.app.execution.executor;

import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫结构数据存储 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
@Validated
public class CrawlerDataStoreCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreGateway crawlerDataStoreGateway;
	private ICrawlerDataStoreService iCrawlerDataStoreService;
	/**
	 * 注入使用set方法
	 * @param crawlerDataStoreGateway
	 */
	@Autowired
	public void setCrawlerDataStoreGateway(CrawlerDataStoreGateway crawlerDataStoreGateway) {
		this.crawlerDataStoreGateway = crawlerDataStoreGateway;
	}
	@Autowired
	public void setICrawlerDataStoreService(ICrawlerDataStoreService iCrawlerDataStoreService) {
		this.iCrawlerDataStoreService = iCrawlerDataStoreService;
	}
}
