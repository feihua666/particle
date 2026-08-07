package com.particle.crawler.client.definition.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;

/**
 * <p>
 * 爬虫项目 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerProjectRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerProjectVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerProjectVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerProjectQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerProjectVO> queryList(CrawlerProjectQueryListCommand crawlerProjectQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerProjectPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerProjectVO> pageQuery(CrawlerProjectPageQueryCommand crawlerProjectPageQueryCommand);

}
