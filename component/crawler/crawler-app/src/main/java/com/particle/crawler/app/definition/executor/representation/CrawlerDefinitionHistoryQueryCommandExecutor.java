package com.particle.crawler.app.definition.executor.representation;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionHistoryAppStructMapping;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryPageQueryCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 爬虫定义历史 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
@Validated
public class CrawlerDefinitionHistoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;

	/**
	 * 执行 爬虫定义历史 列表查询指令
	 * @param crawlerDefinitionHistoryQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerDefinitionHistoryVO> execute(@Valid CrawlerDefinitionHistoryQueryListCommand crawlerDefinitionHistoryQueryListCommand) {
		List<CrawlerDefinitionHistoryDO> crawlerDefinitionHistoryDO = iCrawlerDefinitionHistoryService.list(crawlerDefinitionHistoryQueryListCommand);
		List<CrawlerDefinitionHistoryVO> crawlerDefinitionHistoryVOs = CrawlerDefinitionHistoryAppStructMapping.instance.crawlerDefinitionHistoryDOsToCrawlerDefinitionHistoryVOs(crawlerDefinitionHistoryDO);
		return MultiResponse.of(crawlerDefinitionHistoryVOs);
	}
	/**
	 * 执行 爬虫定义历史 分页查询指令
	 * @param crawlerDefinitionHistoryPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerDefinitionHistoryVO> execute(@Valid CrawlerDefinitionHistoryPageQueryCommand crawlerDefinitionHistoryPageQueryCommand) {
		Page<CrawlerDefinitionHistoryDO> page = iCrawlerDefinitionHistoryService.listPage(crawlerDefinitionHistoryPageQueryCommand);
		return CrawlerDefinitionHistoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫定义历史 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerDefinitionHistoryDO byId = iCrawlerDefinitionHistoryService.getById(detailCommand.getId());
		CrawlerDefinitionHistoryVO crawlerDefinitionHistoryVO = CrawlerDefinitionHistoryAppStructMapping.instance.crawlerDefinitionHistoryDOToCrawlerDefinitionHistoryVO(byId);
		return SingleResponse.of(crawlerDefinitionHistoryVO);
	}
	/**
	 * 执行 爬虫定义历史 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerDefinitionHistoryDO byId = iCrawlerDefinitionHistoryService.getById(detailForUpdateCommand.getId());
		CrawlerDefinitionHistoryVO crawlerDefinitionHistoryVO = CrawlerDefinitionHistoryAppStructMapping.instance.crawlerDefinitionHistoryDOToCrawlerDefinitionHistoryVO(byId);
		return SingleResponse.of(crawlerDefinitionHistoryVO);
	}


	@Autowired
	public void setICrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService) {
		this.iCrawlerDefinitionHistoryService = iCrawlerDefinitionHistoryService;
	}
}
