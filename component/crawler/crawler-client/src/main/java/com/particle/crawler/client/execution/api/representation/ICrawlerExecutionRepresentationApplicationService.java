package com.particle.crawler.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;

/**
 * <p>
 * 爬虫执行实例 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerExecutionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerExecutionVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerExecutionQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerExecutionVO> queryList(CrawlerExecutionQueryListCommand crawlerExecutionQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerExecutionPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerExecutionVO> pageQuery(CrawlerExecutionPageQueryCommand crawlerExecutionPageQueryCommand);

}
