package com.particle.crawler.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreContentGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreContentService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫结构数据存储内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
@Validated
public class CrawlerDataStoreContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreContentGateway crawlerDataStoreContentGateway;
	private ICrawlerDataStoreContentService iCrawlerDataStoreContentService;

	/**
	 * 执行 爬虫结构数据存储内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreContentVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerDataStoreContentId crawlerDataStoreContentId = CrawlerDataStoreContentId.of(deleteCommand.getId());
		CrawlerDataStoreContent byId = crawlerDataStoreContentGateway.getById(crawlerDataStoreContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerDataStoreContentGateway.delete(crawlerDataStoreContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrawlerDataStoreContentAppStructMapping.instance.toCrawlerDataStoreContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
