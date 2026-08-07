package com.particle.crawler.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.app.definition.structmapping.CrawlerProjectAppStructMapping;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
import com.particle.crawler.domain.definition.gateway.CrawlerProjectGateway;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.infrastructure.definition.service.ICrawlerProjectService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 爬虫项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
@Validated
public class CrawlerProjectDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerProjectGateway crawlerProjectGateway;
	private ICrawlerProjectService iCrawlerProjectService;

	private CrawlerDefinitionDeleteCommandExecutor crawlerDefinitionDeleteCommandExecutor;
	private ICrawlerDefinitionService iCrawlerDefinitionService;
	/**
	 * 执行 爬虫项目 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrawlerProjectVO> execute(@Valid CommonIdCommand deleteCommand) {
		CrawlerProjectId crawlerProjectId = CrawlerProjectId.of(deleteCommand.getId());
		CrawlerProject byId = crawlerProjectGateway.getById(crawlerProjectId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crawlerProjectGateway.delete(crawlerProjectId,deleteCommand);
		if (delete) {
			// 删除成功后，将流程定义也删除
			List<CrawlerDefinitionDO> crawlerDefinitionDOList = iCrawlerDefinitionService.getByCrawlerProjectId(crawlerProjectId.getId());
			crawlerDefinitionDOList.forEach(crawlerDefinitionDO -> {
				crawlerDefinitionDeleteCommandExecutor.execute(CommonIdCommand.create(crawlerDefinitionDO.getId()));
			});
			return SingleResponse.of(CrawlerProjectAppStructMapping.instance.toCrawlerProjectVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param crawlerProjectGateway
	 */
	@Autowired
	public void setCrawlerProjectGateway(CrawlerProjectGateway crawlerProjectGateway) {
		this.crawlerProjectGateway = crawlerProjectGateway;
	}
	@Autowired
	public void setICrawlerProjectService(ICrawlerProjectService iCrawlerProjectService) {
		this.iCrawlerProjectService = iCrawlerProjectService;
	}
	@Autowired
	public void setCrawlerDefinitionDeleteCommandExecutor(CrawlerDefinitionDeleteCommandExecutor crawlerDefinitionDeleteCommandExecutor) {
		this.crawlerDefinitionDeleteCommandExecutor = crawlerDefinitionDeleteCommandExecutor;
	}
	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
}
