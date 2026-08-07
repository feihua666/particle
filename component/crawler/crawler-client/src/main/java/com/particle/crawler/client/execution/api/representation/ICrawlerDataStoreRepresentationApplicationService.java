package com.particle.crawler.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;

/**
 * <p>
 * 爬虫结构数据存储 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerDataStoreRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerDataStoreQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerDataStoreVO> queryList(CrawlerDataStoreQueryListCommand crawlerDataStoreQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerDataStorePageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerDataStoreVO> pageQuery(CrawlerDataStorePageQueryCommand crawlerDataStorePageQueryCommand);

}
