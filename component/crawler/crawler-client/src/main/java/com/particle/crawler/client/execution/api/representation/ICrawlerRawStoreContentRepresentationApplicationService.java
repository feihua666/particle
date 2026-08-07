package com.particle.crawler.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;

/**
 * <p>
 * 爬虫原始数据存储内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerRawStoreContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreContentVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerRawStoreContentQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerRawStoreContentVO> queryList(CrawlerRawStoreContentQueryListCommand crawlerRawStoreContentQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerRawStoreContentPageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerRawStoreContentVO> pageQuery(CrawlerRawStoreContentPageQueryCommand crawlerRawStoreContentPageQueryCommand);

}
