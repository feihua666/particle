package com.particle.crawler.app.execution.executor.representation;

import com.particle.crawler.app.execution.structmapping.CrawlerExecutionAppStructMapping;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionPageQueryCommand;
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
 * 爬虫执行实例 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
@Validated
public class CrawlerExecutionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerExecutionService iCrawlerExecutionService;

	/**
	 * 执行 爬虫执行实例 列表查询指令
	 * @param crawlerExecutionQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerExecutionVO> execute(@Valid CrawlerExecutionQueryListCommand crawlerExecutionQueryListCommand) {
		List<CrawlerExecutionDO> crawlerExecutionDO = iCrawlerExecutionService.list(crawlerExecutionQueryListCommand);
		List<CrawlerExecutionVO> crawlerExecutionVOs = CrawlerExecutionAppStructMapping.instance.crawlerExecutionDOsToCrawlerExecutionVOs(crawlerExecutionDO);
		return MultiResponse.of(crawlerExecutionVOs);
	}
	/**
	 * 执行 爬虫执行实例 分页查询指令
	 * @param crawlerExecutionPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerExecutionVO> execute(@Valid CrawlerExecutionPageQueryCommand crawlerExecutionPageQueryCommand) {
		Page<CrawlerExecutionDO> page = iCrawlerExecutionService.listPage(crawlerExecutionPageQueryCommand);
		return CrawlerExecutionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫执行实例 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerExecutionDO byId = iCrawlerExecutionService.getById(detailCommand.getId());
		CrawlerExecutionVO crawlerExecutionVO = CrawlerExecutionAppStructMapping.instance.crawlerExecutionDOToCrawlerExecutionVO(byId);
		return SingleResponse.of(crawlerExecutionVO);
	}
	/**
	 * 执行 爬虫执行实例 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerExecutionDO byId = iCrawlerExecutionService.getById(detailForUpdateCommand.getId());
		CrawlerExecutionVO crawlerExecutionVO = CrawlerExecutionAppStructMapping.instance.crawlerExecutionDOToCrawlerExecutionVO(byId);
		return SingleResponse.of(crawlerExecutionVO);
	}


	@Autowired
	public void setICrawlerExecutionService(ICrawlerExecutionService iCrawlerExecutionService) {
		this.iCrawlerExecutionService = iCrawlerExecutionService;
	}
}
