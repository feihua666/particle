package com.particle.crawler.client.definition.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;

/**
 * <p>
 * 爬虫定义历史 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerDefinitionHistoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerDefinitionHistoryQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerDefinitionHistoryVO> queryList(CrawlerDefinitionHistoryQueryListCommand crawlerDefinitionHistoryQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerDefinitionHistoryPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerDefinitionHistoryVO> pageQuery(CrawlerDefinitionHistoryPageQueryCommand crawlerDefinitionHistoryPageQueryCommand);

}
