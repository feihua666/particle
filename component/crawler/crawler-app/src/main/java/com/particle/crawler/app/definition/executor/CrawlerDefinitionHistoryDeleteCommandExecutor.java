package com.particle.crawler.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateControlCommand;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionHistoryAppStructMapping;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistoryId;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionHistoryGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫定义历史 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
@Validated
public class CrawlerDefinitionHistoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway;
	private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;

	private ICrawlerDefinitionService iCrawlerDefinitionService;
	/**
	 * 执行 爬虫定义历史 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerDefinitionHistoryId crawlerDefinitionHistoryId = CrawlerDefinitionHistoryId.of(deleteCommand.getId());
		CrawlerDefinitionHistory byId = crawlerDefinitionHistoryGateway.getById(crawlerDefinitionHistoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerDefinitionHistoryGateway.delete(crawlerDefinitionHistoryId,deleteCommand);
		if (delete) {
			// 删除后，确认一下流程定义里面是否还引用了该数据，如果引用了，设置为空
			CrawlerDefinitionDO crawlerDefinitionDO = iCrawlerDefinitionService.getById(byId.getCrawlerDefinitionId());

			if (crawlerDefinitionDO != null) {
				boolean needUpdate = false;
				CrawlerDefinitionUpdateControlCommand crawlerDefinitionUpdateCommand = new CrawlerDefinitionUpdateControlCommand();
				crawlerDefinitionUpdateCommand.setId(crawlerDefinitionDO.getId());
				crawlerDefinitionUpdateCommand.setVersion(crawlerDefinitionDO.getVersion());
				crawlerDefinitionUpdateCommand.setLatestPublishCrawlerDefinitionHistoryId(crawlerDefinitionDO.getLatestPublishCrawlerDefinitionHistoryId());
				crawlerDefinitionUpdateCommand.setDraftCrawlerDefinitionHistoryId(crawlerDefinitionDO.getDraftCrawlerDefinitionHistoryId());

				if (deleteCommand.getId().equals(crawlerDefinitionDO.getLatestPublishCrawlerDefinitionHistoryId())) {
					crawlerDefinitionUpdateCommand.setLatestPublishCrawlerDefinitionHistoryId(null);
					needUpdate = true;
				}
				if (deleteCommand.getId().equals(crawlerDefinitionDO.getDraftCrawlerDefinitionHistoryId())) {
					crawlerDefinitionUpdateCommand.setDraftCrawlerDefinitionHistoryId(null);
					needUpdate = true;
				}
				if (needUpdate) {
					CrawlerDefinitionDO crawlerDefinitionDOForUpdate = new CrawlerDefinitionDO();
					crawlerDefinitionDOForUpdate.setId(crawlerDefinitionUpdateCommand.getId());
					crawlerDefinitionDOForUpdate.setUpdateControl(crawlerDefinitionUpdateCommand);
					crawlerDefinitionDOForUpdate.setVersion(crawlerDefinitionUpdateCommand.getVersion());
					crawlerDefinitionDOForUpdate.setLatestPublishCrawlerDefinitionHistoryId(crawlerDefinitionUpdateCommand.getLatestPublishCrawlerDefinitionHistoryId());
					crawlerDefinitionDOForUpdate.setDraftCrawlerDefinitionHistoryId(crawlerDefinitionUpdateCommand.getDraftCrawlerDefinitionHistoryId());
					iCrawlerDefinitionService.update(crawlerDefinitionDOForUpdate);
				}
			}
			return SingleResponse.of(CrawlerDefinitionHistoryAppStructMapping.instance.toCrawlerDefinitionHistoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
}
