package com.particle.crawler.app.execution.executor.representation;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreContentService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentPageQueryCommand;
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
 * 爬虫结构数据存储内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
@Validated
public class CrawlerDataStoreContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerDataStoreContentService iCrawlerDataStoreContentService;

	/**
	 * 执行 爬虫结构数据存储内容 列表查询指令
	 * @param crawlerDataStoreContentQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerDataStoreContentVO> execute(@Valid CrawlerDataStoreContentQueryListCommand crawlerDataStoreContentQueryListCommand) {
		List<CrawlerDataStoreContentDO> crawlerDataStoreContentDO = iCrawlerDataStoreContentService.list(crawlerDataStoreContentQueryListCommand);
		List<CrawlerDataStoreContentVO> crawlerDataStoreContentVOs = CrawlerDataStoreContentAppStructMapping.instance.crawlerDataStoreContentDOsToCrawlerDataStoreContentVOs(crawlerDataStoreContentDO);
		return MultiResponse.of(crawlerDataStoreContentVOs);
	}
	/**
	 * 执行 爬虫结构数据存储内容 分页查询指令
	 * @param crawlerDataStoreContentPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerDataStoreContentVO> execute(@Valid CrawlerDataStoreContentPageQueryCommand crawlerDataStoreContentPageQueryCommand) {
		Page<CrawlerDataStoreContentDO> page = iCrawlerDataStoreContentService.listPage(crawlerDataStoreContentPageQueryCommand);
		return CrawlerDataStoreContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫结构数据存储内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreContentVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerDataStoreContentDO byId = iCrawlerDataStoreContentService.getById(detailCommand.getId());
		CrawlerDataStoreContentVO crawlerDataStoreContentVO = CrawlerDataStoreContentAppStructMapping.instance.crawlerDataStoreContentDOToCrawlerDataStoreContentVO(byId);
		return SingleResponse.of(crawlerDataStoreContentVO);
	}
	/**
	 * 执行 爬虫结构数据存储内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreContentVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerDataStoreContentDO byId = iCrawlerDataStoreContentService.getById(detailForUpdateCommand.getId());
		CrawlerDataStoreContentVO crawlerDataStoreContentVO = CrawlerDataStoreContentAppStructMapping.instance.crawlerDataStoreContentDOToCrawlerDataStoreContentVO(byId);
		return SingleResponse.of(crawlerDataStoreContentVO);
	}


	@Autowired
	public void setICrawlerDataStoreContentService(ICrawlerDataStoreContentService iCrawlerDataStoreContentService) {
		this.iCrawlerDataStoreContentService = iCrawlerDataStoreContentService;
	}
}
