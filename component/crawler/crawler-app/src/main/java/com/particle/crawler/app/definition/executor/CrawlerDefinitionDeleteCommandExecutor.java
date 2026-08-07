package com.particle.crawler.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.app.execution.executor.CrawlerExecutionDeleteCommandExecutor;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionAppStructMapping;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionGateway;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 爬虫定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
@Validated
public class CrawlerDefinitionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionGateway crawlerDefinitionGateway;
	private ICrawlerDefinitionService iCrawlerDefinitionService;

	private CrawlerDefinitionHistoryDeleteCommandExecutor crawlerDefinitionHistoryDeleteCommandExecutor;
	private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;

	private CrawlerExecutionDeleteCommandExecutor crawlerExecutionDeleteCommandExecutor;
	private ICrawlerExecutionService iCrawlerExecutionService;
	/**
	 * 执行 爬虫定义 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerDefinitionId crawlerDefinitionId = CrawlerDefinitionId.of(deleteCommand.getId());
		CrawlerDefinition byId = crawlerDefinitionGateway.getById(crawlerDefinitionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerDefinitionGateway.delete(crawlerDefinitionId,deleteCommand);
		if (delete) {
			// 删除成功后，将历史也删除
			List<CrawlerDefinitionHistoryDO> crawlerDefinitionHistoryDOList = iCrawlerDefinitionHistoryService.getByCrawlerDefinitionId(crawlerDefinitionId.getId());
			crawlerDefinitionHistoryDOList.forEach(crawlerDefinitionHistoryDO ->
					crawlerDefinitionHistoryDeleteCommandExecutor.execute(CommonIdCommand.create(crawlerDefinitionHistoryDO.getId()))
			);
			// 删除成功后，将执行实例也删除
			List<CrawlerExecutionDO> crawlerExecutionDOList = iCrawlerExecutionService.getByCrawlerDefinitionId(crawlerDefinitionId.getId());
			crawlerExecutionDOList.forEach(crawlerExecutionDO ->
					crawlerExecutionDeleteCommandExecutor.execute(CommonIdCommand.create(crawlerExecutionDO.getId()))
			);
			return SingleResponse.of(CrawlerDefinitionAppStructMapping.instance.toCrawlerDefinitionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param crawlerDefinitionGateway
	 */
	@Autowired
	public void setCrawlerDefinitionGateway(CrawlerDefinitionGateway crawlerDefinitionGateway) {
		this.crawlerDefinitionGateway = crawlerDefinitionGateway;
	}
	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
	@Autowired
	public void setCrawlerDefinitionHistoryDeleteCommandExecutor(CrawlerDefinitionHistoryDeleteCommandExecutor crawlerDefinitionHistoryDeleteCommandExecutor) {
		this.crawlerDefinitionHistoryDeleteCommandExecutor = crawlerDefinitionHistoryDeleteCommandExecutor;
	}
	@Autowired
	public void setICrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService) {
		this.iCrawlerDefinitionHistoryService = iCrawlerDefinitionHistoryService;
	}
	@Autowired
	public void setCrawlerExecutionDeleteCommandExecutor(CrawlerExecutionDeleteCommandExecutor crawlerExecutionDeleteCommandExecutor) {
		this.crawlerExecutionDeleteCommandExecutor = crawlerExecutionDeleteCommandExecutor;
	}
	@Autowired
	public void setICrawlerExecutionService(ICrawlerExecutionService iCrawlerExecutionService) {
		this.iCrawlerExecutionService = iCrawlerExecutionService;
	}
}
