package com.particle.crawler.app.execution.executor;

import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫原始数据存储内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
@Validated
public class CrawlerRawStoreContentCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreContentGateway crawlerRawStoreContentGateway;
	private ICrawlerRawStoreContentService iCrawlerRawStoreContentService;
	/**
	 * 注入使用set方法
	 * @param crawlerRawStoreContentGateway
	 */
	@Autowired
	public void setCrawlerRawStoreContentGateway(CrawlerRawStoreContentGateway crawlerRawStoreContentGateway) {
		this.crawlerRawStoreContentGateway = crawlerRawStoreContentGateway;
	}
	@Autowired
	public void setICrawlerRawStoreContentService(ICrawlerRawStoreContentService iCrawlerRawStoreContentService) {
		this.iCrawlerRawStoreContentService = iCrawlerRawStoreContentService;
	}
}
