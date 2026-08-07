package com.particle.crawler.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫原始数据存储 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Component
@Validated
public class CrawlerRawStoreDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreGateway crawlerRawStoreGateway;
	private ICrawlerRawStoreService iCrawlerRawStoreService;

	/**
	 * 执行 爬虫原始数据存储 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerRawStoreId crawlerRawStoreId = CrawlerRawStoreId.of(deleteCommand.getId());
		CrawlerRawStore byId = crawlerRawStoreGateway.getById(crawlerRawStoreId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerRawStoreGateway.delete(crawlerRawStoreId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrawlerRawStoreAppStructMapping.instance.toCrawlerRawStoreVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param crawlerRawStoreGateway
	 */
	@Autowired
	public void setCrawlerRawStoreGateway(CrawlerRawStoreGateway crawlerRawStoreGateway) {
		this.crawlerRawStoreGateway = crawlerRawStoreGateway;
	}
	@Autowired
	public void setICrawlerRawStoreService(ICrawlerRawStoreService iCrawlerRawStoreService) {
		this.iCrawlerRawStoreService = iCrawlerRawStoreService;
	}
}
