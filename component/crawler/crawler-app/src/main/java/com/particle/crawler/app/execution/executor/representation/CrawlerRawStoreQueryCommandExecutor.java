package com.particle.crawler.app.execution.executor.representation;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStorePageQueryCommand;
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
 * 爬虫原始数据存储 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Component
@Validated
public class CrawlerRawStoreQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerRawStoreService iCrawlerRawStoreService;

	/**
	 * 执行 爬虫原始数据存储 列表查询指令
	 * @param crawlerRawStoreQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerRawStoreVO> execute(@Valid CrawlerRawStoreQueryListCommand crawlerRawStoreQueryListCommand) {
		List<CrawlerRawStoreDO> crawlerRawStoreDO = iCrawlerRawStoreService.list(crawlerRawStoreQueryListCommand);
		List<CrawlerRawStoreVO> crawlerRawStoreVOs = CrawlerRawStoreAppStructMapping.instance.crawlerRawStoreDOsToCrawlerRawStoreVOs(crawlerRawStoreDO);
		return MultiResponse.of(crawlerRawStoreVOs);
	}
	/**
	 * 执行 爬虫原始数据存储 分页查询指令
	 * @param crawlerRawStorePageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerRawStoreVO> execute(@Valid CrawlerRawStorePageQueryCommand crawlerRawStorePageQueryCommand) {
		Page<CrawlerRawStoreDO> page = iCrawlerRawStoreService.listPage(crawlerRawStorePageQueryCommand);
		return CrawlerRawStoreAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫原始数据存储 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerRawStoreDO byId = iCrawlerRawStoreService.getById(detailCommand.getId());
		CrawlerRawStoreVO crawlerRawStoreVO = CrawlerRawStoreAppStructMapping.instance.crawlerRawStoreDOToCrawlerRawStoreVO(byId);
		return SingleResponse.of(crawlerRawStoreVO);
	}
	/**
	 * 执行 爬虫原始数据存储 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerRawStoreDO byId = iCrawlerRawStoreService.getById(detailForUpdateCommand.getId());
		CrawlerRawStoreVO crawlerRawStoreVO = CrawlerRawStoreAppStructMapping.instance.crawlerRawStoreDOToCrawlerRawStoreVO(byId);
		return SingleResponse.of(crawlerRawStoreVO);
	}


	@Autowired
	public void setICrawlerRawStoreService(ICrawlerRawStoreService iCrawlerRawStoreService) {
		this.iCrawlerRawStoreService = iCrawlerRawStoreService;
	}
}
