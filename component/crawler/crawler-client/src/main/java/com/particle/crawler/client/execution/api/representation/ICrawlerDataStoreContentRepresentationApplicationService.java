package com.particle.crawler.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;

/**
 * <p>
 * 爬虫结构数据存储内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerDataStoreContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreContentVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerDataStoreContentQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerDataStoreContentVO> queryList(CrawlerDataStoreContentQueryListCommand crawlerDataStoreContentQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerDataStoreContentPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerDataStoreContentVO> pageQuery(CrawlerDataStoreContentPageQueryCommand crawlerDataStoreContentPageQueryCommand);

}
