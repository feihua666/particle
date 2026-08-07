package com.particle.crawler.app.execution.executor.representation;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreContentService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentPageQueryCommand;
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
 * 爬虫原始数据存储内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
@Validated
public class CrawlerRawStoreContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerRawStoreContentService iCrawlerRawStoreContentService;

	/**
	 * 执行 爬虫原始数据存储内容 列表查询指令
	 * @param crawlerRawStoreContentQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerRawStoreContentVO> execute(@Valid CrawlerRawStoreContentQueryListCommand crawlerRawStoreContentQueryListCommand) {
		List<CrawlerRawStoreContentDO> crawlerRawStoreContentDO = iCrawlerRawStoreContentService.list(crawlerRawStoreContentQueryListCommand);
		List<CrawlerRawStoreContentVO> crawlerRawStoreContentVOs = CrawlerRawStoreContentAppStructMapping.instance.crawlerRawStoreContentDOsToCrawlerRawStoreContentVOs(crawlerRawStoreContentDO);
		return MultiResponse.of(crawlerRawStoreContentVOs);
	}
	/**
	 * 执行 爬虫原始数据存储内容 分页查询指令
	 * @param crawlerRawStoreContentPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerRawStoreContentVO> execute(@Valid CrawlerRawStoreContentPageQueryCommand crawlerRawStoreContentPageQueryCommand) {
		Page<CrawlerRawStoreContentDO> page = iCrawlerRawStoreContentService.listPage(crawlerRawStoreContentPageQueryCommand);
		return CrawlerRawStoreContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫原始数据存储内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreContentVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerRawStoreContentDO byId = iCrawlerRawStoreContentService.getById(detailCommand.getId());
		CrawlerRawStoreContentVO crawlerRawStoreContentVO = CrawlerRawStoreContentAppStructMapping.instance.crawlerRawStoreContentDOToCrawlerRawStoreContentVO(byId);
		return SingleResponse.of(crawlerRawStoreContentVO);
	}
	/**
	 * 执行 爬虫原始数据存储内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreContentVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerRawStoreContentDO byId = iCrawlerRawStoreContentService.getById(detailForUpdateCommand.getId());
		CrawlerRawStoreContentVO crawlerRawStoreContentVO = CrawlerRawStoreContentAppStructMapping.instance.crawlerRawStoreContentDOToCrawlerRawStoreContentVO(byId);
		return SingleResponse.of(crawlerRawStoreContentVO);
	}


	@Autowired
	public void setICrawlerRawStoreContentService(ICrawlerRawStoreContentService iCrawlerRawStoreContentService) {
		this.iCrawlerRawStoreContentService = iCrawlerRawStoreContentService;
	}
}
