package com.particle.crawler.app.definition.executor.representation;

import com.particle.crawler.app.definition.structmapping.CrawlerProjectAppStructMapping;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerProjectService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectPageQueryCommand;
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
 * 爬虫项目 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
@Validated
public class CrawlerProjectQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerProjectService iCrawlerProjectService;

	/**
	 * 执行 爬虫项目 列表查询指令
	 * @param crawlerProjectQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerProjectVO> execute(@Valid CrawlerProjectQueryListCommand crawlerProjectQueryListCommand) {
		List<CrawlerProjectDO> crawlerProjectDO = iCrawlerProjectService.list(crawlerProjectQueryListCommand);
		List<CrawlerProjectVO> crawlerProjectVOs = CrawlerProjectAppStructMapping.instance.crawlerProjectDOsToCrawlerProjectVOs(crawlerProjectDO);
		return MultiResponse.of(crawlerProjectVOs);
	}
	/**
	 * 执行 爬虫项目 分页查询指令
	 * @param crawlerProjectPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerProjectVO> execute(@Valid CrawlerProjectPageQueryCommand crawlerProjectPageQueryCommand) {
		Page<CrawlerProjectDO> page = iCrawlerProjectService.listPage(crawlerProjectPageQueryCommand);
		return CrawlerProjectAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫项目 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerProjectVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerProjectDO byId = iCrawlerProjectService.getById(detailCommand.getId());
		CrawlerProjectVO crawlerProjectVO = CrawlerProjectAppStructMapping.instance.crawlerProjectDOToCrawlerProjectVO(byId);
		return SingleResponse.of(crawlerProjectVO);
	}
	/**
	 * 执行 爬虫项目 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerProjectVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerProjectDO byId = iCrawlerProjectService.getById(detailForUpdateCommand.getId());
		CrawlerProjectVO crawlerProjectVO = CrawlerProjectAppStructMapping.instance.crawlerProjectDOToCrawlerProjectVO(byId);
		return SingleResponse.of(crawlerProjectVO);
	}


	@Autowired
	public void setICrawlerProjectService(ICrawlerProjectService iCrawlerProjectService) {
		this.iCrawlerProjectService = iCrawlerProjectService;
	}
}
