package com.particle.crawler.app.execution.executor;

import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫结构数据存储内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
@Validated
public class CrawlerDataStoreContentCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreContentGateway crawlerDataStoreContentGateway;
	private ICrawlerDataStoreContentService iCrawlerDataStoreContentService;
	/**
	 * 注入使用set方法
	 * @param crawlerDataStoreContentGateway
	 */
	@Autowired
	public void setCrawlerDataStoreContentGateway(CrawlerDataStoreContentGateway crawlerDataStoreContentGateway) {
		this.crawlerDataStoreContentGateway = crawlerDataStoreContentGateway;
	}
	@Autowired
	public void setICrawlerDataStoreContentService(ICrawlerDataStoreContentService iCrawlerDataStoreContentService) {
		this.iCrawlerDataStoreContentService = iCrawlerDataStoreContentService;
	}
}
