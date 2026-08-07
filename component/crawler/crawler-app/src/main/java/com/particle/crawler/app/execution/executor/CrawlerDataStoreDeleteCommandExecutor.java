package com.particle.crawler.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫结构数据存储 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
@Validated
public class CrawlerDataStoreDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreGateway crawlerDataStoreGateway;
	private ICrawlerDataStoreService iCrawlerDataStoreService;

	/**
	 * 执行 爬虫结构数据存储 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerDataStoreId crawlerDataStoreId = CrawlerDataStoreId.of(deleteCommand.getId());
		CrawlerDataStore byId = crawlerDataStoreGateway.getById(crawlerDataStoreId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerDataStoreGateway.delete(crawlerDataStoreId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrawlerDataStoreAppStructMapping.instance.toCrawlerDataStoreVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
