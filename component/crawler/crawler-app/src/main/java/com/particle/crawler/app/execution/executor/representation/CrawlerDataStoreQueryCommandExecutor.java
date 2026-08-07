package com.particle.crawler.app.execution.executor.representation;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStorePageQueryCommand;
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
 * 爬虫结构数据存储 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
@Validated
public class CrawlerDataStoreQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerDataStoreService iCrawlerDataStoreService;

	/**
	 * 执行 爬虫结构数据存储 列表查询指令
	 * @param crawlerDataStoreQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerDataStoreVO> execute(@Valid CrawlerDataStoreQueryListCommand crawlerDataStoreQueryListCommand) {
		List<CrawlerDataStoreDO> crawlerDataStoreDO = iCrawlerDataStoreService.list(crawlerDataStoreQueryListCommand);
		List<CrawlerDataStoreVO> crawlerDataStoreVOs = CrawlerDataStoreAppStructMapping.instance.crawlerDataStoreDOsToCrawlerDataStoreVOs(crawlerDataStoreDO);
		return MultiResponse.of(crawlerDataStoreVOs);
	}
	/**
	 * 执行 爬虫结构数据存储 分页查询指令
	 * @param crawlerDataStorePageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerDataStoreVO> execute(@Valid CrawlerDataStorePageQueryCommand crawlerDataStorePageQueryCommand) {
		Page<CrawlerDataStoreDO> page = iCrawlerDataStoreService.listPage(crawlerDataStorePageQueryCommand);
		return CrawlerDataStoreAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫结构数据存储 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerDataStoreDO byId = iCrawlerDataStoreService.getById(detailCommand.getId());
		CrawlerDataStoreVO crawlerDataStoreVO = CrawlerDataStoreAppStructMapping.instance.crawlerDataStoreDOToCrawlerDataStoreVO(byId);
		return SingleResponse.of(crawlerDataStoreVO);
	}
	/**
	 * 执行 爬虫结构数据存储 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerDataStoreDO byId = iCrawlerDataStoreService.getById(detailForUpdateCommand.getId());
		CrawlerDataStoreVO crawlerDataStoreVO = CrawlerDataStoreAppStructMapping.instance.crawlerDataStoreDOToCrawlerDataStoreVO(byId);
		return SingleResponse.of(crawlerDataStoreVO);
	}


	@Autowired
	public void setICrawlerDataStoreService(ICrawlerDataStoreService iCrawlerDataStoreService) {
		this.iCrawlerDataStoreService = iCrawlerDataStoreService;
	}
}
