package com.particle.crawler.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫原始数据存储内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
@Validated
public class CrawlerRawStoreContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreContentGateway crawlerRawStoreContentGateway;
	private ICrawlerRawStoreContentService iCrawlerRawStoreContentService;

	/**
	 * 执行 爬虫原始数据存储内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreContentVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerRawStoreContentId crawlerRawStoreContentId = CrawlerRawStoreContentId.of(deleteCommand.getId());
		CrawlerRawStoreContent byId = crawlerRawStoreContentGateway.getById(crawlerRawStoreContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerRawStoreContentGateway.delete(crawlerRawStoreContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrawlerRawStoreContentAppStructMapping.instance.toCrawlerRawStoreContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
