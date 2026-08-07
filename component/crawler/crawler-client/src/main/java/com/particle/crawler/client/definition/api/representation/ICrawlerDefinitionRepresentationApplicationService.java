package com.particle.crawler.client.definition.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;

/**
 * <p>
 * 爬虫定义 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerDefinitionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerDefinitionQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerDefinitionVO> queryList(CrawlerDefinitionQueryListCommand crawlerDefinitionQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerDefinitionPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerDefinitionVO> pageQuery(CrawlerDefinitionPageQueryCommand crawlerDefinitionPageQueryCommand);

}
