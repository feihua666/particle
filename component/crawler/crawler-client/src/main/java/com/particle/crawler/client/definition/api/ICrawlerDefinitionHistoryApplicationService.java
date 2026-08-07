package com.particle.crawler.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateDraftCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryUpdateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
/**
 * <p>
 * 爬虫定义历史 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
public interface ICrawlerDefinitionHistoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerDefinitionHistoryCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionHistoryVO> create(CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand);

	/**
	 * 添加/创建一个草稿
	 * @param crawlerDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> createDraft(CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionHistoryVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerDefinitionHistoryUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionHistoryVO> update(CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand);
}
