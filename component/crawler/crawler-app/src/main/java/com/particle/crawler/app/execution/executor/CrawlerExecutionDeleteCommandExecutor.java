package com.particle.crawler.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.crawler.app.execution.structmapping.CrawlerExecutionAppStructMapping;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionGateway;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
@Validated
public class CrawlerExecutionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerExecutionGateway crawlerExecutionGateway;
	private ICrawlerExecutionService iCrawlerExecutionService;

	/**
	 * 执行 爬虫执行实例 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerExecutionId crawlerExecutionId = CrawlerExecutionId.of(deleteCommand.getId());
		CrawlerExecution byId = crawlerExecutionGateway.getById(crawlerExecutionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerExecutionGateway.delete(crawlerExecutionId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrawlerExecutionAppStructMapping.instance.toCrawlerExecutionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
