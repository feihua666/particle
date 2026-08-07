package com.particle.crawler.app.definition.executor.representation;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionAppStructMapping;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionPageQueryCommand;
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
 * 爬虫定义 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
@Validated
public class CrawlerDefinitionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrawlerDefinitionService iCrawlerDefinitionService;

	/**
	 * 执行 爬虫定义 列表查询指令
	 * @param crawlerDefinitionQueryListCommand
	 * @return
	 */
	public MultiResponse<CrawlerDefinitionVO> execute(@Valid CrawlerDefinitionQueryListCommand crawlerDefinitionQueryListCommand) {
		List<CrawlerDefinitionDO> crawlerDefinitionDO = iCrawlerDefinitionService.list(crawlerDefinitionQueryListCommand);
		List<CrawlerDefinitionVO> crawlerDefinitionVOs = CrawlerDefinitionAppStructMapping.instance.crawlerDefinitionDOsToCrawlerDefinitionVOs(crawlerDefinitionDO);
		return MultiResponse.of(crawlerDefinitionVOs);
	}
	/**
	 * 执行 爬虫定义 分页查询指令
	 * @param crawlerDefinitionPageQueryCommand
	 * @return
	 */
	public PageResponse<CrawlerDefinitionVO> execute(@Valid CrawlerDefinitionPageQueryCommand crawlerDefinitionPageQueryCommand) {
		Page<CrawlerDefinitionDO> page = iCrawlerDefinitionService.listPage(crawlerDefinitionPageQueryCommand);
		return CrawlerDefinitionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 爬虫定义 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionVO> executeDetail(CommonIdCommand detailCommand) {
		CrawlerDefinitionDO byId = iCrawlerDefinitionService.getById(detailCommand.getId());
		CrawlerDefinitionVO crawlerDefinitionVO = CrawlerDefinitionAppStructMapping.instance.crawlerDefinitionDOToCrawlerDefinitionVO(byId);
		return SingleResponse.of(crawlerDefinitionVO);
	}
	/**
	 * 执行 爬虫定义 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CrawlerDefinitionDO byId = iCrawlerDefinitionService.getById(detailForUpdateCommand.getId());
		CrawlerDefinitionVO crawlerDefinitionVO = CrawlerDefinitionAppStructMapping.instance.crawlerDefinitionDOToCrawlerDefinitionVO(byId);
		return SingleResponse.of(crawlerDefinitionVO);
	}


	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
}
