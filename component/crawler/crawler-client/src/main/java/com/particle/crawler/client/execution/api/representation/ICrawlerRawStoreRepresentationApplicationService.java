package com.particle.crawler.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;

/**
 * <p>
 * 爬虫原始数据存储 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrawlerRawStoreRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crawlerRawStoreQueryListCommand
	 * @return
	 */
	MultiResponse<CrawlerRawStoreVO> queryList(CrawlerRawStoreQueryListCommand crawlerRawStoreQueryListCommand);

	/**
	 * 分页查询
	 * @param crawlerRawStorePageQueryCommand
	 * @return
	 */
	PageResponse<CrawlerRawStoreVO> pageQuery(CrawlerRawStorePageQueryCommand crawlerRawStorePageQueryCommand);

}
